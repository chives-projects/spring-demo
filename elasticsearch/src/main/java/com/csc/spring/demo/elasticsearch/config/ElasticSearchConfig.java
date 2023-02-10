package com.csc.spring.demo.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/26 15:58
 */
@Configuration
public class ElasticSearchConfig {
    private String esHost = "127.0.0.1";
    private int esPort = 9200;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(esHost, esPort, "http"))
        );
    }
}
