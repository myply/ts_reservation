package com.how2java.reservation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.how2java.reservation.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.how2java.reservation.dao.AdminDAO;
import com.how2java.reservation.dao.AdminRoleDAO;
import com.how2java.reservation.dao.PermissionDAO;
import com.how2java.reservation.dao.RoleDAO;
import com.how2java.reservation.dao.RolePermissionDAO;
import com.how2java.reservation.dao.UserDAO;
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.pojo.AdminRole;
import com.how2java.reservation.pojo.Permission;
import com.how2java.reservation.pojo.Role;
import com.how2java.reservation.pojo.RolePermission;
import com.how2java.reservation.pojo.User;

@Service
public class PermissionService {
	@Autowired PermissionDAO permissionDAO;
	@Autowired RoleDAO roleDAO;
	@Autowired AdminDAO adminDAO;
	@Autowired AdminRoleDAO adminRoleDAO;
	@Autowired RolePermissionDAO rolePermissionDAO;
	public Set<String> listPermissions(String userName){
		Set<String> rs = new HashSet<>();
		List<Admin> cs=adminDAO.findByName(userName);
		int Uid=cs.get(0).getId();
		List<AdminRole> cs1=adminRoleDAO.findByUid(Uid);
		for (AdminRole ar : cs1) {
			List<RolePermission> cs2=rolePermissionDAO.findByRid(ar.getRid());
			for (RolePermission rp : cs2){
				int pid=rp.getPid();
				List<Permission> cs3=permissionDAO.findById(pid);
				rs.add(cs3.get(0).getName());
			}
        }
	    return rs;	
	}

	public List<Permission> list(){
		List<Permission> cs=permissionDAO.findAll();
		return cs;
	}

	public void add(Permission permission){
		permissionDAO.save(permission);
	}

	public void delete(int id){
		permissionDAO.delete(id);
	}

	public Permission get(int id){
		Permission p=permissionDAO.getOne(id);
		return p;
	}

	public void update(Permission permission){
		permissionDAO.save(permission);
	}

	public List<Permission> list(Role role){
		List<Permission> rs=new ArrayList<Permission>();
		List<RolePermission> cs2=rolePermissionDAO.findByRid(role.getId());
		for (RolePermission rp : cs2){
			int pid=rp.getPid();
			List<Permission> cs3=permissionDAO.findById(pid);
			rs.addAll(cs3);
		}
	    return rs;	
	}

	public boolean needInterceptor(String requestURI){
		List<Permission> ps = permissionDAO.findAll();
		for (Permission p : ps) {
			if (p.getUrl().equals(requestURI))
				return true;
		}
		return false;
	}

	public Set<String> listPermissionURLs(String userName){
		Set<String> result = new HashSet<>();
		List<Admin> cs=adminDAO.findByName(userName);
		int Uid=cs.get(0).getId();
		List<AdminRole> cs1=adminRoleDAO.findByUid(Uid);
		for (AdminRole ar : cs1) {
			List<RolePermission> cs2=rolePermissionDAO.findByRid(ar.getRid());
			for (RolePermission rp : cs2){
				int pid=rp.getPid();
				List<Permission> cs3=permissionDAO.findById(pid);
				result.add(cs3.get(0).getUrl());
			}
        }
		return result;
	}
}
