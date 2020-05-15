package com.how2java.reservation.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.how2java.reservation.dao.RoleDAO;
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.pojo.User;
import com.how2java.reservation.util.Page4Navigator;
@Service
public class RoleService {
	@Autowired RoleDAO roleDAO;
	public Set<String> listRoleNames(String userName);

	public List<Role> listRoles(String userName);

	public List<Role> listRoles(User user);

	public List<Role> list();

	public void add(Role role);

	public void delete(Long id);

	public Role get(Long id);

	public void update(Role role);
}
