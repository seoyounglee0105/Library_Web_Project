package com.young.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.young.lib.dto.UserDTO;
import com.young.lib.utils.DBHelper;

public class UserDAO implements IUserDAO {
	
	private Connection conn;
	
	public UserDAO() {
		conn = DBHelper.getInstance().getConnection();
	}

	@Override
	public ArrayList<UserDTO> select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO select(String column, String value) {
		UserDTO resultDto = null;
		String sql = " SELECT * FROM user WHERE " + column + " = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
		// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				resultDto = new UserDTO();
				resultDto.setId(rs.getString("id"));
				resultDto.setPassword(rs.getString("password"));
				resultDto.setName(rs.getString("name"));
				resultDto.setPhoneNumber(rs.getString("phone_number"));
				resultDto.setAddress(rs.getString("address"));
				resultDto.setEmail(rs.getString("email"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();					
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultDto;
	} // end of select (조건 1개)

	@Override
	public int insert(UserDTO userDTO) {
		int resultCount = 0;
		String sql = null;
		PreparedStatement pstmt = null;
		
		// email은 NULL 허용 컬럼이므로, 사용 쿼리를 둘로 나눔
		boolean isEmail = true;
		
		// email을 입력하지 않은 경우
		if (userDTO.getEmail().equals("")) {
			isEmail = false;
			sql = " INSERT INTO user (id, password, name, phone_number, address) "
				  + " VALUES (?, ?, ?, ?, ?); ";
		} else {
			sql = " INSERT INTO user VALUES (?, ?, ?, ?, ?, ?); ";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDTO.getId());
			pstmt.setString(2, userDTO.getPassword());
			pstmt.setString(3, userDTO.getName());
			pstmt.setString(4, userDTO.getPhoneNumber());
			pstmt.setString(5, userDTO.getAddress());
			
			if (isEmail == true) {
				pstmt.setString(6, userDTO.getEmail());
			}
			resultCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultCount;
	}

	@Override
	public int delete(String id) {
		int resultCount = 0;
		String sql = " DELETE FROM user WHERE id = ? ";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			resultCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultCount;
	}

	// 두 개의 조건을 이용한 SELECT
	@Override
	public UserDTO select(String firstColumn, String firstValue, String secondColumn, String secondValue) {
		UserDTO resultDto = null;
		String sql = " SELECT * FROM user WHERE " + firstColumn + " = ? AND " + secondColumn + " = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, firstValue);
			pstmt.setString(2, secondValue);
			rs = pstmt.executeQuery();
			
			// 행이 존재한다면 while문으로 들어감
			while (rs.next()) {
				resultDto = new UserDTO();
				resultDto.setId(rs.getString("id"));
				resultDto.setPassword(rs.getString("password"));
				resultDto.setName(rs.getString("name"));
				resultDto.setPhoneNumber(rs.getString("phone_number"));
				resultDto.setAddress(rs.getString("address"));
				resultDto.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultDto;
	} // end of select (조건 2개)

	@Override
	public int update(UserDTO userDTO) {
		int resultCount = 0;
		String sql = " UPDATE user SET password = ?, name = ?, phone_number = ?, address = ?, email = ? WHERE id = ?  ";
		PreparedStatement pstmt = null;
		
		// email은 NULL 허용 컬럼이므로, 사용 쿼리를 둘로 나눔
		boolean isEmail = true;
		
		// email을 입력하지 않은 경우
		if (userDTO.getEmail().equals("")) {
			isEmail = false;
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDTO.getPassword());
			pstmt.setString(2, userDTO.getName());
			pstmt.setString(3, userDTO.getPhoneNumber());
			pstmt.setString(4, userDTO.getAddress());
			pstmt.setString(6, userDTO.getId());
			
			if (isEmail == false) {
				pstmt.setString(5, null);
			} else {
				pstmt.setString(5, userDTO.getEmail());
			}
			
			resultCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultCount;
	}
	
}
