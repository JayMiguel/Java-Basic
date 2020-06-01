package com.miguel.server.server04.servlet;

import com.miguel.server.server04.entity.Request;
import com.miguel.server.server04.entity.Response;

public class LoginServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.print("<html>");
        response.print("<head>");
        response.print("<title>");
        response.print("LoginServlet");
        response.print("</title>");
        response.print("</head>");
        response.print("<body>");
        response.print("欢迎回来：" + request.getParameter("uname"));
        response.print("<body>");
        response.print("</html>");
    }
}
