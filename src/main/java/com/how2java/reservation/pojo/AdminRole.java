package com.how2java.reservation.pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "admin_role")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class AdminRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")   
    int id;
    @Column(name = "uid")   
    int uid;
    @Column(name = "rid")   
    int rid;
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
    public int getRid() {
        return rid;
    }
    public void setRid(int rid) {
        this.rid = rid;
    }
}
