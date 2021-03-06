package com.company.service;

import java.util.List;

import com.company.domain.BookDTO;

public interface BookService {
	//전체 도서 목록
	public List<BookDTO> getList();
		
	//새로운 도서 입력
	public boolean insertBook(BookDTO dto);
	
	//도서 정보 업데이트
	public boolean updateBook(String code, int price);
	
	//도서 정보 삭제
	public boolean deleteBook(String code);
	
	//특정 도서 가져오기
	public BookDTO getRow(String code);
}
