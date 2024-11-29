/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ThangNPHE151263
 */
public class About {
    private int AboutId;
    private String Title;
    private String img;
    private String Content;
    private int UserId;
    private String UserName;

    public About() {
    }

    public About(int AboutId, String Title, String img, String Content, int UserId) {
        this.AboutId = AboutId;
        this.Title = Title;
        this.img = img;
        this.Content = Content;
        this.UserId = UserId;
    }
    public About(int AboutId, String Title, String img, String Content, String UserName) {
        this.AboutId = AboutId;
        this.Title = Title;
        this.img = img;
        this.Content = Content;
        this.UserName = UserName;
    }
    

    public int getAboutId() {
        return AboutId;
    }

    public void setAboutId(int AboutId) {
        this.AboutId = AboutId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    
}
