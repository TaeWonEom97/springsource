package com.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.domain.MemberDTO;
import com.company.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	//로그인
	@GetMapping("/signin")
	public void signin() {
		log.info("로그인 페이지 요청");
	}
	
	//LoginPost
	@PostMapping("/signin")
	public String Login(LoginDTO loginDto,HttpSession session) {
		log.info(loginDto);
		loginDto=service.login(loginDto);
		session.setAttribute("loginDto",loginDto);
		return "redirect:/";
	}
	
	//Logout
	@GetMapping("/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
		
	}
	
	//changePwd
	@GetMapping("/changePwd")
	public void changePwd() {
		log.info("changePwd.jsp 요청");
	}
	
	//changePwd(POST)+폼에서 넘기는 값 가져오기(changeDTO)
	@PostMapping("/changePwd")
	public String changePwdPost(ChangeDTO changeDto,HttpSession session) {
		log.info("비밀번호 변경 요청"+changeDto);
		//비밀번호 변경 요청
		//1) userid 가져오기
		LoginDTO loginDto = (LoginDTO) session.getAttribute("loginDto");
		changeDto.setUserid(loginDto.getUserid());
		if(service.ChangePwd(changeDto)) {
			//비밀번호 변경이 되면 기존의 세션 해제
			session.invalidate();
			return "redirect:/member/signin";
		}
		//변경 실패시 changePwd보여주기
		return "redirect:/member/changePwd";
	
	}
	//회원탈퇴 폼 보여주기
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("회원탈퇴 요청");
	}
	
	@PostMapping("/leave")
	public String leavePost(String userid,String password,HttpSession session) {
		log.info("탈퇴요청"+userid+password);
		if(service.deleteUser(userid, password)) {
			session.invalidate();
			return "redirect:/";
		}	
		return "redirect:/member/leave";
		
	}
}
