package com.icchoi.openchatnotify.application.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.icchoi.openchatnotify.domain.openchat.service.OpenChatApiClient;
import com.icchoi.openchatnotify.infra.message.slack.service.MessageService;

@EnableScheduling
@Configuration
public class OpenChatNotifier {

	private final MessageService slackService;
	private final OpenChatApiClient openChatApiClient;

	public OpenChatNotifier(MessageService slackService, OpenChatApiClient openChatApiClient) {
		this.slackService = slackService;
		this.openChatApiClient = openChatApiClient;
	}

	@Scheduled(cron = "0/10 * * * * ?") // 1분마다
	public void checkOpenChatNotify() {

		openChatApiClient
			.getOpenChat()
			.subscribe(
				openChat -> {
					if (openChat.isHeadcountFull()) {
						slackService.postMessage("빈 자리가 있습니다.");
					}
				},
				error -> slackService.postMessage("에러 발생")
			);
	}
}
