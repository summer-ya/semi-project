package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Hotel;
import dto.Room;
import service.face.HotelService;
import service.face.MarkService;
import service.face.RoomService;
import service.impl.HotelServiceImpl;
import service.impl.MarkServiceImpl;
import service.impl.RoomServiceImpl;

@WebServlet("/hotel/detail")
public class HotelDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HotelService hotelService = new HotelServiceImpl();
	private MarkService markService = new MarkServiceImpl();
	private RoomService roomService = new RoomServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/hotel/detail [GET] 호출 성공");
		int hotel_no = Integer.parseInt(req.getParameter("hotel_no"));

		HttpSession session = req.getSession();
		Integer user_no = (Integer) session.getAttribute("user_no");
		
		// 좋아요 클릭상태 체크
		// 클릭전 -> 빈하트, 클릭후 -> 꽉찬하트로 보여지게함
		if (user_no != null) {
			int check = markService.check(hotel_no, user_no);
			System.out.println("체크 :" + check);
			req.setAttribute("like_check", check);
		}

		Hotel hoteldetail = hotelService.detail(hotel_no);
		req.setAttribute("hotelDetail", hoteldetail);

		// 클릭한 호텔 리뷰 평균점수 구하기
		double reviewScore = hotelService.selectReview(hotel_no);
		System.out.println("리뷰 평균점수 :" + reviewScore);

		// 클릭한 호텔 리뷰 개숫 구하기
		int reviewCnt = hotelService.reviewCne(hotel_no);
		System.out.println("리뷰 갯수 : " + reviewCnt);

		req.setAttribute("reviewCnt", reviewCnt);
		req.setAttribute("reviewScore", reviewScore);

		
		List<Room> roominfo = roomService.detail(hotel_no);
		req.setAttribute("roominfo", roominfo);
		req.getRequestDispatcher("/WEB-INF/views/hotelDetail.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/hotel/detail [POST] 호출 성공");
		// ajax로 전달된 파라미터 data(no) 추출
		int hotel_no = Integer.parseInt(req.getParameter("hotel_no"));

		Hotel hotelinfo = hotelService.detail(hotel_no);
		req.setAttribute("hotelinfo", hotelinfo);
		req.getRequestDispatcher("/WEB-INF/views/hotelInfo.jsp").forward(req, resp);

	}

}
