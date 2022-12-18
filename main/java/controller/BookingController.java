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

@WebServlet("/hotel/booking")
public class BookingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookingService bookingService = new BookingServiceImpl();
	private PaymentService paymentService = new PaymentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/hotel/booking [GET] 호출 성공");
		
		HttpSession session = req.getSession();
		int user_no = (Integer) session.getAttribute("user_no");
		int hotel_no = Integer.parseInt(req.getParameter("hotel_no"));
		int room_no = Integer.parseInt(req.getParameter("room_no"));
		int room_price = Integer.parseInt(req.getParameter("room_price"));
//		String room_price = req.getParameter("room_price");
		String from = (String) req.getParameter("checkin");
		String to = (String) req.getParameter("checkout");
		
		System.out.println("호텔번호 :" + hotel_no);
		System.out.println("유저번호 :" + user_no);
		System.out.println("객실번호 :" + room_no);
		System.out.println("객실가격 :" + room_price);
		System.out.println("체크인날짜 : " + from);
		System.out.println("체크아웃날짜 : " + to);
		
		// 예약DB 삽입 후 반환
		Booking booking = bookingService.insert(hotel_no, room_no, user_no, from, to, room_price);
		System.out.println(booking);
				
		// 결제페이지에 반영될 종합 정보
		Reserve reserve = bookingService.selectAll(booking.getBooking_no());
		System.out.println(reserve);
		
		req.setAttribute("reserve", reserve);		
		req.getRequestDispatcher("/WEB-INF/views/bookingPage.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/hotel/booking [POST] 요청 성공");
		
	}

}