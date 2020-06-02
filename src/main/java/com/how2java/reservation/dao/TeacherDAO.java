package com.how2java.reservation.dao;
  
import org.springframework.data.jpa.repository.JpaRepository;

import com.how2java.reservation.pojo.Teacher;
import com.how2java.reservation.pojo.User;
 
public interface TeacherDAO extends JpaRepository<Teacher,Integer>{
 
}