package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookDTO;
import com.company.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper mapper;
	
	@Override
	public List<BookDTO> selectbook() {
		return mapper.selectbook();
	}

	@Override
	public boolean insert(BookDTO bookDto) {
		return mapper.insert(bookDto)>0?true:false;
	}

	@Override
	public BookDTO select(String code) {
		return mapper.select(code);
	}

	@Override
	public boolean delete(String code) {
		return mapper.delete(code)>0?true:false;
	}

	@Override
	public boolean update(BookDTO updateDto) {
		return mapper.update(updateDto)>0?true:false;
	}


	
}
