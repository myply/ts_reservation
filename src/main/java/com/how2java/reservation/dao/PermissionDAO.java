package com.how2java.reservation.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.how2java.reservation.pojo.Permission;
public interface PermissionDAO extends JpaRepository<Permission,Integer>{

	List<Permission> findByPid(int pid);

	 
}