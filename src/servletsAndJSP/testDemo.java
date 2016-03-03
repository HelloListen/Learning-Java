package com.listenzhangbin.jsp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangbin on 16/3/3.
 */
public class testDemo extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
        response.setContentType("text/html");
        List<String> params = new ArrayList<>();
        params.add("Fred");
        params.add("Pradeep");
        params.add("Philippe");

        request.setAttribute("names",params);

        RequestDispatcher req = request.getRequestDispatcher("hobby.jsp");
        req.forward(request,response);
    }
}
