package com.collekarry.intern;

import android.widget.LinearLayout;

import org.w3c.dom.Comment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class History implements Serializable{
    private String title;
    private String description;
    private Date date;
    private List<String> attachments;
    private List<com.collekarry.intern.Comment> comments;

    public History() {
    }

    public History(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getAttachments() {
        return attachments;
    }
    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public List<com.collekarry.intern.Comment> getComments() {
        return comments;
    }
    public void setComments(List<com.collekarry.intern.Comment> comments) {
        this.comments = comments;
    }
}