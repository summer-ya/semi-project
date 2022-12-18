package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Hotel;
import dto.Mark;


public interface MarkDao {

	/**
	 * 찜하기 DB에 삽입
	 * 
	 * @param conn
	 * @param hotelno
	 * @param userno
	 * @return
	 */
	public int updateMark(Connection conn, int hotel_no, int user_no);

	
	/**
	 * DB에 삽입후 Mark2 테이블 조회
	 * 
	 * @param conn
	 * @param hotel2no
	 * @param userno
	 * @return
	 */
	public Mark selectMark(Connection conn, int hotel_no, int user_no);


	/**
	 * 찜한 유저번호, 호텔번호 카운트 조회
	 * 
	 * @param conn
	 * @param hotel2no
	 * @param userno
	 * @return
	 */
	public int checkMarkCount(Connection conn, int hotel_no, int user_no);

	/**
	 * 좋아요 삭제
	 * 
	 * @param conn
	 * @param hotel2no
	 * @param userno
	 */
	public void deleteMark(Connection conn, int hotel_no, int user_no);


	/**
	 * DB에서 찜하기 테이블 + 호텔 테이블 조인해서 조회
	 * 
	 * @param conn
	 * @param hotel2no
	 * @param userno
	 * @return
	 */
	//public List<Mypage> myPageInfo(Connection conn, int user_no);

	
	public List<Hotel> markedHotelList(Connection conn, String user_email);
	
	/**
	 * 최다 찜 순서대로 호텔 불러오기
	 * 
	 * @param conn
	 * @param 
	 * @param 
	 * @return
	 */
	public List<Hotel> selectHotelOrderbyMarkhit(Connection conn);

}