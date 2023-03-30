package com.icchoi.openchatnotify.domain.openchat.model;

public class OpenChat {
    private String success;
	private Result result;
	private String node;

	public OpenChat() {
	}

	public OpenChat(String success, Result result, String node) {
		this.success = success;
		this.result = result;
		this.node = node;
	}

	public String getSuccess() {
		return success;
	}

	public Result getResult() {
		return result;
	}

	public String getNode() {
		return node;
	}

	@Override
	public String toString() {
		return "OpenChat{" +
			"success='" + success + '\'' +
			", result=" + result.toString() +
			", node='" + node + '\'' +
			'}';
	}

	// TODO 외부 입력값을 받아 최종 인원을 계산 할 수 있도록 개선
	public boolean isHeadcountFull() {
		return result.getHeadcount() < 900;
	}
}

