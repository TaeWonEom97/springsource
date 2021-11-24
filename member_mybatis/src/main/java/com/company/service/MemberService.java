package com.company.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.domain.ChangeDTO;
import com.company.domain.MemberDTO;

public interface MemberService {
	public List<MemberDTO> getList();
	public MemberDTO getRow(String userid,String password);
	public boolean updateDto(ChangeDTO changeDto);
	public boolean deleteDto(String userid);
	public boolean insertDto(MemberDTO dto);
}
