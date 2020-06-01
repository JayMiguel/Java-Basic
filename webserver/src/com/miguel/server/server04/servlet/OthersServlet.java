package com.miguel.server.server04.servlet;

import com.miguel.server.server04.entity.Request;
import com.miguel.server.server04.entity.Response;

public class OthersServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.print("<html>");
        response.print("<head>");
        response.print("<title>");
        response.print("OthersServlet");
        response.print("</title>");
        response.print("</head>");
        response.print("</html>");
    }
}
