package com.company.mapper;

import java.util.List;

import com.company.domain.ReplyDTO;

public interface ReplyMapper {
	public int insert(ReplyDTO replyDto);
	public ReplyDTO select(int rno);
	public int update(ReplyDTO updateDto);
	public int delete(int rno);
	public List<ReplyDTO> list(int bno);
}
