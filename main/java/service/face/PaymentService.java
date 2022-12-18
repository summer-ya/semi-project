package service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.Booking;
import dto.Payment;

public interface PaymentService {
	
	/**
	 * 특정 유저가 그동안 결제한 호텔들 리스트 불러옴
	 * 
	 * @param 
	 * @return
	 */
	public List< Map<String, Object>> selectAllPayedHotel(HttpServletRequest request);

	public Payment insert(int booking_no, int user_no, int room_price, String pay_kind);

	
}

