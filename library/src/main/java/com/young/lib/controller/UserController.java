package com.young.lib.controller;

import com.young.lib.dto.UserDTO;
import com.young.lib.service.UserService;

public class UserController {

	private UserService userService;
	
	public UserController() {
		userService = new UserService();
	}
	
	// 회원 가입 요청
	public int requestSignUp(UserDTO userDTO) {
		int responseType = 0;
		// 0 : 실패, 1 : 성공, 2 : 아이디 중복, 3 : 전화번호 중복, 4 : 전화번호 형식 틀림
		responseType = userService.signUp(userDTO);
		return responseType;
	}
	
	// 로그인 요청
	public UserDTO requestLogin(String id, String password) {
		UserDTO responseDTO = null;
		// null : 실패
		responseDTO = userService.login(id, password);
		return responseDTO;
	}
	
	// 로그인된 사용자의 id 값으로 정보 확인하기
	public UserDTO requestUserInfo(String id) {
		UserDTO responseDTO = null;
		responseDTO = userService.userInfo(id);
		return responseDTO;
	}
	
}
