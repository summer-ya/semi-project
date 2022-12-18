package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.SortedHotel;
import service.face.HotelService;
import service.impl.HotelServiceImpl;

@WebServlet("/hotel/sortcount")
public class HotelSortByReviewCntController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HotelService hotelService = new HotelServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/hotel/scorecount [POST] 요청 성공");
		
		List<SortedHotel> reviewView = hotelService.review();
		//for(Hotel m : reviewView) { System.out.println(m); }
		
		req.setAttribute("reviewView", reviewView);
		req.getRequestDispatcher("/WEB-INF/views/hoetlSortByReview.jsp").forward(req, resp);
		
	}

}
