package com.csc.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Description:
 * @Author: csc
 * @Create: 2024-12-27
 */
@Component
public class Service {
    @Autowired
    WebClient.Builder webClientBuilder;

    public Mono<String> prompt(String id) {


        return get(id);
    }

    // 调用hello模块
    public Mono<String> get(String templateId) {
        WebClient webClient = getWebClient();
        String path = "/hello/get";
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .queryParam("promptId", templateId)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<String>() {
                })
                ;
    }

    public Mono<String> post(User user) {
        WebClient webClient = getWebClient();
        String path = "/hello/postBody";
        return webClient.post()
                .uri(path)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<String>() {
                })
                ;
    }

    private WebClient getWebClient() {
        String baseUrl = "http://127.0.0.1:8081";
        String apiKey = "key";
        return webClientBuilder.baseUrl(baseUrl)
                .defaultHeader("em_api_key", apiKey)
                .build();
    }

    static class User {
        private int id;
        private String name;

        public User() {
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
