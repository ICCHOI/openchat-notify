package com.icchoi.openchatnotify.domain.openchat.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.icchoi.openchatnotify.domain.openchat.model.OpenChat;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Service
public class KakaoOpenChatApiClient implements OpenChatApiClient {

	private static final String TARGET_ROOM_URL = "https://api.develope.kr/search/room?room=%EC%9C%A0%EC%BE%8C%ED%95%9C%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B0%A9";

	private final HttpClient httpClient;

	public KakaoOpenChatApiClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public WebClient webClient() {
		DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(TARGET_ROOM_URL);
		factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

		return WebClient.builder()
			.clientConnector(new ReactorClientHttpConnector(httpClient))
			.defaultCookie("cookieKey", "cookieValue")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.uriBuilderFactory(factory)
			.build();
	}

	public Mono<OpenChat> getOpenChat() {

		return webClient()
			.get()
			.retrieve()
			.bodyToMono(OpenChat.class);
	}
}
