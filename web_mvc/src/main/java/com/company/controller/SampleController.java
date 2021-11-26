package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.UserDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller //객체 생성
@RequestMapping("/sample/*")
public class SampleController {

	@RequestMapping("/basic")
	public void basic() {
		log.info("/basic 요청.......");
	}
	
	// 기본 GET / POST 둘다 응답
//	@RequestMapping("/login")
//	public String login() {
//		log.info("/login 요청.......");
//		return "login";
//	}
	//GET 방식 응답
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		log.info("/login 요청.......");
		//view 리졸버
		return "login";
	}
	
	//POST 방식 응답
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String loginPost(String userid,@RequestParam("pwd") String password,Model model) {
//		log.info("/login Post 요청");
//		
//		log.info("userid : "+userid);
//		log.info("password : "+password);
//		
//		model.addAttribute("userid",userid); //request.setAttribute와 같다.
//		model.addAttribute("password",password); //request.setAttribute와 같다.
//		
//		return "/sample/basic";
//	}
	
	//POST 방식 응답
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPost(@ModelAttribute("login") UserDTO userDto) {
		log.info("/login Post 요청");
		
		log.info("userid : "+userDto.getUserid());
		log.info("password : "+userDto.getPassword());
		log.info("name : "+userDto.getName());
		
		return "/sample/basic";
	}
	
	@RequestMapping(value="/info", method=RequestMethod.GET)
	public void method1() {
		log.info("/sample/info 요청..");
		//view resolver : /sample/info
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String method2() {
		log.info("/sample/admin 요청..");
		return "admin";
	}
}
