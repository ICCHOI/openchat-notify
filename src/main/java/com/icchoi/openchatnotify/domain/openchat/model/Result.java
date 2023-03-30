package com.icchoi.openchatnotify.domain.openchat.model;

import java.util.List;

class Result {
	private String name;
	private String link;
	private String description;
	private Integer headcount;
	private String master;
	private List<String> tags;
	private Integer like;

	public Result() {
	}

	public Result(String name, String link, String description, int headcount, String master, List<String> tags,
		int like) {
		this.name = name;
		this.link = link;
		this.description = description;
		this.headcount = headcount;
		this.master = master;
		this.tags = tags;
		this.like = like;
	}

	public String getName() {
		return name;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public Integer getHeadcount() {
		return headcount;
	}

	public String getMaster() {
		return master;
	}

	public List<String> getTags() {
		return tags;
	}

	public Integer getLike() {
		return like;
	}

}
