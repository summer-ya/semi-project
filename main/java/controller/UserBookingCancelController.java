package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.BookingService;
import service.impl.BookingServiceImpl;

@WebServlet("/booking/cancel")
public class UserBookingCancelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookingService bService = new BookingServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Map<String, Object>> dList = bService.getBookingInfo(req);
		System.out.println("취소 리스트 : " + dList);
		
		req.setAttribute("dList", dList);
		
		req.getRequestDispatcher("/WEB-INF/views/bookingCancel.jsp").forward(req, resp);
	}

}
