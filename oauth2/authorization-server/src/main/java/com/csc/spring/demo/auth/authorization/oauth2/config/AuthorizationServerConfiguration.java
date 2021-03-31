package com.csc.spring.demo.auth.authorization.oauth2.config;

import com.csc.spring.demo.auth.authorization.config.OAuthTokenAuthenticationFilter;
import com.csc.spring.demo.auth.authorization.config.UserTokenEnhancer;
import com.csc.spring.demo.auth.authorization.security.OAuth2ClientDetailsService;
import com.csc.spring.demo.auth.authorization.security.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Description: 配置ClientDetailsServiceConfigurer、AuthorizationServerEndpointsConfigurer、AuthorizationServerSecurityConfigurer
 * @PackageName: com.example.temp.oauth2.config
 * @Author: csc
 * @Create: 2021-03-23 16:11
 * @Version: 1.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    /**
     * Redis工厂类
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    /**
     * OAuth2 token持久化接口
     */
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecurityUserService securityUserService;
    @Autowired
    private OAuth2ClientDetailsService oAuth2ClientDetailsService;
    @Autowired
    private ClientDetailsService clientDetailsService;
//    @Autowired
//    private WebResponseExceptionTranslator webResponseExceptionTranslator;
//    @Autowired
//    private OAuthTokenAuthenticationFilter oAuthTokenAuthenticationFilter;
    @Autowired
    private UserTokenEnhancer userTokenEnhancer;
//    @Autowired
//    private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String RESOURCE_ONE = "oauth2";
    private static final String RESOURCE_TWO = "RESOURCE_TWO";

    /**
     * 配置从哪里获取ClientDetails信息。
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 1. 数据库的方式
        clients.withClientDetails(clientDetailsService);
        // 2. 在内存中配置，这种方式不够灵活，学习倒是没有问题
        // //配置两个客户端,一个用于password认证一个用于client认证
        clients.inMemory().withClient("client_1")
                .resourceIds(RESOURCE_ONE)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("select")
                .authorities("client")
                .secret("123456")
                .and().withClient("client_2")
                .resourceIds(RESOURCE_TWO)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("client")
                .secret("123456")
                .redirectUris("http://www.baidu.com")
        ;

    }


    /**
     * 声明授权和token的端点以及token的服务的一些配置信息，
     * 比如采用什么存储方式、token的有效期等
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //token持久化容器
        tokenServices.setTokenStore(tokenStore);
        //客户端信息
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        //自定义token生成
        tokenServices.setTokenEnhancer(userTokenEnhancer);
        //access_token 的有效时长 (秒), 默认 12 小时
        tokenServices.setAccessTokenValiditySeconds(60 * 1);
        //refresh_token 的有效时长 (秒), 默认 30 天
        tokenServices.setRefreshTokenValiditySeconds(60 * 2);
        //是否支持refresh_token，默认false
        tokenServices.setSupportRefreshToken(true);
        //是否复用refresh_token,默认为true(如果为false,则每次请求刷新都会删除旧的refresh_token,创建新的refresh_token)
        tokenServices.setReuseRefreshToken(false);
        endpoints
                .tokenServices(tokenServices)
//                .tokenStore(tokenStore())
                // 配置授权管理认证对象
                .authenticationManager(authenticationManager)
                // 配置加载用户信息的服务
                .userDetailsService(securityUserService)
                // 授权码服务,添加就可以保存到数据库了
//                .authorizationCodeServices(authorizationCodeServices)
                .setClientDetailsService(clientDetailsService);
    }


    /**
     * 声明安全约束，哪些允许访问，哪些不允许访问
     */

    /**
     * 配置：安全检查流程,用来配置令牌端点（Token Endpoint）的安全与权限访问
     * 默认过滤器：BasicAuthenticationFilter
     * 1、oauth_client_details表中clientSecret字段加密【ClientDetails属性secret】
     * 2、CheckEndpoint类的接口 oauth/check_token 无需经过过滤器过滤，默认值：denyAll()
     * 对以下的几个端点进行权限配置：
     * /oauth/authorize：授权端点
     * /oauth/token：令牌端点
     * /oauth/confirm_access：用户确认授权提交端点
     * /oauth/error：授权服务错误信息端点
     * /oauth/check_token：用于资源服务访问的令牌解析端点
     * /oauth/token_key：提供公有密匙的端点，如果使用JWT令牌的话
     **/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.allowFormAuthenticationForClients()//允许客户表单认证
                .passwordEncoder(passwordEncoder)//设置oauth_client_details中的密码编码器
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")

                //允许表单认证
                .allowFormAuthenticationForClients()
                .passwordEncoder(passwordEncoder)
                // 对于CheckEndpoint控制器[框架自带的校验]的/oauth/check端点允许所有客户端发送器请求而不会被Spring-security拦截
                .tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
                .realm("oauth2");
    }

    @Bean
    public TokenStore tokenStore() {
        //token保存在内存中（也可以保存在数据库、Redis中）。
        //如果保存在中间件（数据库、Redis），那么资源服务器与认证服务器可以不在同一个工程中。
        //注意：如果不保存access_token，则没法通过access_token取得用户信息
        //return new InMemoryTokenStore();
        return new RedisTokenStore(redisConnectionFactory);
    }
}
