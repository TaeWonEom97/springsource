package com.company.mapper;

import java.util.List;

import com.company.domain.BoardDTO;
import com.company.domain.Criteria;

public interface BoardMapper {
	public int insert(BoardDTO boardDto);
	public List<BoardDTO> select(Criteria cri);
	public BoardDTO read(int bno);
	public int update(BoardDTO updateDto);
	public int remove(int bno);
	public int totalCnt(Criteria cri);
}
