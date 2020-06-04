package com.how2java.reservation.web;
 
import com.how2java.reservation.pojo.AddUser;
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.pojo.AdminRole;
import com.how2java.reservation.pojo.Teacher;
import com.how2java.reservation.pojo.User;
import com.how2java.reservation.service.AdminRoleService;
import com.how2java.reservation.service.AdminService;
import com.how2java.reservation.service.UserService;
import com.how2java.reservation.util.Page4Navigator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
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
public class UserController {
  @Autowired UserService userService;
  @Autowired AdminService adminService;
  @Autowired AdminRoleService adminRoleService;
  @GetMapping("/users")
  public Page4Navigator<User> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
      start = start<0?0:start;
//      System.out.print("user controller test\n");
      Page4Navigator<User> page =userService.list(start, size, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
      return page;
  }
//  REST 规范
  @GetMapping("getadmin")
  public Admin getAdmin(HttpServletRequest request) throws Exception {
	  Subject subject = SecurityUtils.getSubject();
	  String userName =(String)subject.getSession().getAttribute("userName");
//	  String userName = (String)request.getSession().getAttribute("user");	 
	  Admin bean=adminService.getByName(userName);
//	  System.out.print("\n获取使用者"+bean.getName()+"\n");
      return bean;
  }
  @PostMapping("/users")
  public Object add(AddUser bean,HttpServletRequest request) throws Exception {
	  System.out.print("add测试\n");
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
	  
	  User user=new User();
	  user.setName(bean.getName());
	  user.setAid(id);
	  userService.add(user);
	  
	  AdminRole ar=new AdminRole();
	  ar.setRid(3);
	  ar.setUid(id);
	  adminRoleService.add(ar);
      return user;
	  
  }
  @DeleteMapping("/users/{id}")
  public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
	  User user=userService.get(id);
	  adminRoleService.deleteByUser(user.getAid());
	  adminService.delete(user.getAid());
	  userService.delete(id);
      return null;
  }
  @GetMapping("/users/{id}")
  public User get(@PathVariable("id") int id) throws Exception {
	  User bean=userService.get(id);
      return bean;
  }
  @PostMapping("/users/{id}")
  public Object update(User bean, HttpServletRequest request) throws Exception {
      String name = request.getParameter("name");
//      System.out.print("bean.name：");  
//      System.out.print(name);  
//      System.out.print("name is done");
      bean.setName(name);
      userService.update(bean);

      return bean;
  }

}