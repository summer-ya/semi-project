package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.BookingService;
import service.impl.BookingServiceImpl;

@WebServlet("/booking/delete")
public class UserBookingDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookingService bookingService = new BookingServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		boolean res = bookingService.deleteBooking(req);
		System.out.println("예약내역 삭제 결과 : " + res);
		
		req.getRequestDispatcher("/WEB-INF/views/bookingDelete.jsp").forward(req, resp);
	}

}
