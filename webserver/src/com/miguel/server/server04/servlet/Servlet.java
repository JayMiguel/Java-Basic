package com.miguel.server.server04.servlet;

import com.miguel.server.server04.entity.Request;
import com.miguel.server.server04.entity.Response;

public interface Servlet {

    void service(Request request, Response response);
}
