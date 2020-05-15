package com.how2java.reservation.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.how2java.reservation.dao.AdminDAO;
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.util.Page4Navigator;
public class AdminService {
	@Autowired AdminDAO adminDAO;
	 
    public Page4Navigator<Admin> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA =adminDAO.findAll(pageable);
 
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
    public List<Admin> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return adminDAO.findAll(sort);
    }
}
