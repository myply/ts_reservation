package com.how2java.reservation.pojo;

import java.util.Date;

public class ReservationInfo {
	int id;
    Date begintime;
    Date endtime; 
    String status;
    String user;  
    String teacher;
    public ReservationInfo(Reservation r) {
		// TODO Auto-generated constructor stub
    	setId(r.getId());
    	setBeginTime(r.getBeginTime());
    	setEndTime(r.getEndTime());
    	setStatus(r.getStatus());	
	}
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
