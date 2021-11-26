package com.company.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.domain.AddDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/calc/*")
public class AddController {
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public void add() {
		log.info("/add요청");
	}
	
	@PostMapping("/add")
	public void addPost(AddDTO addDto,Model model) {
		log.info("/calc/add post 요청");
		log.info(addDto.getNum1());
		log.info(addDto.getNum2());
		int result=addDto.getNum1()+addDto.getNum2();	
		model.addAttribute("result",result);
	}
}
