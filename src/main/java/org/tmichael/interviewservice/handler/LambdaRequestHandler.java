package org.tmichael.interviewservice.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaRequestHandler implements RequestHandler<String, String> {

    @Override
    public String handleRequest(String s, Context context) {
        return "Hello, " + s;
    }
}
