package com.icchoi.openchatnotify.infra.sms.slack.service;

import com.icchoi.openchatnotify.infra.sms.slack.exception.SlackApiCallException;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SlackBotService implements MessageService {

    @Value("${slack.token}")
    private String token;
    @Value("${slack.channel.monitor}")
    private String channel;

    public void postMessage(String message){
        try {
            MethodsClient methods = Slack.getInstance().methods(token);
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(message)
                .build();

            methods.chatPostMessage(request);
//            log.info("보냄");
        } catch (SlackApiException | IOException e) {
            throw new SlackApiCallException();
        }
    }
}
