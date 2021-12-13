package com.company.service;

import java.util.List;

import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;
import com.company.domain.ReplyPageDTO;

public interface ReplySerivce {
	public boolean insert(ReplyDTO replyDto);
	public ReplyDTO select(int rno);
	public boolean update(ReplyDTO updateDto);
	public boolean delete(int rno);
	public ReplyPageDTO list(Criteria cri,int bno);
}
