package com.company.app;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BoardDTO;
import com.company.service.BoardService;

public class BoardClient {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		BoardService service = (BoardService) ctx.getBean("boardService");
		
		//입력
//		BoardDTO insertDto = new BoardDTO();
//		insertDto.setTitle("스프링 프레임워크3");
//		insertDto.setContent("스프링 프레임워크 게시판3");
//		insertDto.setWriter("홍길동3");
////		
//		System.out.println(service.insertBoard(insertDto)?"입력성공":"입력실패");
		
		//전체 검색
		List<BoardDTO> list = service.getRows();
		System.out.println("------------------------------------------------------------");
		System.out.println("bno\t title\t content\t writer\t regdate\t updatedate");		
		System.out.println("------------------------------------------------------------");
		for(BoardDTO dto:list) {
			System.out.print(dto.getBno()+"\t");
			System.out.print(dto.getTitle()+"\t");
			System.out.print(dto.getContent()+"\t");
			System.out.print(dto.getWriter()+"\t");
			System.out.print(dto.getRegdate()+"\t");
			System.out.print(dto.getUpdatedate()+"\n");
		}
		
		//하나 검색
//		BoardDTO dto = service.getRow(4);
//		System.out.print(dto.getBno()+"\t");
//		System.out.print(dto.getTitle()+"\t");
//		System.out.print(dto.getContent()+"\t");
//		System.out.print(dto.getWriter()+"\t");
//		System.out.print(dto.getRegdate()+"\t");
//		System.out.print(dto.getUpdatedate()+"\n");
		
		//업데이트
//		BoardDTO updateDto = new BoardDTO();
//		updateDto.setBno(4);
//		updateDto.setTitle("스프링 수정");
//		updateDto.setContent("DI란 무엇인가");
//		System.out.println(service.updateBoard(updateDto)?"수정성공":"수정실패");
		
//		System.out.println(service.deleteBoard(4)?"삭제성공":"삭제실패");

	}
}
