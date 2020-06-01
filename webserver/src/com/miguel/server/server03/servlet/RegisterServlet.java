package com.miguel.server.server03.servlet;

import com.miguel.server.server03.entity.Request;
import com.miguel.server.server03.entity.Response;

public class RegisterServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.print("注册成功");
    }
}
