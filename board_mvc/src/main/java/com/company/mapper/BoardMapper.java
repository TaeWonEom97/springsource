package com.company.mapper;

import java.util.List;

import com.company.domain.BoardDTO;

public interface BoardMapper {
	public int insert(BoardDTO boardDto);
	public List<BoardDTO> select();
	public BoardDTO read(int bno);
	public int update(BoardDTO updateDto);
	public int remove(int bno);
}
