package com.company.mapper;

import java.util.List;

import com.company.domain.BookDTO;

public interface BookMapper {
	public List<BookDTO> selectbook();
	public int insert(BookDTO bookDto);
	public BookDTO select(String code);

}
