package com.how2java.reservation.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//专门用于显示页面的控制器
@Controller
//@RestController
public class PageController {

//	@RequestMapping("index")
//	public String index() {
//		return "index";
//	}
    @RequestMapping("index")
    public void index( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		System.out.println("login get方式运行到此" );
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }
  
	// @RequiresPermissions("deleteOrder")
	@RequestMapping("deleteOrder")
	public String deleteOrder() {
		return "deleteOrder";
	}

	// @RequiresRoles("productManager")
	@RequestMapping("deleteProduct")
	public String deleteProduct() {
		return "deleteProduct";
	}

	@RequestMapping("listProduct")
	public String listProduct() {
		return "listProduct";
	}


    @RequestMapping(value = "/login", method =RequestMethod.GET)
    public void login( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		System.out.println("login get方式运行到此" );
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }
    
	@RequestMapping("unauthorized")
	public String noPerms() {
		return "unauthorized";
	}

}
