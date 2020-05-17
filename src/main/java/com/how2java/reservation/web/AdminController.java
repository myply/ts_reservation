package com.how2java.reservation.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.how2java.reservation.pojo.Role;
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.service.RoleService;
import com.how2java.reservation.service.AdminRoleService;
import com.how2java.reservation.service.AdminService;

@Controller
@RequestMapping("config")
public class AdminController {
	@Autowired
	AdminRoleService adminRoleService;
	@Autowired
	AdminService adminService;
	@Autowired
	RoleService roleService;

	@RequestMapping("listUser")
	public String list(Model model) {
		List<Admin> ad = adminService.list();
		model.addAttribute("us", ad);
		Map<Admin, List<Role>> admin_roles = new HashMap<>();
		for (Admin admin : ad) {
			List<Role> roles = roleService.listRoles(admin);
			admin_roles.put(admin, roles);
		}
		model.addAttribute("admin_roles", admin_roles);

		return "listUser";
	}

	@RequestMapping("editUser")
	public String edit(Model model, int id) {
		List<Role> rs = roleService.list();
		model.addAttribute("rs", rs);
		Admin admin = adminService.get(id);
		model.addAttribute("admin", admin);

		List<Role> roles = roleService.listRoles(admin);
		model.addAttribute("currentRoles", roles);

		return "editUser";
	}

	@RequestMapping("deleteUser")
	public String delete(Model model, int id) {
		adminService.delete(id);
		return "redirect:listUser";
	}

	@RequestMapping("updateUser")
	public String update(Admin admin, int[] roleIds) {
		adminRoleService.setRoles(admin, roleIds);

		String password = admin.getPassword();
		// 如果在修改的时候没有设置密码，就表示不改动密码
		if (admin.getPassword().length() != 0) {
			String salt = new SecureRandomNumberGenerator().nextBytes().toString();
			int times = 2;
			String algorithmName = "md5";
			String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();
			admin.setSalt(salt);
			admin.setPassword(encodedPassword);
		} else
			admin.setPassword(null);

		adminService.update(admin);

		return "redirect:listUser";

	}

	@RequestMapping("addUser")
	public String add(Model model, String name, String password) {

		String salt = new SecureRandomNumberGenerator().nextBytes().toString();
		int times = 2;
		String algorithmName = "md5";

		String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();

		Admin a = new Admin();
		a.setName(name);
		a.setPassword(encodedPassword);
		a.setSalt(salt);
		adminService.add(a);

		return "redirect:listUser";
	}

}