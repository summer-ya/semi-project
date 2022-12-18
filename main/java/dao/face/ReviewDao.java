package dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import dto.Review;

public interface ReviewDao {
	
	public int selectNextReviewno(Connection conn);
	
	public int insert(Connection conn, Review review);
	
	public List<Map<String, Object>> selectReviewsByDateByHotelNo(Connection conn, int hotel_no);

	public List<Map<String, Object>> selectReviewsByScoreByHotelNo(Connection conn, int hotel_no);

	public int update(Connection conn, int review_no,String review_content);
	
}
