package com.how2java.reservation.web;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
public class AdminPageController {
    @GetMapping(value="/admin")
    public String admin(){
        return "redirect:admin_user_list";
    }
    @GetMapping(value="/admin_user_list")
    public String listUser(){
        return "admin/listUser";
    }
}