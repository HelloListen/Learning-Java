package servletsAndJSP;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhangbin on 16/2/29.
 */
public class SessionTest extends HttpServlet {
//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("test session attribute<br>");
//
//        HttpSession session = request.getSession();
//        if (session.isNew()) {
//            out.println("This is a new Session");
//        } else {
//            out.println("Welcome back!");
//        }
//    }

//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("test sessions<br>");
//
//        HttpSession session = request.getSession(false);
//
//        if (session == null) {
//            out.println("no session was available");
//            out.println("making one...");
//            session = request.getSession();
//        } else {
//            out.println("there was a session!");
//        }
//    }

    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        out.println("<html><body>");
        out.println("<a href=\""+response.encodeURL("session")+"\">click me</a>");
        out.println("</body></html>");
    }
}
