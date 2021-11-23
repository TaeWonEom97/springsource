package com.company.app;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.company.domain.BookDTO;
import com.company.service.BookService;

public class BookClient {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");

		//서비스 호출
		BookService service = (BookService) ctx.getBean("bookServiceimpl");
		
		//새로운 도서 입력
//		BookDTO insertBookDTO = new BookDTO("1011", "스프링3", "구멍가게2", 320000);
//		System.out.println(service.insertBook(insertBookDTO)?"입력성공":"입력실패");
//		System.out.println(service.updateBook("1009", 5000)?"수정성공":"수정실패");
//		System.out.println(service.deleteBook("1010")?"삭제성공":"삭제실패");
		
		//전체 리스트 결과 호출 
		List<BookDTO> list = service.getList();
		System.out.println("코드	제목		작가	가격"	);
		System.out.println("---------------------------------------");
		for(BookDTO book:list) {
			System.out.print(book.getCode()+"\t");
			System.out.print(book.getTitle()+"\t");
			System.out.print(book.getWriter()+"\t");
			System.out.print(book.getPrice()+"\n");
		}
	}

}
