package model;

import java.util.Date;

public class BlogCmt {
    private Long commentId;
    private Long blogId;
    private Long userId;
    private String commentText;
    private Date createdAt;
    private Date updatedAt;

    // Default constructor
    public BlogCmt() {}

    // Parameterized constructor
    public BlogCmt(Long commentId, Long blogId, Long userId, String commentText, Date createdAt, Date updatedAt) {
        this.commentId = commentId;
        this.blogId = blogId;
        this.userId = userId;
        this.commentText = commentText;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getter and Setter for commentId
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    // Getter and Setter for blogId
    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    // Getter and Setter for userId
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Getter and Setter for commentText
    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    // Getter and Setter for createdAt
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Getter and Setter for updatedAt
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
