package com.company.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.domain.UserDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller //객체 생성
public class SampleController2 {

	@RequestMapping("/member/basic")
	public void basic() {
		log.info("/member/basic 요청.......");
	}
	
	// 기본 GET / POST 둘다 응답
//	@RequestMapping("/login")
//	public String login() {
//		log.info("/login 요청.......");
//		return "login";
//	}
	//GET 방식 응답
//	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	@GetMapping("/member/login")
	public String login() {
		log.info("/member/login 요청.......");
		//view 리졸버
		return "login";
	}
	
	//POST 방식 응답
//	@RequestMapping(value="/member/login", method=RequestMethod.POST)
//	@PostMapping("/member/login")
//	public String loginPost() {
//		log.info("/member/login Post 요청");
//		return "/sample/basic";
//	}
	
	//POST 방식 응답 + 사용자의 입력값 가져오기
	//request 객체 사용 - HttpServletRequest 이용
//	@RequestMapping(value="/member/login", method=RequestMethod.POST)
//	@PostMapping("/member/login")
//	public String loginPost(HttpServletRequest req) {
//		log.info("/member/login Post 요청");
//		
//		log.info("userid : "+req.getParameter("userid"));
//		log.info("password : "+req.getParameter("password"));
//		
//		return "/sample/basic";
//	}
	
	//바인딩 변수 사용
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String loginPost(String userid, String password) {
		log.info("/member/login Post 요청");
		
		log.info("userid : "+userid);
		log.info("password : "+password);
		
		return "/sample/basic";
	}
	
	// 바인딩 객체 사용
//	@RequestMapping(value="/member/login", method=RequestMethod.POST)
//	public String loginPost(UserDTO userDto) {
//		log.info("/member/login Post 요청");
//		
//		log.info("userid : "+userDto.getUserid());
//		log.info("password : "+userDto.getPassword());
//		
//		return "/sample/basic";
//	}
	
//	@RequestMapping(value="/admin/info", method=RequestMethod.GET)
	@GetMapping("/admin/info")
	public void method1() {
		log.info("/admin/info 요청..");
		//view resolver : /sample/info
	}
	
//	@RequestMapping(value="/admin", method=RequestMethod.GET)
	@GetMapping("/admin")
	public String method2() {
		log.info("/admin 요청..");
		return "admin";
	}
}
