package com.mashibing.consumer.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @Author Jianrong.Chen
 * @Description 对client请求的拦截，打印请求前和请求后的请求信息
 * @Date 2021-10-26 09:52
 * @Param
 * @return
 **/
public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        System.out.println("拦截啦！！！");
        System.out.println("uri:" + request + "=======" + "headers:" + request.getHeaders());

        ClientHttpResponse response = execution.execute(request, body);

        System.out.println(response.getHeaders());
        return response;
    }
}