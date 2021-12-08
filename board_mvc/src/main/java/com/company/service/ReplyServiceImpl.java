package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;
import com.company.domain.ReplyPageDTO;
import com.company.mapper.BoardMapper;
import com.company.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplySerivce {
	
	@Autowired
	private ReplyMapper mapper;
	@Autowired
	private BoardMapper boardmapper;

	@Transactional
	@Override
	public boolean insert(ReplyDTO replyDto) {
		boardmapper.updateReplyCnt(replyDto.getBno(), 1);
		return mapper.insert(replyDto)>0?true:false ;
	}

	@Override
	public ReplyDTO select(int rno) {
		return mapper.select(rno);
	}

	@Override
	public boolean update(ReplyDTO updateDto) {
		return mapper.update(updateDto)>0?true:false;
	}
	@Transactional
	@Override
	public boolean delete(int rno) {
		ReplyDTO reply=mapper.select(rno);
		boardmapper.updateReplyCnt(reply.getBno(), -1);
		return mapper.delete(rno)>0?true:false;
	}

	@Override
	public ReplyPageDTO list(Criteria cri,int bno) {
		return new ReplyPageDTO(mapper.getCountByBno(bno),mapper.list(cri, bno));
	}



}
