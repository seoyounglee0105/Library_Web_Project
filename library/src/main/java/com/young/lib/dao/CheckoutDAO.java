package com.young.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.young.lib.dto.BookDTO;
import com.young.lib.dto.CheckoutDTO;
import com.young.lib.utils.DBHelper;

public class CheckoutDAO implements ICheckoutDAO {

	private Connection conn;
	
	public CheckoutDAO() {
		conn = DBHelper.getInstance().getConnection();
	}
	
	@Override
	public int insert(CheckoutDTO checkoutDTO) {
		int resultCount = 0;
		String query = " INSERT INTO checkout (user_id, book_id) VALUES (?, ?) ";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, checkoutDTO.getUserId());
			pstmt.setInt(2, checkoutDTO.getBookId());
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
	public ArrayList<CheckoutDTO> select(String userId) {
		ArrayList<CheckoutDTO> resultList = new ArrayList<>();
		String sql = " SELECT * FROM checkout WHERE user_id = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CheckoutDTO dto = new CheckoutDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getString("user_id"));
				dto.setBookId(rs.getInt("book_id"));
				dto.setCheckoutDate(rs.getString("checkout_date"));
				dto.setReturn(rs.getBoolean("is_return"));
				resultList.add(dto);
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
		return resultList;
	}

	@Override
	public ArrayList<CheckoutDTO> select(String userId, int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(boolean isReturn) {
		// TODO Auto-generated method stub
		return 0;
	}

}
