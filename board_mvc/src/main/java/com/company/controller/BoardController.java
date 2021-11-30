package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardDTO;
import com.company.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// /board/register.jsp
	@GetMapping("/register")
	public void registerGet() {
		log.info("register요청");
	}
	
	@PostMapping("/register")
	public String registerPost(BoardDTO boardDto,RedirectAttributes rttr) {
		log.info("register"+boardDto); 
		service.insert(boardDto);
//		log.info("bno "+boardDto.getBno());
		rttr.addFlashAttribute("result",boardDto.getBno());
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("전체 리스트 요청");
		List<BoardDTO> list = service.select();
		model.addAttribute("list",list);
	}
	
	@GetMapping({"/read","/modify"})
	public void titleGet(int bno, Model model) {
		BoardDTO dto = service.read(bno);
		model.addAttribute("dto",dto);
		log.info("read or modify요청");
	}
	
	@PostMapping("/remove")
	public String removeGet(int bno) {
		log.info("remove 요청"+bno);
		service.remove(bno);
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modifyPost(BoardDTO boardDto) {
		log.info("modify 요청"+boardDto);
		service.update(boardDto);
		return "redirect:/board/list";
		
	}
	

}
