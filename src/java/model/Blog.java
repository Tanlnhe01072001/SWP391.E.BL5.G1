/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author hieupham
 */
public class Blog {

    private int blog_id;
    private String title;
    private String summary;
    private String content;
    private Date created_at;
    private Date updated_at;
    private User user;
    private String images;
    private int user_id;


    public Blog() {
    }

    public Blog(int blog_id, String title, String summary, String content, Date created_at, Date updated_at, User user, String images) {
        this.blog_id = blog_id;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user = user;
        this.images = images;
    }
    
    public Blog(int blog_id, String title, String summary, String content, Date created_at, Date updated_at, int user_id, String images) {
        this.blog_id = blog_id;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user = user;
        this.images = images;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
    
     public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}