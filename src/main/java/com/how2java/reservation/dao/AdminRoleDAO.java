package com.how2java.reservation.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.pojo.AdminRole;
public interface AdminRoleDAO extends JpaRepository<AdminRole,Integer>{


	void deleteByUid(int userId);

	void deleteByRid(int roleId);

	List<AdminRole> findByUid(int uid);


}