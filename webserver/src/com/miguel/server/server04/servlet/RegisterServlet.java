package com.miguel.server.server04.servlet;

import com.miguel.server.server04.entity.Request;
import com.miguel.server.server04.entity.Response;

public class RegisterServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.print("注册成功");
    }
}
