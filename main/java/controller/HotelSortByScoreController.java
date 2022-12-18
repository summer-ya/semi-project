package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Hotel;
import dto.SortedHotel;
import service.face.HotelService;
import service.impl.HotelServiceImpl;

@WebServlet("/hotel/score")
public class HotelSortByScoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HotelService hotelService = new HotelServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/hotel/score [POST] 호출 성공");
		
	System.out.println("/hotel/score [POST] 요청 성공");
		
		List<SortedHotel> scoreView = hotelService.sortScore();
		for(SortedHotel m : scoreView) { System.out.println(m); }
		
		req.setAttribute("scoreView", scoreView);
		req.getRequestDispatcher("/WEB-INF/views/hotelSortByScore.jsp").forward(req, resp);
		
	}
	
}
