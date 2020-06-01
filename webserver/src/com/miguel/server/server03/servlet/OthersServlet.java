package com.miguel.server.server03.servlet;

import com.miguel.server.server03.entity.Request;
import com.miguel.server.server03.entity.Response;

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
