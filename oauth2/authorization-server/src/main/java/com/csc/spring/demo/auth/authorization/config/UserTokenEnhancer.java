package com.csc.spring.demo.auth.authorization.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/31 23:13
 */
@Component
public class UserTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if(accessToken instanceof DefaultOAuth2AccessToken){
            DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
            token.setValue(getToken());
            //使用DefaultExpiringOAuth2RefreshToken类生成refresh_token，自带过期时间，否则不生效，refresh_token一直有效
            DefaultExpiringOAuth2RefreshToken refreshToken = (DefaultExpiringOAuth2RefreshToken)token.getRefreshToken();
            //OAuth2RefreshToken refreshToken = token.getRefreshToken();
            if(refreshToken instanceof DefaultExpiringOAuth2RefreshToken){
                token.setRefreshToken(new DefaultExpiringOAuth2RefreshToken(getToken(), refreshToken.getExpiration()));
            }
            Map<String, Object> additionalInformation = new HashMap<>();
            additionalInformation.put("client_id", authentication.getOAuth2Request().getClientId());
            //添加额外配置信息
            token.setAdditionalInformation(additionalInformation);
            return token;
        }
        return accessToken;
    }

    /**
     * 生成自定义token
     */
    private String getToken(){
        return StringUtils.join(UUID.randomUUID().toString().replace("-", ""));
    }
}
