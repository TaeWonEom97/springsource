package com.company.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.domain.MemberDTO;
import com.company.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public boolean register(MemberDTO memberDto) {
		return mapper.insert(memberDto)>0?true:false;
	}

	@Override
	public MemberDTO dupId(String userid) {
		return mapper.selectById(userid);
	}

	@Override
	public LoginDTO login(LoginDTO loginDto) {
		return mapper.login(loginDto);
	}

	@Override
	public boolean ChangePwd(ChangeDTO changeDto) {
		return mapper.update(changeDto)>0?true:false;
	}

	@Override
	public boolean deleteUser(LoginDTO loginDto) {
		return mapper.delete(loginDto)>0?true:false;
	}

	


}
