package com.icchoi.openchatnotify.service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SlackBotService {

    @Value("${slack.token}")
    String token;
    @Value("${slack.channel.monitor}")
    String channel;

    public void postSlackMessage(String message){
        try {
            MethodsClient methods = Slack.getInstance().methods(token);
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel)
                .text(message)
                .build();

            methods.chatPostMessage(request);
//            log.info("보냄");
        } catch (SlackApiException | IOException e) {
//            log.error(e.getMessage());
        }
    }
}
