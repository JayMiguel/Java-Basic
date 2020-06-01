package com.miguel.server.server03.servlet;

import com.miguel.server.server03.entity.Request;
import com.miguel.server.server03.entity.Response;

public interface Servlet {

    void service(Request request, Response response);
}
