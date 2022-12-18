package service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.BookingDao;
import dao.impl.BookingDaoImpl;
import dto.Booking;
import dto.Reserve;
import service.face.BookingService;

public class BookingServiceImpl implements BookingService {

	private BookingDao bookingDao = new BookingDaoImpl();

	@Override
	public Booking insert(int hotel_no, int room_no, int user_no, String from, String to, int room_price) {

		Connection conn = JDBCTemplate.getConnection();

		// 삽입결과부터 확인
		int result = bookingDao.bookingInsert(conn, hotel_no, room_no, user_no, from, to, room_price);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// DB삽입
		return bookingDao.SelectAllBooking(conn, hotel_no, room_no, user_no, from, to, room_price);
	}
	
	@Override
	public Reserve selectAll(int booking_no) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		return bookingDao.SelectAllByBookingNo(conn, booking_no);
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------

	@Override
	public List<Map<String, Object>> userBookinglist(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		int userno = (int) req.getSession().getAttribute("user_no");
		
		return bookingDao.selectBookinglistByUserno(conn, userno);
	}
	
	
	@Override
	public List<Map<String, Object>> getBookingInfo(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		int bookingno = Integer.parseInt(req.getParameter("booking_no"));
		
		return bookingDao.selectDetailByBookingno(conn, bookingno);
	}
	
	
	@Override
	public boolean deleteBooking(HttpServletRequest req) {
		Connection conn = JDBCTemplate.getConnection();
		int bookingno = Integer.parseInt(req.getParameter("booking_no"));
		
		if(bookingDao.deleteBookingByBookingno(conn, bookingno) > 0) {
			JDBCTemplate.commit(conn);
			return true;
		} else {
			JDBCTemplate.rollback(conn);
			return false;
		}
		
	}
	

}
