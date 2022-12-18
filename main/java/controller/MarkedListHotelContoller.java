package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Hotel;
import service.face.MarkService;
import service.impl.MarkServiceImpl;


@WebServlet("/mark/list")
public class MarkedListHotelContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MarkService markService = new MarkServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/mark/list GET");
		
		//유저 email이 session 으로 넘어옴 -> session으로 넘어오니까 service에서 get하면 되니 controller에서 get할 필요 없음
		
		//해당 유저가 mark한 호텔들을 보여주는 서비스 작동
		List<Hotel> markedHotelList = markService.markedHotelList(request);
		
		request.setAttribute("markedHotelList", markedHotelList);
		
		request.getRequestDispatcher("/WEB-INF/views/markedHotelList.jsp").forward(request, response);		
	

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

}
