package com.young.lib.service;

import com.young.lib.dao.UserDAO;
import com.young.lib.dto.UserDTO;

public class UserService {

	private UserDAO userDAO;
	
	public UserService() {
		userDAO = new UserDAO();
	}
	
	// 회원 가입
	public int signUp(UserDTO userDTO) {
		int resultType = 0;
		
		// 아이디가 중복인지 확인
		if (checkId(userDTO.getId()) == true) {
			// 중복이면
			resultType = 2;
			return resultType;
		// 전화번호가 중복인지 확인
		} else if (checkPhoneNumber(userDTO.getPhoneNumber()) == true) {
			// 중복이면
			resultType = 3;
			return resultType;
		// 전화번호 형식 확인
		} else if (userDTO.getPhoneNumber().indexOf("-") != 3 || userDTO.getPhoneNumber().lastIndexOf("-") != 8
				|| userDTO.getPhoneNumber().length() != 13 || userDTO.getPhoneNumber().replaceAll("-", "").length() != 11) {
			// 형식이 틀렸다면
			resultType = 4;
			return resultType;
		}
		// 성공 시 1 반환
		resultType = userDAO.insert(userDTO);
		
		return resultType;
	}
	
	// 회원 아이디 중복 조회
	public boolean checkId(String id) {
		boolean result = false;
		
		// 중복이라면 result = true;
		if (userDAO.select("id", id) != null) {
			result = true;
		}
		return result;
	}
	
	// 회원 전화번호 중복 조회
	public boolean checkPhoneNumber(String phoneNumber) {
		boolean result = false;
		
		// 중복이라면 result = true;
		if (userDAO.select("phone_number", phoneNumber) != null) {
			result = true;
		}
		return result;
	}
	
	// 로그인 처리
	public UserDTO login(String id, String password) {
		UserDTO resultDTO = null;
		
		// 틀렸다면 null
		resultDTO = userDAO.select("id", id, "password", password);
		
		// 로그인 시에 password 정보를 반환하지 않도록 함 (민감한 정보)
		if (resultDTO != null) {
			resultDTO.setPassword(null);			
		}
		return resultDTO;
	}
	
	// id를 통해 사용자 정보 가져오기
	public UserDTO userInfo(String id) {
		UserDTO resultDTO = null;
		resultDTO = userDAO.select("id", id);
		return resultDTO;
	}
	
	// 회원 정보 수정
	public int updateInfo(UserDTO userDTO) {
		int resultType = 0;
		
		// 전화번호가 중복인지 확인
		if (checkPhoneNumber(userDTO.getPhoneNumber()) == true
			&& userInfo(userDTO.getId()).getPhoneNumber().equals(userDTO.getPhoneNumber()) == false) {
			// 중복이면
			resultType = 2;
			return resultType;
		// 전화번호 형식 확인
		} else if (userDTO.getPhoneNumber().indexOf("-") != 3 || userDTO.getPhoneNumber().lastIndexOf("-") != 8
				|| userDTO.getPhoneNumber().length() != 13 || userDTO.getPhoneNumber().replaceAll("-", "").length() != 11) {
			// 형식이 틀렸다면
			resultType = 3;
			return resultType;
		}
		resultType = userDAO.update(userDTO);
		return resultType;
	}
	
	// 회원 탈퇴
	public int deleteUser(String id) {
		int resultCount = 0;
		resultCount = userDAO.delete(id);
		return resultCount;
	}
	
	
}
