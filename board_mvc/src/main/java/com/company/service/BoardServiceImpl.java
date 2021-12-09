package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.AttachFileDTO;
import com.company.domain.BoardDTO;
import com.company.domain.Criteria;
import com.company.mapper.BoardAttachMapper;
import com.company.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private BoardAttachMapper boardAttachMapper;
	
	@Transactional
	@Override
	public boolean insert(BoardDTO boardDto) {		
		//게시물 등록
		mapper.insert(boardDto);
		
		//첨부파일 등록
		if(boardDto.getAttachList()==null || boardDto.getAttachList().size() <= 0) {
			return false;
		}
		boardDto.getAttachList().forEach(attach->{
			attach.setBno(boardDto.getBno());
			boardAttachMapper.insert(attach);
		});
		
		return true;
	}

	@Override
	public List<BoardDTO> select(Criteria cri) {
		return mapper.select(cri);
	}

	@Override
	public BoardDTO read(int bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean update(BoardDTO updateDto) {
		return mapper.update(updateDto)>0?true:false;
	}

	@Override
	public boolean remove(int bno) {
		return mapper.remove(bno)>0?true:false;
	}

	@Override
	public int getTotalCount(Criteria cri) {
		return mapper.totalCnt(cri);
	}

	@Override
	public List<AttachFileDTO> findByBno(int bno) {
		return boardAttachMapper.read(bno);
	}
	
	

}
