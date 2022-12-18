package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ReviewImage;
import service.face.ReviewService;
import service.impl.ReviewServiceImpl;

@WebServlet("/review/list")
public class ReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ReviewService reviewService = new ReviewServiceImpl();
       
	//GET : 리뷰 최신순 불러오기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/review [GET]");

		//n번 호텔의 리뷰를 보겠다-> n번이 쿼리스트링으로 넘어옴
		int hotel_no = Integer.parseInt(request.getParameter("hotel_no"));
		System.out.println("hotel_no"+request.getParameter("hotel_no"));
		request.setAttribute("hotel_no",hotel_no);
		
		
		//최신순으로 볼지 or 별점순으로 볼지 넘어옴
		String dateOrScore = request.getParameter("selectedOption");
		String url = "";
		
		if(dateOrScore.equals("byDate")) {	//최신순으로 보겠다는 요청이 넘어옴
			
			//넘겨줄 jsp url 설정
			url = "/WEB-INF/views/reviewListByDate.jsp";
			
			List<Map<String, Object>> list = reviewService.reviewListByDate(request,hotel_no);


			request.setAttribute("list",list);
			
		} else {     //별점순으로 보겠다는 요청이 넘어옴
			
			//넘겨줄 jsp url 설정
			url = "/WEB-INF/views/reviewListByScore.jsp";
			
			List<Map<String, Object>> list = reviewService.reviewListByScore(request,hotel_no);
			//불러온 값들 JSP에 떠넘기기
			request.setAttribute("list",list);
			
		}
		
		//if문에 따라 달라진 url을 호출함
		  request.getRequestDispatcher(url).forward(request, response);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		

	}

}