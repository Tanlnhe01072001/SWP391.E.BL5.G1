/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;


public class Coupon {
    private int couponId;
    private Date startDate;
    private Date endDate;
    private int usageLimit;
    private int couponTypeId;
    private String code;
    private int userid;
    private String couponDescription;
    private double discountAmount;

    // Constructor bao gồm discountAmount
    public Coupon(int couponId, Date startDate, Date endDate, int usageLimit, int couponTypeId, String code,int userid, String couponDescription, double discountAmount) {
        this.couponId = couponId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.usageLimit = usageLimit;
        this.couponTypeId = couponTypeId;
        this.code = code;
        this.userid = userid;
        this.couponDescription = couponDescription;
        this.discountAmount = discountAmount;
    }

    // Getter và Setter cho discountAmount
    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Coupon(int couponId, Date startDate, Date endDate, int usageLimit, int couponTypeId, String code, int userid) {
        this.couponId = couponId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.usageLimit = usageLimit;
        this.couponTypeId = couponTypeId;
        this.code = code;
        this.userid = userid;
    }

    public Coupon(int couponId, Date startDate, Date endDate, int usageLimit, int couponTypeId, String code,int userid, String couponDescription) {
        this.couponId = couponId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.usageLimit = usageLimit;
        this.couponTypeId = couponTypeId;
        this.code = code;
        this.userid = userid;
        this.couponDescription = couponDescription;
    }


    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    public int getCouponTypeId() {
        return couponTypeId;
    }

    public void setCouponTypeId(int couponTypeId) {
        this.couponTypeId = couponTypeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}