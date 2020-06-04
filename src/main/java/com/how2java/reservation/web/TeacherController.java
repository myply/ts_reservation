package com.how2java.reservation.web;
 
import com.how2java.reservation.pojo.AddTeacher;
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.pojo.AdminRole;
import com.how2java.reservation.pojo.Teacher;
import com.how2java.reservation.service.AdminRoleService;
import com.how2java.reservation.service.AdminService;
import com.how2java.reservation.service.TeacherService;
import com.how2java.reservation.util.Page4Navigator;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
  

@RestController
public class TeacherController {
  @Autowired TeacherService teacherService;
  @Autowired AdminRoleService adminRoleService;
  @Autowired AdminService adminService;

  @GetMapping("/teachers")
  public Page4Navigator<Teacher> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
      start = start<0?0:start;
      
      Page4Navigator<Teacher> page =teacherService.list(start, size, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
      return page;
  }
//  REST 规范
  @PostMapping("/teachers")
  public Object add(AddTeacher bean,HttpServletRequest request) throws Exception {
	  Admin admin=new Admin();
	  String salt = new SecureRandomNumberGenerator().nextBytes().toString();
      int times = 2;
      String algorithmName = "md5";
      String encodedPassword = new SimpleHash(algorithmName,bean.getPassword(),salt,times).toString();
      
      admin.setName(bean.getAccount());
      admin.setSalt(salt);
	  admin.setPassword(encodedPassword);
	  adminService.add(admin);
	  
	  int id=adminService.getByName(admin.getName()).getId();
	  
	  Teacher teacher=new Teacher();
	  teacher.setName(bean.getName());
	  teacher.setDid(bean.getDid());
	  teacher.setAid(id);
	  teacherService.add(teacher);
	  
	  AdminRole ar=new AdminRole();
	  ar.setRid(2);
	  ar.setUid(id);
	  adminRoleService.add(ar);
      return teacher;
  }
  @DeleteMapping("/teachers/{id}")
  public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
	  Teacher teacher=teacherService.get(id);
	  adminRoleService.deleteByUser(teacher.getAid());
	  adminService.delete(teacher.getAid());
	  teacherService.delete(id);
      return null;
  }
  @GetMapping("/teachers/{id}")
  public Teacher get(@PathVariable("id") int id) throws Exception {
	  Teacher bean=teacherService.get(id);
      return bean;
  }
  @PostMapping("/teachers/{id}")
  public Object update(@PathVariable("id") int id,HttpServletRequest request) throws Exception {
      String name = request.getParameter("name");
      Teacher teacher=teacherService.get(id);
      teacher.setName(name);
      teacherService.update(teacher);
      return teacher;
  }

}