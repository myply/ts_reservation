package com.how2java.reservation.dao;
  
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.pojo.User;
 
public interface UserDAO extends JpaRepository<User,Integer>{

	


 
}