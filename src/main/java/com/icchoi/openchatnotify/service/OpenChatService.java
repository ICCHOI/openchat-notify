package com.icchoi.openchatnotify.service;

import com.icchoi.openchatnotify.domain.OpenChat;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Service
public class OpenChatService {

    // TODO 여러 개의 채팅방을 검사 할 수 있도록 구현
    private static final String BASE_URL = "https://api.develope.kr/search/room?room=https://open.kakao.com/o/gPjtvkfe";

    private final HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(5000))
            .doOnConnected(conn -> conn
                    .addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                    .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

    private final WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//        .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
            .build();

    public OpenChat getApi() {
        return webClient.get()
                .uri(BASE_URL)
                .retrieve()
                .bodyToMono(OpenChat.class)
                .block();
    }
}
