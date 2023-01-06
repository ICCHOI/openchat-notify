package com.icchoi.openchatnotify.scheduler;

import com.icchoi.openchatnotify.domain.OpenChat;
import com.icchoi.openchatnotify.service.OpenChatService;
import com.icchoi.openchatnotify.service.SlackBotService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class SlackScheduler {

    private final SlackBotService slackService;
    private final OpenChatService openChatService;

    public SlackScheduler(SlackBotService slackService, OpenChatService openChatService) {
        this.slackService = slackService;
        this.openChatService = openChatService;
    }

    @Scheduled(cron="0 0/1 * * * ?") // 1분마다
    public void todayCocktail() {
        OpenChat openchat = openChatService.getApi();
        if (openchat.isHeadcountFull()) {
            slackService.postSlackMessage("빈 자리가 있습니다.");
        }
    }
}
