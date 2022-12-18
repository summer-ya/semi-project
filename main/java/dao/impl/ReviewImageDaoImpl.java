package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ReviewImageDao;
import dto.ReviewImage;

public class ReviewImageDaoImpl implements ReviewImageDao {
	
	private PreparedStatement ps; //SQL수행 객체
	private ResultSet rs; //SQL조회 결과 객체

	@Override
	public int insert(Connection conn, ReviewImage reviewImage) {

		String sql = "";
		sql += "INSERT INTO reviewimage( reviewimage_no, review_no, originname, storedname )";
		sql += " VALUES( reviewimage_seq.nextval, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reviewImage.getReview_no());
			ps.setString(2, reviewImage.getOriginname());
			ps.setString(3, reviewImage.getStoredname());
			
			System.out.println( reviewImage.getReview_no());
			System.out.println( reviewImage.getOriginname());
			System.out.println( reviewImage.getStoredname());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public List<ReviewImage> selectImageByReviewNO(Connection conn, int review_no) {

		String sql = "";
		sql += "SELECT review_no, reviewimage_no, originname, storedname FROM REVIEWIMAGE";
		sql += " WHERE review_no = ?";

		List<ReviewImage> list =  new ArrayList<>();
		
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review_no);
			
			rs = ps.executeQuery();						
			while( rs.next() ) {
				ReviewImage reviewImage = new ReviewImage();
				
				reviewImage.setReview_no(rs.getInt("review_no"));
				reviewImage.setReviewimage_no( rs.getInt("reviewimage_no") );
				reviewImage.setOriginname( rs.getString("originname") );
				reviewImage.setStoredname( rs.getString("storedname") );				
				list.add(reviewImage);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return list;
	}


}
