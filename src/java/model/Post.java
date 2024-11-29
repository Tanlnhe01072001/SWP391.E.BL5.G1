/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ThangNPHE151263
 */
public class Post {

    private int postId;
    private String title;
    private String content;
    private Date createAt;
    private Date updateAt;
    private int postTypeId;
    private int userid;
    private String username;
    private String type;

    public Post(int postId, String title, String content, int postTypeId, int userid, Date creDate, Date upDate, String username, String type) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.postTypeId = postTypeId;
        this.userid = userid;
        this.createAt = creDate;
        this.updateAt = upDate;
        this.username = username;
        this.type = type;
    }

    public Post(int postId, String title, String content, int postTypeId, int userid, Date creDate, Date upDate) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.postTypeId = postTypeId;
        this.userid = userid;
        this.createAt = creDate;
        this.updateAt = upDate;
    }

    public Post() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(int postTypeId) {
        this.postTypeId = postTypeId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
