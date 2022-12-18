package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Booking;
import dto.Payment;
import dto.Reserve;
import service.face.BookingService;
import service.face.PaymentService;
import service.impl.BookingServiceImpl;
import service.impl.PaymentServiceImpl;

@WebServlet("/payment")
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PaymentService paymentService = new PaymentServiceImpl();
	private BookingService bookingService = new BookingServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String pay_kind = req.getParameter("paykind");
		int user_no = Integer.parseInt(req.getParameter("user_no"));
		int hotel_no = Integer.parseInt(req.getParameter("hotel_no"));
		int room_no = Integer.parseInt(req.getParameter("room_no"));
		int booking_no = Integer.parseInt(req.getParameter("booking_no"));
		int room_price = Integer.parseInt(req.getParameter("room_price"));
		System.out.println(pay_kind);
//		System.out.println("호텔번호 :" + hotel_no);
//		System.out.println("유저번호 :" + user_no);
//		System.out.println("객실번호 :" + room_no);
//		System.out.println("예약번호 :" + booking_no);
//		System.out.println("객실가격 :" + room_price);
		
		Payment payment = paymentService.insert(booking_no, user_no, room_price, pay_kind);
		System.out.println("결제내역 : " + payment);
		req.getRequestDispatcher("/WEB-INF/views/PaymentOk.jsp").forward(req, resp);
		

	}

}
