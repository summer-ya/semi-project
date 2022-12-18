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


@WebServlet("/maxMarked")
public class MaxMarkedHotelListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MarkService markService = new MarkServiceImpl();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/maxMarked 실행()");
		List<Hotel> list = markService.maxMarkedHotelList(request);
		request.setAttribute("list",list);
		
		request.getRequestDispatcher("/WEB-INF/views/maxMarkHotel.jsp").forward(request, response);	
	}


}
