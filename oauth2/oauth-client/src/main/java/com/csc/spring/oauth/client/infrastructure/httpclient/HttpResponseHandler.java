package com.csc.spring.oauth.client.infrastructure.httpclient;

/**
 * @author Shengzhao Li
 */

public interface HttpResponseHandler {


    public void handleResponse(MkkHttpResponse response);

}