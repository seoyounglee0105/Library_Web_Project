package com.young.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.young.lib.dto.BookDTO;
import com.young.lib.utils.DBHelper;

public class BookDAO implements IBookDAO {

	private Connection conn;
	
	public BookDAO() {
		conn = DBHelper.getInstance().getConnection();
	}
	
	// 전체 조회
	@Override
	public ArrayList<BookDTO> select() {
		ArrayList<BookDTO> resultList = new ArrayList<>();
		String sql = " SELECT * FROM book AS b INNER JOIN category AS c ON b.category_id = c.id ORDER BY b.id ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setId(rs.getInt("b.id"));
				dto.setName(rs.getString("b.name"));
				dto.setWriter(rs.getString("writer"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setImage(rs.getString("image"));
				dto.setDescription(rs.getString("description"));
				dto.setCheckOutCount(rs.getInt("check_out_count"));
				dto.setAvailable(rs.getBoolean("is_available"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setCategoryName(rs.getString("c.name"));
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

	// 하나의 조건에 대해 조회
	@Override
	public ArrayList<BookDTO> select(String column, int value) {
		ArrayList<BookDTO> resultList = new ArrayList<>();
		String sql = " SELECT * FROM book AS b INNER JOIN category AS c ON b.category_id = c.id WHERE " + column + " = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, value);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setId(rs.getInt("b.id"));
				dto.setName(rs.getString("b.name"));
				dto.setWriter(rs.getString("writer"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setImage(rs.getString("image"));
				dto.setDescription(rs.getString("description"));
				dto.setCheckOutCount(rs.getInt("check_out_count"));
				dto.setAvailable(rs.getBoolean("is_available"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setCategoryName(rs.getString("c.name"));
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

	// 검색어에 대한 조회
	@Override
	public ArrayList<BookDTO> select(String search) {
		ArrayList<BookDTO> resultList = new ArrayList<>();
		String sql = " SELECT * FROM book AS b INNER JOIN category AS c ON b.category_id = c.id WHERE b.name LIKE ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();
			System.out.println(search);
			
			while (rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setId(rs.getInt("b.id"));
				dto.setName(rs.getString("b.name"));
				dto.setWriter(rs.getString("writer"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setImage(rs.getString("image"));
				dto.setDescription(rs.getString("description"));
				dto.setCheckOutCount(rs.getInt("check_out_count"));
				dto.setAvailable(rs.getBoolean("is_available"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setCategoryName(rs.getString("c.name"));
				resultList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();					
				}
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}

	// 전체 조회 - 정렬
	@Override
	public ArrayList<BookDTO> selectOrder(String orderColumn) {
		ArrayList<BookDTO> resultList = new ArrayList<>();
		String sql = " SELECT * FROM book AS b INNER JOIN category AS c ON b.category_id = c.id ORDER BY " + orderColumn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setId(rs.getInt("b.id"));
				dto.setName(rs.getString("b.name"));
				dto.setWriter(rs.getString("writer"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setImage(rs.getString("image"));
				dto.setDescription(rs.getString("description"));
				dto.setCheckOutCount(rs.getInt("check_out_count"));
				dto.setAvailable(rs.getBoolean("is_available"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setCategoryName(rs.getString("c.name"));
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

	// 하나의 조건에 대한 조회 - 정렬
	@Override
	public ArrayList<BookDTO> selectOrder(String column, int value, String orderColumn) {
		ArrayList<BookDTO> resultList = new ArrayList<>();
		String sql = " SELECT * FROM book AS b INNER JOIN category AS c ON b.category_id = c.id WHERE " + column + " = ? Order BY " + orderColumn ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, value);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setId(rs.getInt("b.id"));
				dto.setName(rs.getString("b.name"));
				dto.setWriter(rs.getString("writer"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setImage(rs.getString("image"));
				dto.setDescription(rs.getString("description"));
				dto.setCheckOutCount(rs.getInt("check_out_count"));
				dto.setAvailable(rs.getBoolean("is_available"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setCategoryName(rs.getString("c.name"));
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

	// 대여량 갱신
	@Override
	public int update(int checkOutCount, int bookId) {
		int resultCount = 0;
		String query = " UPDATE book SET check_out_count = ? WHERE id = ? ";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, checkOutCount);
			pstmt.setInt(2, bookId);
			pstmt.executeUpdate();
			
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
	
	// 대여 가능 여부 갱신
	@Override
	public int update(boolean isAvailable, int bookId) {
		int resultCount = 0;
		String query = " UPDATE book SET is_available = ? WHERE id = ? ";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setBoolean(1, isAvailable);
			pstmt.setInt(2, bookId);
			pstmt.executeUpdate();
			
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

	// 대여량 순 & LIMIT n개
	@Override
	public ArrayList<BookDTO> select(int count) {
		ArrayList<BookDTO> resultList = new ArrayList<>();
		String sql = " SELECT * FROM book AS b INNER JOIN category AS c ON b.category_id = c.id ORDER BY check_out_count DESC LIMIT ? "; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setId(rs.getInt("b.id"));
				dto.setName(rs.getString("b.name"));
				dto.setWriter(rs.getString("writer"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setImage(rs.getString("image"));
				dto.setDescription(rs.getString("description"));
				dto.setCheckOutCount(rs.getInt("check_out_count"));
				dto.setAvailable(rs.getBoolean("is_available"));
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setCategoryName(rs.getString("c.name"));
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


}
