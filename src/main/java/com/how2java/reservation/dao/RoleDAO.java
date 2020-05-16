package com.how2java.reservation.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.how2java.reservation.pojo.Role;
public interface RoleDAO extends JpaRepository<Role,Integer>{

	List<Role> findById(int rid);


}
