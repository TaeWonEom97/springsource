package com.company.mapper;

import org.apache.ibatis.annotations.Param;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.domain.MemberDTO;

public interface MemberMapper {
	public int insert(MemberDTO memberDto);
	public MemberDTO selectById(String userid);
	public LoginDTO login(LoginDTO loginDto);
	public int update(ChangeDTO changeDto);
	public int delete(@Param("userid")String userid, @Param("password")String password);
	
}
