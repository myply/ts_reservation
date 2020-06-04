package com.how2java.reservation.web;
 
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.pojo.AdminRole;
import com.how2java.reservation.pojo.Reservation;
import com.how2java.reservation.pojo.ReservationInfo;
import com.how2java.reservation.pojo.Teacher;
import com.how2java.reservation.pojo.User;
import com.how2java.reservation.service.AdminRoleService;
import com.how2java.reservation.service.AdminService;
import com.how2java.reservation.service.ReservationService;
import com.how2java.reservation.service.TeacherService;
import com.how2java.reservation.service.UserService;
import com.how2java.reservation.util.Page4Navigator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
  

@RestController
public class ReservationController {
  @Autowired TeacherService teacherService;
  @Autowired AdminRoleService adminRoleService;
  @Autowired AdminService adminService;
  @Autowired ReservationService reservationService;
  @Autowired UserService userService;
  @GetMapping("/reservations")
  public Page4Navigator<ReservationInfo> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
      start = start<0?0:start;
      Page4Navigator<Reservation> page =reservationService.list(start, size, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
      List<Reservation> content=page.getContent();
      List<ReservationInfo> infoContent=new ArrayList<>(); 
      for(Reservation r:content){
    	  ReservationInfo temp=new ReservationInfo(r);
    	  temp.setUser(userService.get(r.getUid()).getName());
    	  temp.setTeacher(teacherService.get(r.getTid()).getName());
    	  infoContent.add(temp);
      }
      Page4Navigator<ReservationInfo> infoPage=new Page4Navigator(); 
      infoPage.setContent(infoContent);
      return infoPage;
  }
  //特性getMapping不支持@RequestBody
  @GetMapping("/onesReservations")
  public Page4Navigator<ReservationInfo> listOnes(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "id", defaultValue = "1") int id) throws Exception {
	  Subject subject = SecurityUtils.getSubject();
	  String userName =(String)subject.getSession().getAttribute("userName");
//	  String userName = (String)request.getSession().getAttribute("user");	 
	  Admin bean=adminService.getByName(userName);
	  
	  start = start<0?0:start;
      Page4Navigator<Reservation> page =reservationService.list(start, size, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
      List<Reservation> content=page.getContent();
      List<ReservationInfo> infoContent=new ArrayList<>(); 
      for(Reservation r:content){
    	  if(userService.get(r.getUid()).getAid()==bean.getId()){
    		  ReservationInfo temp=new ReservationInfo(r);
        	  temp.setUser(userService.get(r.getUid()).getName());
        	  temp.setTeacher(teacherService.get(r.getTid()).getName());
        	  infoContent.add(temp);
    	  }
      }
      Page4Navigator<ReservationInfo> infoPage=new Page4Navigator(); 
      infoPage.setContent(infoContent);
      return infoPage;
  }
//  REST 规范
  @PostMapping("/reservations")
  public Object add(Teacher bean,Admin admin,HttpServletRequest request) throws Exception {
	  adminService.add(admin);
	  AdminRole ar=new AdminRole();
	  ar.setRid(2);
	  int id=adminService.getByName(admin.getName()).getId();
	  ar.setUid(id);
	  bean.setAid(id);
	  teacherService.add(bean);
	  adminRoleService.add(ar);
      return bean;
  }
  @DeleteMapping("/deleteMyReservation/{id}")
  public String deleteOnes(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
	  reservationService.delete(id);
      return null;
  }
  @DeleteMapping("/reservations/{id}")
  public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
	  reservationService.delete(id);
      return null;
  }
  @PostMapping("/reservations/{id}")
  public Object update(@PathVariable("id") int id,HttpServletRequest request) throws Exception {
      System.out.print("reserve ok\n");
	  Reservation bean=reservationService.get(id);
      bean.setStatus("yes");
      reservationService.update(bean);
      return bean;
  }

}