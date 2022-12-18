package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Hotel;
import dto.Mark;
import service.face.HotelService;
import service.face.MarkService;
import service.impl.HotelServiceImpl;
import service.impl.MarkServiceImpl;


@WebServlet("/mark")
public class MarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HotelService hotelService = new HotelServiceImpl();
	MarkService markService = new MarkServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mark 찜하기 [POST] 호출성공");

		int hotel_no = Integer.parseInt(req.getParameter("hotel_no"));
		int user_no = Integer.parseInt(req.getParameter("user_no"));

		System.out.println("유저번호 : " + user_no);
		System.out.println("호텔번호 : " + hotel_no);
		// 해당 호텔 찜하기 + 1 후, 반영된 호텔DTO 반환(확인용)
		Hotel markResult = hotelService.insertMark(hotel_no);

		// 중복클릭 체크
		int check = markService.check(hotel_no, user_no);
		System.out.println("체크상태:" +  check);
		req.setAttribute("like_check", check);
		
		if (check == 0) {
			// 찜하기 테이블에 삽입하기 후 조회
			Mark markupdate = markService.update(hotel_no, user_no);
			System.out.println("찜하기 DB 삽입완료");
			
		} else {
			// 찜하기 삭제
			markService.deleteMark(hotel_no, user_no);
			System.out.println("찜하기 DB 삭제완료");
			
		}
		
		// 토탈정보 객체(Join 저장dto)		
		//List<Mypage> myMarkList = markService.total(user_no);

	}

}
