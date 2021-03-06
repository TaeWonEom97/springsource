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
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper mapper;
	@Autowired
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public boolean insertReply(ReplyDTO insertDto) {
		// TODO Auto-generated method stub
		
		boardMapper.updateReplyCnt(insertDto.getBno(), 1);
		
		return mapper.insertReply(insertDto)>0?true:false;
	}

	@Override
	public ReplyDTO getRow(int rno) {
		// TODO Auto-generated method stub
		return mapper.get(rno);
	}

	@Override
	public boolean updateReply(ReplyDTO updateDto) {
		// TODO Auto-generated method stub
		return mapper.updateReply(updateDto)>0?true:false;
	}
	
	@Transactional
	@Override
	public boolean deleteReply(int rno) {
		// TODO Auto-generated method stub
		
		ReplyDTO reply=mapper.get(rno);
		
		boardMapper.updateReplyCnt(reply.getBno(), -1);
		
		return mapper.deleteReply(rno)>0?true:false;
	}

	@Override
	public ReplyPageDTO list(Criteria cri, int bno) {
		// TODO Auto-generated method stub
		return  new ReplyPageDTO(mapper.getCountByBno(bno),mapper.list(cri, bno));
	}

	
}