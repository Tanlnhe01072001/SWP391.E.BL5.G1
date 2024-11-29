/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author hieupham
 */
public class Bill {
    int bill_id;
    User user;
    Float total;
    String payment;
    String address;
    Date date;
    int phone;

    public Bill() {
    }

    public Bill(int bill_id, User user, Float total, String payment, String address, Date date, int phone) {
        this.bill_id = bill_id;
        this.user = user;
        this.total = total;
        this.payment = payment;
        this.address = address;
        this.date = date;
        this.phone = phone;
    }
    
    public Bill(int bill_id,Float total,Date date) {
        this.bill_id = bill_id;
        this.total = total;
        this.date = date;
    }
    
    public Bill(int bill_id, Float total, String payment, String address, Date date, int phone) {
        this.bill_id = bill_id;
        this.total = total;
        this.payment = payment;
        this.address = address;
        this.date = date;
        this.phone = phone;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    

    

    
    
    
    
}
