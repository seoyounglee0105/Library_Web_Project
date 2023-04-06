package com.young.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.young.lib.dto.ReviewDTO;
import com.young.lib.utils.DBHelper;

public class ReviewDAO implements IReviewDAO {
	
	private Connection conn;
	
	public ReviewDAO() {
		conn = DBHelper.getInstance().getConnection();
	}

	// 리뷰 작성
	@Override
	public int insert(ReviewDTO reviewDTO) {
		int resultCount = 0;
		String sql = " INSERT INTO review (user_id, book_id, star, title, content)"
				   + " VALUES (?, ?, ?, ?, ?) ";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reviewDTO.getUserId());
			pstmt.setInt(2, reviewDTO.getBookId());
			pstmt.setInt(3, reviewDTO.getStar());
			pstmt.setString(4, reviewDTO.getTitle());
			pstmt.setString(5, reviewDTO.getContent());
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

	// 회원별 리뷰 조회
	@Override
	public ArrayList<ReviewDTO> select(String userId) {
		ArrayList<ReviewDTO> resultList = new ArrayList<>();
		String sql = " SELECT * FROM review WHERE user_id = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getString("user_id"));
				dto.setBookId(rs.getInt("book_id"));
				dto.setStar(rs.getInt("star"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriteDate(rs.getString("write_date"));
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

	// 도서별 리뷰 조회 (리뷰 정보만 있으면 됨)
	@Override
	public ArrayList<ReviewDTO> select(int bookId) {
		ArrayList<ReviewDTO> resultList = new ArrayList<>();
		String sql = " SELECT * FROM review WHERE book_id = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setId(rs.getInt("id"));
				dto.setUserId(rs.getString("user_id"));
				dto.setBookId(rs.getInt("book_id"));
				dto.setStar(rs.getInt("star"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriteDate(rs.getString("write_date"));
				resultList.add(dto);
				System.out.println();
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

	// 회원+도서별 리뷰 조회 (단일 객체)
	@Override
	public ReviewDTO select(String userId, int bookId) {
		ReviewDTO resultDto = null;
		String sql = " SELECT * FROM review AS r INNER JOIN book AS b ON r.book_id = b.id WHERE r.user_id = ? AND r.book_id = ?  ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, bookId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				resultDto = new ReviewDTO();
				resultDto.setId(rs.getInt("r.id"));
				resultDto.setUserId(rs.getString("r.user_id"));
				resultDto.setBookId(rs.getInt("r.book_id"));
				resultDto.setStar(rs.getInt("r.star"));
				resultDto.setTitle(rs.getString("r.title"));
				resultDto.setContent(rs.getString("r.content"));
				resultDto.setName(rs.getString("b.name"));
				resultDto.setWriteDate(rs.getString("write_date"));
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
	}

	@Override
	public ReviewDTO selectById(int reviewId) {
		ReviewDTO resultDto = null;
		String sql = " SELECT * FROM review WHERE id = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				resultDto = new ReviewDTO();
				resultDto.setId(rs.getInt("id"));
				resultDto.setUserId(rs.getString("user_id"));
				resultDto.setBookId(rs.getInt("book_id"));
				resultDto.setStar(rs.getInt("star"));
				resultDto.setTitle(rs.getString("title"));
				resultDto.setContent(rs.getString("content"));
				resultDto.setWriteDate(rs.getString("write_date"));
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
	}

	@Override
	public int delete(int reviewId) {
		int resultCount = 0;
		String sql = " DELETE FROM review WHERE id = ? ";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
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
