package com.bhushan.examples.jaxrs.model;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Stories implements Comparable<Stories>{

    private String title;
    private String url;
    private Integer score;
    private Date timeOfSubmission;
    private String user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getTimeOfSubmission() {
        return timeOfSubmission;
    }

    public void setTimeOfSubmission(Date timeOfSubmission) {
        this.timeOfSubmission = timeOfSubmission;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Stories{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", score=" + score +
                ", timeOfSubmission=" + timeOfSubmission +
                ", user='" + user + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NotNull Stories o) {
        return o.getScore() - this.getScore();
    }
}