package com.icchoi.openchatnotify.infra.sms.slack.scheduler;

import com.icchoi.openchatnotify.domain.openchat.model.OpenChat;
import com.icchoi.openchatnotify.domain.openchat.service.OpenChatService;
import com.icchoi.openchatnotify.infra.sms.slack.service.MessageService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class SlackScheduler {

    private final MessageService slackService;
    private final OpenChatService openChatService;

    public SlackScheduler(MessageService slackService, OpenChatService openChatService) {
        this.slackService = slackService;
        this.openChatService = openChatService;
    }

    @Scheduled(cron="0 0/1 * * * ?") // 1분마다
    public void todayCocktail() {
        OpenChat openchat = openChatService.getApi();
        if (openchat.isHeadcountFull()) {
            slackService.postMessage("빈 자리가 있습니다.");
        }
    }
}
