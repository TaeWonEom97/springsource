package com.company.app;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.company.domain.BookDTO;
import com.company.service.BookService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BookClient {
	private static BookService service;
	
	public static void main(String[] args) {
		
		log.info("main 시작");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");

		//서비스 호출
		service = (BookService) ctx.getBean("bookServiceimpl");
		menu();
		
	}
	
	public static void menu() {
		boolean flag = true;
		
		while(flag) {
			System.out.println("-------------------------------");
			System.out.println("1.도서 정보 조회");
			System.out.println("2.도서 정보 추가");
			System.out.println("3.도서 정보 삭제");
			System.out.println("4.도서 목록 전체조회");
			System.out.println(  "5.도서 목록 수정");
			System.out.println("6.종료");
			System.out.println("-------------------------------");
			
			Scanner sc = new Scanner(System.in);
			System.out.println("메뉴 선택 >> ");
			int no = Integer.parseInt(sc.next());
			
			System.out.println();
			
			switch (no) {
			case 1:
				System.out.println("조회할 도서 코드 입력 >>");
				String code = sc.next();
				BookDTO dto=service.getRow(code);
				System.out.print(dto.getCode()+"\t");
				System.out.print(dto.getTitle()+"\t");
				System.out.print(dto.getWriter()+"\t");
				System.out.print(dto.getPrice()+"\n");
				break;
				
			case 2:
				System.out.println("코드를 입력하시오");
				String code1 = sc.next();
				System.out.println("책 제목을 입력하시오");
				String title = sc.next();
				System.out.println("저자를 입력하시오");
				String writer = sc.next();
				System.out.println("가격을 입력하시오");
				int price = sc.nextInt();
				BookDTO insertBookDTO = new BookDTO(code1, title, writer, price);
				System.out.println(service.insertBook(insertBookDTO)?"입력성공":"입력실패");
				break;
				
			case 3:
				System.out.println("삭제할 책 코드를 입력하시오");
				String code2 = sc.next();
				System.out.println(service.deleteBook(code2)?"삭제성공":"삭제실패");
				break;
				
			case 4:
				List<BookDTO> list = service.getList();
				System.out.println("코드	제목		작가	가격"	);
				System.out.println("---------------------------------------");
				for(BookDTO book:list) {
					System.out.print(book.getCode()+"\t");
					System.out.print(book.getTitle()+"\t");
					System.out.print(book.getWriter()+"\t");
					System.out.print(book.getPrice()+"\n");
				}
				break;
				
			case 5:
				System.out.println("수정할 책 코드를 입력하시오");
				String code3=sc.next();
				System.out.println("수정할 가격을 입력하시오");
				int price2=sc.nextInt();
				System.out.println(service.updateBook(code3,price2)?"수정성공":"수정실패");
				break;
				
			case 6:
				flag=false;
				break;
			}
		}
	}

}
