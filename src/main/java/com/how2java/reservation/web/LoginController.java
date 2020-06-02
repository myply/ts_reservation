package com.how2java.reservation.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, String name, String password) {
//		System.out.println("login post方式运行到此" );
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		try {
			subject.login(token);
			Session session = subject.getSession();
			session.setAttribute("subject", subject);
//			System.out.println("验证失败，运行到此" );
			return "redirect:index";

		} catch (AuthenticationException e) {
//			System.out.println("验证失败，运行到此" );
			model.addAttribute("error", "验证失败");
			return "login";
//			return "admin/listUser";
		}
	}

}
