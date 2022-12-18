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
import dao.face.BookingDao;
import dto.Booking;
import dto.Reserve;

public class BookingDaoImpl implements BookingDao {
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public int bookingInsert(Connection conn, int hotel_no, int room_no, int user_no, String from, String to,
			int room_price) {

		String sql = "";
		sql += "INSERT INTO booking VALUES(booking_seq.nextval, ?, ?, ?, ?, ?, ?)";

		System.out.println(hotel_no);
		System.out.println(user_no);
		System.out.println(room_no);
		System.out.println(room_price);
		System.out.println(from);
		System.out.println(to);

		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, room_no);
			ps.setInt(2, hotel_no);
			ps.setInt(3, user_no);

			// String -> Date 변환
			SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat afterFormat = new SimpleDateFormat("yyyyMMdd");
			Date date1 = null;
			Date date2 = null;

			date1 = beforeFormat.parse(from);
			date2 = beforeFormat.parse(to);
			from = afterFormat.format(date1);
			to = afterFormat.format(date2);
			ps.setString(4, from);
			ps.setString(5, to);
			ps.setInt(6, room_price);

			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return result;
	}

	@Override
	public Booking SelectAllBooking(Connection conn, int hotel_no, int room_no, int user_no, String from, String to,
			int room_price) {

		String sql = "";
		sql += "SELECT * FROM BOOKING WHERE hotel_no=? and room_no=? and user_no=?";
		Booking booking = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, hotel_no);
			ps.setInt(2, room_no);
			ps.setInt(3, user_no);

			rs = ps.executeQuery();

			while (rs.next()) {

				booking = new Booking();

				booking.setBooking_no(rs.getInt("booking_no"));
				booking.setRoom_no(rs.getInt("room_no"));
				booking.setHotel_no(rs.getInt("hotel_no"));
				booking.setUser_no(rs.getInt("user_no"));

				// Date -> String 변환
				Date hotelin = rs.getDate("hotel_in");
				Date hotelout = rs.getDate("hotel_out");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String hotel_in = format.format(hotelin);
				String hotel_out = format.format(hotelout);

				booking.setHotel_in(hotel_in);
				booking.setHotel_out(hotel_out);
				booking.setRoom_price(rs.getInt("room_price"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return booking;
	}

	@Override
	public Reserve SelectAllByBookingNo(Connection conn, int booking_no) {

		String sql = "select h.hotel_name, h.hotel_intime, h.hotel_no, h.hotel_outtime, r.room_type,";
		sql += " r.room_price, r.room_no, b.booking_no, u.user_no, b.hotel_in, b.hotel_out, u.user_name, u.user_phone";
		sql += " FROM hotel h";
		sql += " JOIN room r ON (h.hotel_no = r.hotel_no)";
		sql += " JOIN booking b ON (r.room_no = b.room_no)";
		sql += " JOIN semi_user u ON ( b.user_no = u.user_no)";
		sql += " WHERE b.booking_no = ?";

		Reserve reserve = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, booking_no);

			rs = ps.executeQuery();

			while (rs.next()) {
				reserve = new Reserve();

				reserve.setHotel_name(rs.getString("hotel_name"));
				reserve.setHotel_intime(rs.getString("hotel_intime"));
				reserve.setHotel_outtime(rs.getString("hotel_outtime"));
				reserve.setHotel_no(rs.getInt("hotel_no"));
				reserve.setBooking_no(rs.getInt("booking_no"));
				reserve.setRoom_no(rs.getInt("room_no"));
				reserve.setUser_no(rs.getInt("user_no"));
				reserve.setRoom_type(rs.getString("room_type"));
				reserve.setRoom_price(rs.getString("room_price"));
				
				// Date -> String 변환
				Date hotelin = rs.getDate("hotel_in");
				Date hotelout = rs.getDate("hotel_out");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String hotel_in = format.format(hotelin);
				String hotel_out = format.format(hotelout);
				
				reserve.setHotel_in(hotel_in);
				reserve.setHotel_out(hotel_out);
				reserve.setUser_name(rs.getString("user_name"));
				reserve.setUser_phone(rs.getString("user_phone"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return reserve;
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------

	@Override
	public List<Map<String, Object>> selectBookinglistByUserno(Connection conn, int userno) {
		
		String sql = "";
		sql += "SELECT h.hotel_photo, h.hotel_name, h.hotel_intime, r.room_type, b.hotel_in, b.booking_no";
		sql += " FROM booking b";
		sql += " join room r on r.room_no = b.room_no";
		sql += " join hotel h on h.hotel_no = b.hotel_no";
		sql += " where user_no = ? AND b.hotel_in > sysdate";
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, userno);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				
				map.put("hotel_photo", rs.getString("hotel_photo"));
				map.put("hotel_name", rs.getString("hotel_name"));
				map.put("hotel_intime", rs.getString("hotel_intime"));
				map.put("room_type", rs.getString("room_type"));
				map.put("hotel_in", rs.getDate("hotel_in"));
				map.put("booking_no", rs.getInt("booking_no"));

				list.add(map);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}
	
	
	@Override
	public List<Map<String, Object>> selectDetailByBookingno(Connection conn, int bookingno) {
		String sql = "";
		sql += "SELECT h.hotel_photo, h.hotel_name, h.hotel_intime, h.hotel_outtime, r.room_type, b.hotel_in, "
				+ "b.hotel_out, b.booking_no, p.pay_total, p.pay_kind";
		sql += " FROM payment p";
		sql += " join booking b on p.booking_no = b.booking_no";
		sql += " join room r on r.room_no = b.room_no";
		sql += " join hotel h on h.hotel_no = b.hotel_no";
		sql += " where b.booking_no = ?";
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, bookingno);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				
				map.put("hotel_photo", rs.getString("hotel_photo"));
				map.put("hotel_name", rs.getString("hotel_name"));
				map.put("hotel_intime", rs.getString("hotel_intime"));
				map.put("hotel_outtime", rs.getString("hotel_outtime"));
				map.put("room_type", rs.getString("room_type"));
				map.put("hotel_in", rs.getDate("hotel_in"));
				map.put("hotel_out", rs.getDate("hotel_out"));
				map.put("booking_no", rs.getInt("booking_no"));
				map.put("pay_total", rs.getInt("pay_total"));
				map.put("pay_kind", rs.getString("pay_kind"));

				list.add(map);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}
	
	
	@Override
	public int deleteBookingByBookingno(Connection conn, int bookingno) {
		
		String sql = "";
		sql += "DELETE booking";
		sql += " WHERE booking_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookingno);
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	
	
}
