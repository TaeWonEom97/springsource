package com.company.service;

import java.util.List;

import com.company.domain.ReplyDTO;

public interface ReplySerivce {
	public boolean insert(ReplyDTO replyDto);
	public ReplyDTO select(int rno);
	public boolean update(ReplyDTO updateDto);
	public boolean delete(int rno);
	public List<ReplyDTO> list(int bno);
}
