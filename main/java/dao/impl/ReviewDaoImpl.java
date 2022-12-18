package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.JDBCTemplate;
import dao.face.ReviewDao;
import dao.face.ReviewImageDao;
import dto.Review;
import dto.ReviewImage;
import dto.Semi_User;

public class ReviewDaoImpl implements ReviewDao {
	
	private PreparedStatement ps; //SQL수행 객체
	private ResultSet rs; //SQL조회 결과 객체
	
	@Override
	public int selectNextReviewno(Connection conn) {

		
		String sql = "";
		sql += "SELECT REVIEW_seq.nextval FROM dual";
		
		int nextReviewNo = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				nextReviewNo = rs.getInt("nextval");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextReviewNo;
	}

	@Override
	public int insert(Connection conn, Review review) {

		String sql = "";
		sql += "INSERT INTO review ( pay_no, review_no, hotel_no, booking_no, user_email, review_content, review_score, user_no, review_date, room_type)";
		sql += " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, to_char(sysdate,'yyyy.mm.dd hh24:mi'), ?)";
		
		int res = 0;

		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, review.getPay_no());
			ps.setInt(2, review.getReview_no());
			ps.setInt(3, review.getHotel_no());
			ps.setInt(4, review.getBooking_no());
			ps.setString(5, review.getUser_email());
			ps.setString(6, review.getReview_content());
			ps.setInt(7, review.getReview_score());
			ps.setInt(8, review.getUser_no());
			
			ps.setString(9, review.getRoom_type());
			
			res = ps.executeUpdate();
			
			System.out.println(res);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public List<Map<String, Object>> selectReviewsByDateByHotelNo(Connection conn, int hotel_no) {
		
		//SQL작성
		String sql = "";
		sql += "select * from review r";
		sql += " join semi_user s";
		sql += " on r.user_no = s.user_no";
		sql += " where hotel_no= ? ";
		sql += " order by r.review_date desc";
		
		//결과 저장할 List
		List<Map<String, Object>> resultlist = new ArrayList<>();
		Map<String, Object> map;
		List<ReviewImage> imageList;
		
		ReviewImageDao reviewImageDao = new ReviewImageDaoImpl();
	
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, hotel_no);
			
			rs = ps.executeQuery(); //SQL수행 및 결과 집합 저장
								
			//조회 결과 처리
			while(rs.next()) {

			//결과값 저장 객체
			Review r = new Review(); 
			Semi_User u = new Semi_User();
					
			//결과값 한 행씩 처리
			r.setPay_no(rs.getInt("pay_no"));
			r.setReview_no(rs.getInt("review_no"));
			r.setHotel_no(rs.getInt("hotel_no"));
			r.setBooking_no(rs.getInt("booking_no"));
			r.setUser_email(rs.getString("user_email"));
			r.setReview_content(rs.getString("review_content"));
			r.setReview_score(rs.getInt("review_score"));
			r.setUser_no(rs.getInt("user_no"));
			r.setRoom_type(rs.getString("room_type"));
			
			String date = rs.getString("review_date");
			r.setReview_date(date);

			
			imageList = reviewImageDao.selectImageByReviewNO(conn,rs.getInt("review_no") );


			
			//결과값 한 행씩 처리
			u.setUser_no(rs.getInt("user_no"));
			u.setUser_name(rs.getString("user_name"));
			u.setUser_email(rs.getString("user_email"));			
			u.setUser_phone(rs.getString("user_phone"));
			u.setUser_pw(rs.getString("user_pw"));
			u.setUser_pic(rs.getString("user_pic"));
		
			//넣을 map 생성
			map = new HashMap<>();
			
			map.put("r", r);
			map.put("ri", imageList);
			map.put("u", u);
			
			//list에 map 넣기
			resultlist.add(map);
						
		}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
	return resultlist;
	}
	

	@Override
	public List<Map<String, Object>> selectReviewsByScoreByHotelNo(Connection conn, int hotel_no) {
		//SQL작성
		String sql = "";
		sql += "select * from review r";
		sql += " join semi_user s";
		sql += " on r.user_no = s.user_no";
		sql += " where hotel_no= ? ";
		sql += " order by r.review_score desc";
		
		//결과 저장할 List
		List<Map<String, Object>> resultlist = new ArrayList<>();
		Map<String, Object> map;
		List<ReviewImage> imageList;
		
		System.out.println("hotel_no"+hotel_no);
		ReviewImageDao reviewImageDao = new ReviewImageDaoImpl();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, hotel_no);
			
			rs = ps.executeQuery(); //SQL수행 및 결과 집합 저장
								
			//조회 결과 처리
			while(rs.next()) {

			//결과값 저장 객체
			Review r = new Review(); 
			Semi_User u = new Semi_User();
				
			//결과값 한 행씩 처리
			r.setPay_no(rs.getInt("pay_no"));
			r.setReview_no(rs.getInt("review_no"));
			//System.out.println(rs.getInt("review_no"));
			r.setHotel_no(rs.getInt("hotel_no"));
			r.setBooking_no(rs.getInt("booking_no"));
			r.setUser_email(rs.getString("user_email"));
			r.setReview_content(rs.getString("review_content"));
			r.setReview_score(rs.getInt("review_score"));
			r.setUser_no(rs.getInt("user_no"));
			r.setRoom_type(rs.getString("room_type"));
			
			String date = rs.getString("review_date");
			r.setReview_date(date);
			
			
			imageList = reviewImageDao.selectImageByReviewNO(conn,rs.getInt("review_no") );
			
			
			//결과값 한 행씩 처리
			u.setUser_no(rs.getInt("user_no"));
			u.setUser_name(rs.getString("user_name"));
			u.setUser_email(rs.getString("user_email"));			
			u.setUser_phone(rs.getString("user_phone"));
			u.setUser_pw(rs.getString("user_pw"));
			u.setUser_pic(rs.getString("user_pic"));
		
			//넣을 map 생성
			map = new HashMap<>();
			
			map.put("r", r);
			map.put("ri", imageList);
			map.put("u", u);
			
			//list에 map 넣기
			resultlist.add(map);
						
		}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
	return resultlist;
	}


	@Override
	public int update(Connection conn, int review_no, String review_content) {

		System.out.println("review updateDaonImpl 실행()");
		
		String sql = "";
		sql += "UPDATE review SET";
		sql += " review_content = ?";
		sql += " WHERE review_no = ?";
				
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, review_content);
			ps.setInt(2, review_no);
			
			res = ps.executeUpdate();
			System.out.println(res);
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	
	}

}


