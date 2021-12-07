package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;
import com.company.domain.ReplyPageDTO;
import com.company.service.ReplySerivce;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {
	@Autowired
	private ReplySerivce service;
	
	@PostMapping("/new")
	public ResponseEntity<String> create(@RequestBody ReplyDTO replyDto){
		log.info("댓글입력"+replyDto);
		return service.insert(replyDto)?
				new ResponseEntity<String>("success",HttpStatus.OK):
					new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
	}
	
	// /replies/{rno} + GET => return 댓글내용(JSON)
	@GetMapping("/{rno}")
	public ResponseEntity<ReplyDTO> select(@PathVariable int rno){
		log.info("댓글 확인"+rno);
		return new ResponseEntity<ReplyDTO>(service.select(rno),HttpStatus.OK);
	}
	
	@PutMapping("/{rno}")
	public ResponseEntity<String> update(@PathVariable int rno,@RequestBody ReplyDTO updateDto){
		log.info("댓글 수정"+updateDto);
		//updateDto rno 세팅하기
		updateDto.setRno(rno);
		
		return service.update(updateDto)?
				new ResponseEntity<String>("success",HttpStatus.OK):
					new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> delete(@PathVariable int rno){
		log.info("댓글 삭제"+rno);
		return service.delete(rno)?
				new ResponseEntity<String>("success",HttpStatus.OK):
					new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
	}
	
	//댓글 전체 가져오기
	// /replies/page/{bno}/{page} + GET
	@GetMapping("/pages/{bno}/{page}")
	public ResponseEntity<ReplyPageDTO> list(@PathVariable int bno,@PathVariable int page){
		log.info("댓글 전체 가져오기"+bno);
		Criteria cri = new Criteria(page, 10);
		return new ResponseEntity<ReplyPageDTO>(service.list(cri,bno),HttpStatus.OK);
	}
}
