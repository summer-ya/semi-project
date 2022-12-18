package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Hotel;
import dto.Mark;

public interface MarkService {

	/**
	 * 찜하기 업데이트
	 * 
	 * @param hotelno
	 * @param userno
	 * @return
	 */
	public Mark update(int hotel_no, int user_no);

	/**
	 * 중복값 체크	 *
	 * 
	 * @param hotel2no
	 * @param userno
	 * @return
	 */
	public int check(int hotel_no, int user_no);

	/**
	 * 찜하기 삭제
	 * 	
	 * @param hotel2no
	 * @param userno
	 * @return
	 */
	public void deleteMark(int hotel_no, int user_no);
	
	
	/**
	 * 마이페이지에 반영될 토탈정보
	 * 
	 * @param hotel2no
	 * @param userno
	 * @return
	 */
//	public List<Mypage> total(int userno);
	
	/**
	 * 특정 유저가 찜한 호텔들을 전부 불러오는 메서드
	 * 
	 * @param 
	 * @param 
	 * @return
	 */
	public List<Hotel> markedHotelList(HttpServletRequest request);
	
	
	/**
	 * 최다 찜 순서로 호텔 불러오기 : 메인에 나타남
	 * 
	 * @param 
	 * @param 
	 * @return
	 */
	public List<Hotel> maxMarkedHotelList(HttpServletRequest request);

}