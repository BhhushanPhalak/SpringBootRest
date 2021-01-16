package com.bhushan.examples.jaxrs.model;

import org.jetbrains.annotations.NotNull;

public class Comments implements Comparable<Comments>{

    private String text;
    private String user;
    private Integer descendants;
    private Integer noOfChildComment;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getDescendants() {
        return descendants;
    }

    public void setDescendants(Integer descendants) {
        this.descendants = descendants;
    }

    public Integer getNoOfChildComment() {
        return noOfChildComment;
    }

    public void setNoOfChildComment(Integer noOfChildComment) {
        this.noOfChildComment = noOfChildComment;
    }

    @Override
    public int compareTo(@NotNull Comments o) {
        return o.getNoOfChildComment() - this.noOfChildComment;
    }
}
