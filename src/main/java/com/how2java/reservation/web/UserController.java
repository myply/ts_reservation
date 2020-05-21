package com.how2java.reservation.web;
 
import com.how2java.reservation.pojo.User;
import com.how2java.reservation.service.UserService;
import com.how2java.reservation.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
  

@RestController
public class UserController {
  @Autowired UserService userService;

  @GetMapping("/users")
  public Page4Navigator<User> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
      start = start<0?0:start;
      Page4Navigator<User> page =userService.list(start, size, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
      return page;
  }
//  REST 规范
  @PostMapping("/users")
  public Object add(User bean, HttpServletRequest request) throws Exception {
	  userService.add(bean);
      return bean;
  }

}