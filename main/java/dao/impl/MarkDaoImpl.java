package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.MarkDao;
import dto.Hotel;
import dto.Mark;

public class MarkDaoImpl implements MarkDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int updateMark(Connection conn, int hotel_no, int user_no) {

		System.out.println(user_no);
		String sql = "";
		sql += "INSERT INTO mark VALUES";
		sql += "(mark_seq.nextval, ?, ?)";

		int updateRes = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user_no);
			ps.setInt(2, hotel_no);

			updateRes = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return updateRes;
	}

	@Override
	public Mark selectMark(Connection conn, int hotel_no, int user_no) {

		String sql = "";
		sql += "SELECT * FROM mark WHERE hotel_no=? and user_no=?";
		Mark mark = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, hotel_no);
			ps.setInt(2, user_no);

			rs = ps.executeQuery();

			while (rs.next()) {

				mark = new Mark();

				mark.setMark_no(rs.getInt("mark_no"));
				mark.setUser_no(rs.getInt("user_no"));
				mark.setHotel_no(rs.getInt("hotel_no"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return mark;
	}

	@Override
	public int checkMarkCount(Connection conn, int hotel_no, int user_no) {

		String sql = "";
		sql += "select count(*) cnt FROM mark WHERE hotel_no=? and user_no=?";

		int count = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, hotel_no);
			ps.setInt(2, user_no);

			rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}
	
	@Override
	public void deleteMark(Connection conn, int hotel_no, int user_no) {
		
		String sql ="";
		sql += "delete from mark where hotel_no=? and user_no=?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, hotel_no);
			ps.setInt(2, user_no);
			
			res = ps.executeUpdate();
			
			if( res > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	}
	
	// 마이페이지에 보낼 데이터 조회 메소드
	
//	@Override
//	public List<Mypage> myPageInfo(Connection conn, int user_no) {
//		
//		String sql = "";
//		sql += "SELECT h.hotel_name, h.hotel_tel, h.hotel_photo, u.user_name";
//		sql += " FROM mark m";
//		sql += " JOIN hotel h ON (m.hotel_no = h.hotel_no)";
//		sql += " JOIN semi_user u ON (m.user_no = u.user_no)";
//		sql += " WHERE m.user_no = ?";
//		
//		List<Mypage> list = new ArrayList<>();
//		
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, user_no);
//			rs = ps.executeQuery();
//			
//			
//			while (rs.next()) {
//
//				Mypage mypage = new Mypage();
//
//				mypage.setHotel_name(rs.getString("hotel_name"));
//				mypage.setHotel_tel(rs.getString("hotel_tel"));
//				mypage.setHotel_photo(rs.getString("hotel_photo"));
//				mypage.setUser_name(rs.getString("user_name"));
//
//				list.add(mypage);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCTemplate.close(rs);
//			JDBCTemplate.close(ps);
//		}
//		
//		return list;
//	}

	
	@Override
	public List<Hotel> markedHotelList(Connection conn, String user_email) {

		String sql = "";
		sql += "select h.hotel_no, h.hotel_name, h.hotel_addr, h.hotel_tel, h.hotel_info, h.hotel_photo, h.mark_hit, h.hotel_intime, h.hotel_outtime from";
		sql += "(select * from semi_user s";
		sql += " join mark m";
		sql += " on m.user_no = s.user_no";
		sql += " where user_email = ?) t";
		sql += " left outer join hotel h";
		sql += " on t.hotel_no = h.hotel_no";
		
		List<Hotel> list  = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, user_email);
			
			rs = ps.executeQuery(); //SQL수행 및 결과 집합 저장
								
			//조회 결과 처리
			while(rs.next()) {
			Hotel h = new Hotel();//결과값 저장 객체
						
			//결과값 한 행씩 처리
			h.setHotel_no(rs.getInt("hotel_no"));
			h.setHotel_name(rs.getString("hotel_name"));
			h.setHotel_addr(rs.getString("hotel_addr"));
			h.setHotel_tel(rs.getString("hotel_tel"));
			h.setHotel_info(rs.getString("hotel_info"));
			h.setHotel_photo(rs.getString("hotel_photo"));
			h.setMark_hit(rs.getInt("mark_hit"));
			h.setHotel_intime(rs.getString("hotel_intime"));
			h.setHotel_outtime(rs.getString("hotel_outtime"));
			
						
			//리스트에 결과값 저장
			list.add(h);
						
		}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
	return list;
	}

	@Override
	public List<Hotel> selectHotelOrderbyMarkhit(Connection conn) {

		String sql = "";
		sql += "select * from(";
		sql += " select * from hotel";
		sql += "  ORDER BY mark_hit desc)";
		sql += "  where rownum <= 20";
		
		
		List<Hotel> list  = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL수행 및 결과 집합 저장
								
			//조회 결과 처리
			while(rs.next()) {
			Hotel h = new Hotel();//결과값 저장 객체
						
			//결과값 한 행씩 처리
			h.setHotel_no(rs.getInt("hotel_no"));
			h.setHotel_name(rs.getString("hotel_name"));
			h.setHotel_addr(rs.getString("hotel_addr"));
			h.setHotel_tel(rs.getString("hotel_tel"));
			h.setHotel_info(rs.getString("hotel_info"));
			h.setHotel_photo(rs.getString("hotel_photo"));
			h.setMark_hit(rs.getInt("mark_hit"));
			h.setHotel_intime(rs.getString("hotel_intime"));
			h.setHotel_outtime(rs.getString("hotel_outtime"));

			//리스트에 결과값 저장
			list.add(h);
						
		}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
	return list;
	}
	
}