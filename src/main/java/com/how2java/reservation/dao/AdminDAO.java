package com.how2java.reservation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.how2java.reservation.pojo.Admin;

public interface AdminDAO extends JpaRepository<Admin,Integer>{
	 
}
