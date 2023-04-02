package com.young.lib.dao;

import java.util.ArrayList;

import com.young.lib.dto.UserDTO;

public interface IUserDAO {

	// 전체 조회
	ArrayList<UserDTO> select();
	
	// 한 개의 조건을 이용한 SELECT
	UserDTO select(String column, String value); 
	
	// 두 개의 조건을 이용한 SELECT
	UserDTO select(String firstColumn, String firstValue, String secondColumn, String secondValue);
	
	int insert(UserDTO userDTO); // 회원 가입
	
	int update(UserDTO userDTO); // 회원 정보 수정
	
	int delete(String id); // 회원 탈퇴
	
}
