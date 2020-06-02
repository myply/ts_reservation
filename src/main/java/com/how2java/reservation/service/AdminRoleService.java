package com.how2java.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.reservation.dao.AdminDAO;
import com.how2java.reservation.dao.AdminRoleDAO;
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.pojo.AdminRole;
import com.how2java.reservation.pojo.Role;
import com.how2java.reservation.pojo.User;
@Service
public class AdminRoleService {
	@Autowired AdminRoleDAO adminRoleDAO;
	@Autowired AdminDAO adminDAO;
	public void setRoles(Admin admin, int[] roleIds){
		for (int rid : roleIds) {
			AdminRole adminRole=new AdminRole();
			adminRole.setUid(admin.getId());
			adminRole.setRid(rid);
			adminRoleDAO.save(adminRole);
        }
	}
    
	public void add(AdminRole bean){
		adminRoleDAO.save(bean);
	}

	public void deleteByUser(int userId){
		adminRoleDAO.deleteByUid(userId);
	}

	public void deleteByRole(int roleId){
		adminRoleDAO.deleteByRid(roleId);
	}

}
