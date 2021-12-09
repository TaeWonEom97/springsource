package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.AttachFileDTO;
import com.company.domain.BoardDTO;
import com.company.domain.Criteria;
import com.company.domain.PageDTO;
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
		
		/*
		 * if(boardDto.getAttachList()!=null) {
		 * boardDto.getAttachList().forEach(attach->log.info(attach+"")); }
		 */
		
		
		service.insert(boardDto);
//		log.info("bno "+boardDto.getBno());
		rttr.addFlashAttribute("result",boardDto.getBno());
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public void list(Model model,Criteria cri) {
		log.info("전체 리스트 요청"+cri);
		List<BoardDTO> list = service.select(cri);
		
		//페이지 나누기를 위한 정보 얻기
		int totalCnt = service.getTotalCount(cri);
		model.addAttribute("pageDto",new PageDTO(cri, totalCnt));
		model.addAttribute("list",list);
	}
	
	@GetMapping({"/read","/modify"})
	public void titleGet(int bno,@ModelAttribute("cri") Criteria cri ,Model model) {
		BoardDTO dto = service.read(bno);
		model.addAttribute("dto",dto);
		log.info("read or modify요청");
	}
	
	@PostMapping("/remove")
	public String removeGet(int bno,Criteria cri,RedirectAttributes rttr) {
		log.info("remove 요청"+bno);
		service.remove(bno);
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		//검색값
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		rttr.addFlashAttribute("result","success");
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modifyPost(BoardDTO boardDto,Criteria cri,RedirectAttributes rttr) {
		log.info("modify 요청"+boardDto+"  "+cri);
		
		service.update(boardDto);
		//페이지 나누기 값
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		//검색값
		rttr.addAttribute("type",cri.getType());
		rttr.addAttribute("keyword",cri.getKeyword());
		rttr.addFlashAttribute("result","success");
		return "redirect:/board/list";
		
	}
	
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachFileDTO>> getAttachList(int bno){
		log.info("파일 첨부가져오기");
		return new ResponseEntity<List<AttachFileDTO>>(service.findByBno(bno),HttpStatus.OK);
	}

}
