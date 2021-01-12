package com.testProject.service;

import java.util.List;

import com.testProject.dao.UserDao;
import com.testProject.dto.JoinReqDto;
import com.testProject.dto.UserDto;

public class UserService {
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public UserDto 로그인(String username, String password) {
		
		return userDao.login(username,password) ;
	}

	public int 회원가입(JoinReqDto dto) {
		return userDao.join(dto);
	}

	public List<UserDto> 목록보기() {
		
		return userDao.findAll();
	}

	public int 유저삭제(int id) {
		
		return userDao.deleteUser(id);
	}

}
