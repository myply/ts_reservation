package com.how2java.reservation.pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "role")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")   
    int id;
	
	@Column(name = "name")   
    String name;
	
	@Column(name = "desc_")   
    String desc_;
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
    public String getDesc_() {
        return desc_;
    }
    public void setDesc_(String desc_) {
        this.desc_ = desc_;
    }
}
