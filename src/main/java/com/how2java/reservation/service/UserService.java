package com.how2java.reservation.service;
 
import java.util.List;
 
import com.how2java.reservation.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
 
import com.how2java.reservation.dao.UserDAO;
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.pojo.User;
 
@Service
public class UserService {
    @Autowired UserDAO userDAO;
 
    public Page4Navigator<User> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA =userDAO.findAll(pageable);
 
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
    public List<User> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return userDAO.findAll(sort);
    }
	public void add(User bean) {
		userDAO.save(bean);
	}

	public User get(int id) {
		User c= userDAO.findOne(id);
		return c;
	}
	public void update(User bean) {
		userDAO.save(bean);
	}
	public void delete(int id) {
		userDAO.delete(id);
    }

}