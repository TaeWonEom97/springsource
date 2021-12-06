package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.ReplyDTO;
import com.company.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplySerivce {
	
	@Autowired
	private ReplyMapper mapper;

	@Override
	public boolean insert(ReplyDTO replyDto) {
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

	@Override
	public boolean delete(int rno) {
		return mapper.delete(rno)>0?true:false;
	}

	@Override
	public List<ReplyDTO> list(int bno) {
		return mapper.list(bno);
	}

}
