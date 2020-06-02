package com.how2java.reservation.pojo;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 
@Entity
@Table(name = "teacher")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
 
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")   
    int id;
    int did;
    int aid;
    String name;
    public int getAid() {
        return aid;
    }
    public void setAid(int aid) {
        this.aid = aid;
    } 
    public int getDid() {
        return did;
    }
    public void setDid(int did) {
        this.did = did;
    } 
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}