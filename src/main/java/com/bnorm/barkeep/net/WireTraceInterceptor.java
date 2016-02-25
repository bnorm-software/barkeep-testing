package com.bnorm.barkeep.net;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;

public class WireTraceInterceptor implements Interceptor {

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        RequestBody requestBody = request.body();
        if (requestBody != null) {
            if (requestBody.contentType() != null && requestBody.contentType().charset() != null) {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                String rawBody = buffer.readByteString().utf8();
                System.out.println(String.format("Sending [%s %s] with body [%s]",
                                                 request.method(),
                                                 request.url(),
                                                 rawBody));
            } else {
                System.out.println(String.format("Sending [%s %s] with non-text body",
                                                 request.method(),
                                                 request.url()));
            }
        } else {
            System.out.println(String.format("Sending [%s %s]", request.method(), request.url()));
        }

        Instant start = Instant.now();
        okhttp3.Response response = chain.proceed(request);

        Duration duration = Duration.between(start, Instant.now());
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            if (responseBody.contentType() != null && responseBody.contentType().charset() != null) {
                String rawBody = responseBody.string();
                System.out.println(String.format("Received response for [%s %s] in %sms with body [%s]",
                                                 response.request().method(),
                                                 response.request().url(),
                                                 duration.toMillis(),
                                                 rawBody));

                // Reading the body as a string closes the stream so create a new response body
                response = response.newBuilder()
                                   .body(ResponseBody.create(responseBody.contentType(), rawBody))
                                   .build();
            } else {
                System.out.println(String.format("Received response for [%s %s] in %sms with non-text body",
                                                 response.request().method(),
                                                 response.request().url(),
                                                 duration.toMillis()));
            }
        } else {
            System.out.println(String.format("Received response for [%s %s] in %sms",
                                             response.request().method(),
                                             response.request().url(),
                                             duration.toMillis()));
        }

        return response;
    }
}
