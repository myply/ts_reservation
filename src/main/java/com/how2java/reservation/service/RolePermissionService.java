package com.how2java.reservation.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.how2java.reservation.dao.AdminDAO;
import com.how2java.reservation.dao.RolePermissionDAO;
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.pojo.AdminRole;
import com.how2java.reservation.pojo.Role;
import com.how2java.reservation.pojo.RolePermission;
import com.how2java.reservation.pojo.User;
import com.how2java.reservation.util.Page4Navigator;
@Service
public class RolePermissionService {
	@Autowired RolePermissionDAO rolepermissionDAO;
	public void setPermissions(Role role, int[] permissionIds){
		for (int pid : permissionIds) {
			RolePermission rolepermission=new RolePermission();
			rolepermission.setRid(role.getId());
			rolepermission.setPid(pid);
			rolepermissionDAO.save(rolepermission);
        }
	}

	public void deleteByRole(int roleId){
		rolepermissionDAO.deleteByRid(roleId);
	}

	public void deleteByPermission(int permissionId){
		rolepermissionDAO.deleteByPid(permissionId);
	}
}
