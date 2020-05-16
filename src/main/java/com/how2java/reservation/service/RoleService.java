package com.how2java.reservation.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.how2java.reservation.dao.AdminDAO;
import com.how2java.reservation.dao.AdminRoleDAO;
import com.how2java.reservation.dao.RoleDAO;
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.pojo.AdminRole;
import com.how2java.reservation.pojo.Role;
import com.how2java.reservation.pojo.User;
import com.how2java.reservation.util.Page4Navigator;
@Service
public class RoleService {
	@Autowired RoleDAO roleDAO;
	@Autowired AdminDAO adminDAO;
	@Autowired AdminRoleDAO adminRoleDAO;
	public Set<String> listRoleNames(String adminName){
		List<Admin> cs=adminDAO.findByName(adminName);
		List<AdminRole> cs1=adminRoleDAO.finByUid(cs.get(0).getId());
		List<Role> cs2=roleDAO.findById(cs1.get(0).getRid());
		Set<String> rs = new HashSet<>();
		for (Role c : cs2) {
			rs.add(c.getName());
        }
	    return rs;
	}

	public List<Role> listRoles(String adminName){
		List<Admin> cs=adminDAO.findByName(adminName);
		List<AdminRole> cs1=adminRoleDAO.finByUid(cs.get(0).getId());
		List<Role> cs2=roleDAO.findById(cs1.get(0).getRid());
		return cs2;
	}

	public List<Role> listRoles(Admin admin){
		List<AdminRole> cs1=adminRoleDAO.finByUid(admin.getId());
		List<Role> cs2=roleDAO.findById(cs1.get(0).getRid());
		return cs2;
	}

	public List<Role> list(){
		List<Role> cs=roleDAO.findAll();
		return cs;
	}

	public void add(Role role){
		roleDAO.save(role);
	}

	public void delete(int id){
		roleDAO.delete(id);
	}

	public Role get(int id){
		Role r= roleDAO.findOne(id);
		return r;
	}

	public void update(Role role){
		roleDAO.save(role);
	}
}
