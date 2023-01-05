package com.icchoi.openchatnotify.domain;

import java.util.List;

class Result {
    String name;
    String link;
    String description;
    int headcount;
    String master;
    List<String> tags;
    int like;

    public Result() {
    }

    public Result(String name, String link, String description, int headcount, String master, List<String> tags, int like) {
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

    public int getHeadcount() {
        return headcount;
    }

    public String getMaster() {
        return master;
    }

    public List<String> getTags() {
        return tags;
    }

    public int getLike() {
        return like;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", headcount=" + headcount +
                ", master='" + master + '\'' +
                ", tags=" + tags +
                ", like=" + like +
                '}';
    }
}
