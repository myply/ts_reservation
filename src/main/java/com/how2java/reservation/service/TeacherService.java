package com.how2java.reservation.service;
 
import java.util.List;
 
import com.how2java.reservation.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.how2java.reservation.dao.TeacherDAO;
import com.how2java.reservation.dao.UserDAO;
import com.how2java.reservation.pojo.Teacher;
 
@Service
public class TeacherService {
    @Autowired TeacherDAO teacherDAO;
 
    public Page4Navigator<Teacher> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA =teacherDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
    public List<Teacher> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return teacherDAO.findAll(sort);
    }
	public void add(Teacher bean) {
		teacherDAO.save(bean);
	}

	public Teacher get(int id) {
		Teacher c= teacherDAO.findOne(id);
		return c;
	}
	public void update(Teacher bean) {
		teacherDAO.save(bean);
	}
	public void delete(int id) {
		teacherDAO.delete(id);
    }
}