package service.face;

import java.util.List;

import dto.Hotel;
import dto.SortedHotel;

public interface HotelService {
	
	/**
	 * 클릭한 호텔 상세정보 
	 * 
	 * @param hotel_no
	 * @return
	 */
	public Hotel detail(int hotel_no);
	
	/**
	 * 찜한 호텔의 찜횟수+1
	 * 
	 * @param hotel_no
	 * @return
	 */
	public Hotel insertMark(int hotel_no);
	
	
	/**
	 * 호텔 DB삽입
	 * 
	 * @param hotelparam
	 * @return
	 */
	public Hotel join(Hotel hotelparam);

	
	/**
	 * DB에 삽입된 호텔 리스트 조회
	 * 
	 * @return
	 */
	public List<Hotel> list();

	
	/**
	 * 해당 호텔 평균점수 구하기
	 * 
	 * @param hotel_no
	 * @return
	 */
	public double selectReview(int hotel_no);

	
	/**
	 * 해당 호텔 리뷰개수 구하기
	 * 
	 * @param hotel_no
	 * @return
	 */
	public int reviewCne(int hotel_no);

	/**
	 * 리뷰 많은 순으로 정렬하여 리스트 보여주기
	 * 	 
	 * @return
	 */
	List<SortedHotel> review();

	/**
	 * 평점 높은 순으로 정렬하여 리스트 보여주기
	 * 
	 * @return
	 */
	public List<SortedHotel> sortScore();

	/**
	 * 최신순으로 정렬하여 리스트 보여주기
	 * 
	 * @return
	 */
	public List<SortedHotel> sortLatest();


	
	
	
}
