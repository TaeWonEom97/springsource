package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.MemberDTO;
import com.company.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/register/*")
@Controller
public class RegisterController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/step1")
	public void registGet() {
		log.info("step1 요청");
	}
	
	@PostMapping("/step2")
	public String step2(boolean agree,RedirectAttributes rttr) {
		log.info("step2 요청"+agree);
		if(!agree) {
			//step1보여주기
			rttr.addFlashAttribute("check","false");
			return "redirect:/register/step1";
		}
		return "/register/step2";
	}
	
	//중복아이디 검사
	@ResponseBody //return 하는 값이 데이터임
	@PostMapping("/checkId")
	public String IdCheck(String userid) {
		log.info("중복아이디 검사"+userid);
		if(service.dupId(userid)!=null) {
			return "false";
		}
		return "true";
	}
	
	
	@PostMapping("/step3")
	public String step3(MemberDTO memberDto) {
		log.info("회원가입 요청"+memberDto);
		try {
			if(!service.register(memberDto)) {
				//회원가입페이지로 이동
				return "/register/step2";
			}
		} catch (Exception e) {
			//회원가입페이지로 이동
			return "/register/step2";
		}
		
		return "redirect:/member/signin";
	}
	
	

	//step3,2+GET => 405 - 허용되지 않는 메세지
	@GetMapping(value= {"/step2","/step3"})
	public String handleGet() {
		log.info("/step2,/step3 직접요청");
		return "redirect:/";
	}

	
		
}
