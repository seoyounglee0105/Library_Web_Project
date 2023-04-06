package com.young.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.young.lib.dto.ReviewImageDTO;
import com.young.lib.utils.DBHelper;

public class ReviewImageDAO implements IReviewImageDAO {
	
	private Connection conn;
	
	public ReviewImageDAO() {
		conn = DBHelper.getInstance().getConnection();
	}
	
	@Override
	public int insert(ReviewImageDTO reviewImageDTO) {
		int resultCount = 0;
		String sql = " INSERT INTO review_image VALUES (?, ?, ?) ";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewImageDTO.getReviewId());
			pstmt.setString(2, reviewImageDTO.getOriginImage());
			pstmt.setString(3, reviewImageDTO.getUuidImage());
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
	public ReviewImageDTO select(int reviewId) {
		ReviewImageDTO reviewImageDTO = null;
		String sql = " SELECT * FROM review_image WHERE review_id = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				reviewImageDTO = new ReviewImageDTO();
				reviewImageDTO.setReviewId(rs.getInt("review_id"));
				reviewImageDTO.setOriginImage(rs.getString("origin_image"));
				reviewImageDTO.setUuidImage(rs.getString("uuid_image"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return reviewImageDTO;
	}

}
