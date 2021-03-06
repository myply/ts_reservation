package com.how2java.reservation.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.how2java.reservation.pojo.Permission;
import com.how2java.reservation.pojo.Role;
import com.how2java.reservation.service.PermissionService;
import com.how2java.reservation.service.RolePermissionService;
import com.how2java.reservation.service.RoleService;

@Controller
@RequestMapping("config")
public class RoleController {
	@Autowired
	RoleService roleService;
	@Autowired
	RolePermissionService rolePermissionService;
	@Autowired
	PermissionService permissionService;

	@RequestMapping("listRole")
	public String list(Model model) {
		List<Role> rs = roleService.list();
		model.addAttribute("rs", rs);

		Map<Role, List<Permission>> role_permissions = new HashMap<>();

		for (Role role : rs) {
			List<Permission> ps = permissionService.list(role);
			role_permissions.put(role, ps);
		}
		model.addAttribute("role_permissions", role_permissions);

		return "listRole";
	}

	@RequestMapping("editRole")
	public String list(Model model, int id) {
		Role role = roleService.get(id);
		model.addAttribute("role", role);

		List<Permission> ps = permissionService.list();
		model.addAttribute("ps", ps);

		List<Permission> currentPermissions = permissionService.list(role);
		model.addAttribute("currentPermissions", currentPermissions);

		return "editRole";
	}

	@RequestMapping("updateRole")
	public String update(Role role, int[] permissionIds) {
		rolePermissionService.setPermissions(role, permissionIds);
		roleService.update(role);
		return "redirect:listRole";
	}

	@RequestMapping("addRole")
	public String list(Model model, Role role) {
		System.out.println(role.getName());
		System.out.println(role.getDesc_());
		roleService.add(role);
		return "redirect:listRole";
	}

	@RequestMapping("deleteRole")
	public String delete(Model model, int id) {
		roleService.delete(id);
		return "redirect:listRole";
	}

}