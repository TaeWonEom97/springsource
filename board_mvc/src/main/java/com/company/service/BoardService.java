package com.company.service;

import java.util.List;

import com.company.domain.BoardDTO;

public interface BoardService {
	public boolean insert(BoardDTO boardDto);
	public List<BoardDTO> select();
	public BoardDTO read(int bno);
	public boolean update(BoardDTO updateDto);
	public boolean remove(int bno);
}
