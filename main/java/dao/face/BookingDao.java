package dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import dto.Booking;
import dto.Reserve;

public interface BookingDao {

	public int bookingInsert(Connection conn, int hotel_no, int room_no, int user_no, String from, String to, int room_price);

	public Booking SelectAllBooking(Connection conn, int hotel_no, int room_no, int user_no, String from, String to,
			int room_price);

	public Reserve SelectAllByBookingNo(Connection conn, int booking_no);
	
	
	//----------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 유저번호로 예약내역 가져오기
	 * 
	 * @param conn
	 * @param userno
	 * @return
	 */
	public List<Map<String, Object>> selectBookinglistByUserno(Connection conn, int userno);

	
	/**
	 * 예약번호로 예약 상세내역 가져오기
	 * 
	 * @param conn
	 * @param bookingno
	 * @return
	 */
	public List<Map<String, Object>> selectDetailByBookingno(Connection conn, int bookingno);
	
	
	/**
	 * 예약취소
	 * 
	 * @param conn
	 * @param bookingno
	 * @return
	 */
	public int deleteBookingByBookingno(Connection conn, int bookingno);
	

}
