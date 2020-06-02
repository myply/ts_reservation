package com.how2java.reservation.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reservation")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")   
    int id;
    @Column(name = "createdate")  
    Date createdate;
    @Column(name = "begintime")  
    Date begintime;
    @Column(name = "endtime")  
    Date endtime;
    @Column(name = "status")  
    String status;
    @Column(name = "uid")   
    int uid;
    @Column(name = "tid")   
    int tid;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public int getTid() {
        return tid;
    }
    public void setTid(int tid) {
        this.tid = tid;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getCreateDate() {
        return createdate;
    }
    public void setCreateDate(Date createdate) {
        this.createdate = createdate;
    }
    public Date getBeginTime() {
        return begintime;
    }
    public void setBeginTime(Date begintime) {
        this.begintime = begintime;
    }
    public Date getEndTime() {
        return endtime;
    }
    public void setEndTime(Date endtime) {
        this.endtime = endtime;
    }
    
    
}