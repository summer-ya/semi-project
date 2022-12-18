package dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import dto.Booking;
import dto.Payment;

public interface PaymentDao {

	public List<Map<String, Object>> selectAllPayedHotelByUserNo(Connection conn, int user_no);

	public Payment SelectPayment(Connection conn, int booking_no, int user_no);

	public int insertPayment(Connection conn, int booking_no, int user_no, int room_price, String pay_kind);

	
	
	
}
