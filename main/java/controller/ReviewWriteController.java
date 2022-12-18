package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Hotel;
import service.face.ReviewService;
import service.impl.ReviewServiceImpl;

//with 아리
//아리님이 넘겨주는 파라미터 : hotel_no, pay_no, booking_no, room_type, user_no
//아리님이 넘겨주는 방식 : get
@WebServlet("/review/write")
public class ReviewWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ReviewService reviewService = new ReviewServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("hotel_no",request.getParameter("hotel_no"));
		
		//n번 호텔의 리뷰를 작성하겠다는 요청이 넘어옴
		int hotel_no = Integer.parseInt(request.getParameter("hotel_no"));
		//jsp에서 호텔 사진 필요하므로 호텔 사진 불러들이는 service 실행
		Hotel hotel = reviewService.selectHotelByHotelNo(request,hotel_no);
		String hotelPhotoLocation = hotel.getHotel_photo();
		String hotel_name = hotel.getHotel_name();
		//리뷰 작성 jsp에 호텔사진위치 넘겨주기
		request.setAttribute("hotelPhotoLocation",hotelPhotoLocation);
		//쿼리스트링에서 pay_no, booking_no, room_type 가져오기
		String pay_no= request.getParameter("pay_no");
		String booking_no = request.getParameter("booking_no");
		String room_type = request.getParameter("room_type");
		String user_no = request.getParameter("user_no");
		System.out.println();
		//jsp에 값 넘겨주기 
		request.setAttribute("pay_no",pay_no);
		request.setAttribute("booking_no",booking_no);
		request.setAttribute("room_type",room_type);
		request.setAttribute("hotel_name",hotel_name);
		request.setAttribute("user_no",user_no);

		request.getRequestDispatcher("/WEB-INF/views/reviewWrite.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자가 작성한 리뷰값들이 post로 넘어옴
		//리뷰 등록하는 서비스 실행
		reviewService.writeReview(request);
		String hotel_no = request.getParameter("hotel_no");
		//리뷰 등록 완료. 원래 페이지로 돌아가는 리다이렉트
		response.sendRedirect("/hotel/list");
	}

}