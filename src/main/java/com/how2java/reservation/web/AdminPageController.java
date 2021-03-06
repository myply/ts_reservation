package com.how2java.reservation.web;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
public class AdminPageController {
    @GetMapping(value="/admin")
    public String admin(){
        return "redirect:admin_user_list";
    }
    @GetMapping(value="/student")
    public String student(){
        return "admin/studentReservation";
    }
    @GetMapping(value="/myReservation")
    public String myReservation(){
        return "admin/myReservation";
    }
    @GetMapping(value="/admin_user_list")
    public String listUser(){
        return "admin/listUser";
    }
    @GetMapping(value="/admin_user_edit")
    public String editUser(){
        return "admin/editUser";
 
    }
    @GetMapping(value="/admin_teacher_list")
    public String listTeacher(){
        return "admin/listTeacher";
 
    }
    @GetMapping(value="/admin_teacher_edit")
    public String editTeacher(){
        return "admin/editTeacher";
 
    }
    @GetMapping(value="/admin_reservation_list")
    public String listReservation(){
        return "admin/listReservation";
 
    }
}