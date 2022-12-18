package service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.Hotel;
import dto.Review;
import dto.ReviewImage;
import dto.Semi_User;


public interface ReviewService {
	
	//-------------리뷰 write에 필요한 메서드
	public void writeReview(HttpServletRequest req);
	
	public Hotel selectHotelByHotelNo(HttpServletRequest request, int hotel_no);
	
	//--------------리뷰 list 최신순에 필요한 메서드
	
	public List< Map<String, Object>> reviewListByDate(HttpServletRequest request, int hotel_no);
	
	
	//--------------리뷰 list 별점순에 필요한 메서드
	
	public List< Map<String, Object>> reviewListByScore(HttpServletRequest request, int hotel_no);
	
	
	public void modifyReview(HttpServletRequest request);
}
