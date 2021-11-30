package com.company.service;

import java.awt.print.Book;
import java.util.List;

import com.company.domain.BookDTO;

public interface BookService {
	public List<BookDTO> selectbook();
	public boolean insert(BookDTO bookDto);
	public BookDTO select(String code);
	public boolean delete(String code);
	public boolean update(BookDTO updateDto);
}
