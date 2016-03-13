package com.listenzhangbin.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

/**
 * Created by zhangbin on 16/3/11.
 */
public class CompressionFilter implements Filter {
    private ServletContext ctx;
    private FilterConfig cfg;

    @Override
    public void init(FilterConfig cfg) throws ServletException {
        this.cfg = cfg;
        ctx = cfg.getServletContext();
        ctx.log(cfg.getFilterName() + " initialized.");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String valid_encodings = request.getHeader("Accept-Encoding");
        if (valid_encodings.contains("gzip")) {
            CompressionResponseWrapper wrapperResp = new CompressionResponseWrapper(response);
            wrapperResp.setHeader("Content-Encoding", "gzip");
            fc.doFilter(request, wrapperResp);

            GZIPOutputStream gzos = wrapperResp.getGZIPOutputStream();
            gzos.finish();
            ctx.log(cfg.getFilterName() + ": finished the request.");
        } else {
            ctx.log(cfg.getFilterName() + ": no encoding performed.");
            fc.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}

class CompressionResponseWrapper extends HttpServletResponseWrapper {
    private GZIPServletOutputStream servletGzipOS = null;
    private PrintWriter pw = null;

    CompressionResponseWrapper(HttpServletResponse resp) {
        super(resp);
    }

    @Override
    public void setContentLength(int len) {
    }

    public GZIPOutputStream getGZIPOutputStream() {
        return this.servletGzipOS.internalGzipOS;
    }

    private Object streamUsed = null;

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if ((streamUsed != null) && (streamUsed != pw)) {
            throw new IllegalStateException();
        }
        if (servletGzipOS == null) {
            servletGzipOS = new GZIPServletOutputStream(getResponse().getOutputStream());
            streamUsed = servletGzipOS;
        }
        return servletGzipOS;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if ((streamUsed != null) && (streamUsed != servletGzipOS)) {
            throw new IllegalStateException();
        }
        if (pw == null) {
            servletGzipOS = new GZIPServletOutputStream(getResponse().getOutputStream());
            OutputStreamWriter osw = new OutputStreamWriter(servletGzipOS, getResponse().getCharacterEncoding());
            pw = new PrintWriter(osw);
            streamUsed = pw;
        }
        return pw;
    }
}

class GZIPServletOutputStream extends ServletOutputStream {
    GZIPOutputStream internalGzipOS;

    GZIPServletOutputStream(ServletOutputStream sos) throws IOException {
        this.internalGzipOS = new GZIPOutputStream(sos);
    }

    @Override
    public void write(int param) throws IOException {
        internalGzipOS.write(param);
    }
}