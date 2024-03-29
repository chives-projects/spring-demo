package com.csc.spring.oauth.client.service.impl;

import com.csc.spring.oauth.client.infrastructure.httpclient.MkkHttpResponse;
import com.csc.spring.oauth.client.service.dto.AccessTokenDto;

/**
 * 15-5-18
 *
 * @author Shengzhao Li
 */
public class AccessTokenResponseHandler extends AbstractResponseHandler<AccessTokenDto> {


    private AccessTokenDto accessTokenDto;

    public AccessTokenResponseHandler() {
    }

    @Override
    public void handleResponse(MkkHttpResponse response) {
        if (response.isResponse200()) {
            this.accessTokenDto = responseToDto(response, new AccessTokenDto());
        } else {
            this.accessTokenDto = responseToErrorDto(response, new AccessTokenDto());
        }
    }


    public AccessTokenDto getAccessTokenDto() {
        return accessTokenDto;
    }
}
