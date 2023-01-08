package com.icchoi.openchatnotify.infra.sms.slack.service;

public interface MessageService {
    void postMessage(String message);
}
