package com.how2java.reservation.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.how2java.reservation.pojo.Permission;
import com.how2java.reservation.pojo.RolePermission;
public interface RolePermissionDAO extends JpaRepository<RolePermission,Integer>{

	void deleteByRid(int roleId);

	void deleteByPid(int permissionId);

	List<RolePermission> findByRid(int rid);

}
