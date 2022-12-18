package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Hotel;
import dto.SortedHotel;

public interface HotelDao {
	
	public Hotel selectHotelByHotelNo(Connection conn, int hotel_no);

	public int insertMarkByhotelno(Connection conn, int hotel_no);

	public int hotelInsert(Connection conn, Hotel hotelparam, int nextSeq);

	public int hotelNextSeq(Connection conn);

	public List<Hotel> selectAllHotelList(Connection conn);

	public double selectReviewScoreByHotelNo(Connection conn, int hotel_no);

	public int selectReviewCntByHotelNo(Connection conn, int hotel_no);

	public List<SortedHotel> selectHotelListByReviewCnt(Connection conn);

	public List<SortedHotel> selectHotelListByScore(Connection conn);

	public List<SortedHotel> selectHotelListByLatest(Connection conn);
}
