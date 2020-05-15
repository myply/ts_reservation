package com.how2java.reservation.pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "role_permission")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class RolePermission {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")   
    int id;
    @Column(name = "pid")   
    int pid;
    @Column(name = "rid")   
    int rid;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public int getRid() {
        return rid;
    }
    public void setRid(int rid) {
        this.rid = rid;
    }
}
