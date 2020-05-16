package com.how2java.reservation.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.how2java.reservation.dao.AdminDAO;
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.pojo.User;
import com.how2java.reservation.util.Page4Navigator;
@Service
public class AdminService {
	@Autowired AdminDAO adminDAO;
	public String getPassword(String name){
		List<Admin> cs=adminDAO.findByName(name);
		return cs.get(0).getPassword();
	}

	public Admin getByName(String name){
		List<Admin> cs=adminDAO.findByName(name);
		return cs.get(0);
	}

	public List<Admin> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return adminDAO.findAll(sort);
    }
	public void add(Admin admin){
		adminDAO.save(admin);
	}

	public void delete(int id){
		adminDAO.delete(id);
	}

	public Admin get(int id){
		Admin a= adminDAO.findOne(id);
		return a;
	}

	public void update(Admin admin){
		adminDAO.save(admin);
	}
}
