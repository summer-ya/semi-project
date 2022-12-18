package service.impl;

import java.sql.Connection;
import java.util.List;

import common.JDBCTemplate;
import dao.face.HotelDao;
import dao.impl.HotelDaoImpl;
import dto.Hotel;
import dto.SortedHotel;
import service.face.HotelService;

public class HotelServiceImpl implements HotelService {
	
	private HotelDao hotelDao = new HotelDaoImpl();
	
	@Override
	public Hotel detail(int hotel_no) {
		
		Connection conn = JDBCTemplate.getConnection();

		return hotelDao.selectHotelByHotelNo(conn, hotel_no);
		
	}
	
	@Override
	public Hotel insertMark(int hotel_no) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( hotelDao.insertMarkByhotelno(conn, hotel_no) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		Hotel hotel = hotelDao.selectHotelByHotelNo(conn, hotel_no);
		
		return hotel;
	}
	
	@Override
	public Hotel join(Hotel hotelparam) {

		Connection conn = JDBCTemplate.getConnection();

		int nextSeq = hotelDao.hotelNextSeq(conn);
		System.out.println(nextSeq);
		int result = hotelDao.hotelInsert(conn, hotelparam, nextSeq);

		if (result > 0) {
			JDBCTemplate.commit(conn);
			return hotelparam;
		} else {
			JDBCTemplate.rollback(conn);
			return null;
		}
	}
	
	@Override
	public List<Hotel> list() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		return hotelDao.selectAllHotelList(conn);
	}
	
	@Override
	public double selectReview(int hotel_no) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		return hotelDao.selectReviewScoreByHotelNo(conn, hotel_no);
	}
	
	@Override
	public int reviewCne(int hotel_no) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		return hotelDao.selectReviewCntByHotelNo(conn, hotel_no);
	}
	
	@Override
	public List<SortedHotel> review() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		return hotelDao.selectHotelListByReviewCnt(conn);
	}
	
	@Override
	public List<SortedHotel> sortScore() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		return hotelDao.selectHotelListByScore(conn);
	}
	
	@Override
	public List<SortedHotel> sortLatest() {
		
		Connection conn = JDBCTemplate.getConnection();
				
		return hotelDao.selectHotelListByLatest(conn);
	}

}

