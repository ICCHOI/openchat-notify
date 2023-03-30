package com.icchoi.openchatnotify.domain.openchat.service;

import com.icchoi.openchatnotify.domain.openchat.model.OpenChat;

import reactor.core.publisher.Mono;

public interface OpenChatApiClient {
    Mono<OpenChat> getOpenChat();
}
