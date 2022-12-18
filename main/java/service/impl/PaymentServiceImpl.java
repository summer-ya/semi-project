package service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.PaymentDao;
import dao.impl.PaymentDaoImpl;
import dto.Booking;
import dto.Payment;
import service.face.PaymentService;

public class PaymentServiceImpl implements PaymentService {

	PaymentDao paymentDao = new PaymentDaoImpl();

	@Override
	public List<Map<String, Object>> selectAllPayedHotel(HttpServletRequest request) {

		Connection conn = JDBCTemplate.getConnection();
		int user_no = (int) request.getSession().getAttribute("user_no");

		return paymentDao.selectAllPayedHotelByUserNo(conn, user_no);

	}
	
	
	@Override
	public Payment insert(int booking_no, int user_no, int room_price, String pay_kind) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = paymentDao.insertPayment(conn, booking_no, user_no, room_price, pay_kind);
		
		if ( result > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
			
		}
		
		return paymentDao.SelectPayment(conn, booking_no, user_no);
	}

}
