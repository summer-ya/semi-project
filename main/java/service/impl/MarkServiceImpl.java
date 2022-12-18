package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.MarkDao;
import dao.impl.MarkDaoImpl;
import dto.Hotel;
import dto.Mark;
import service.face.MarkService;


public class MarkServiceImpl implements MarkService {

	private MarkDao markDao = new MarkDaoImpl();
	
	@Override
	public Mark update(int hotel_no, int user_no) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if(markDao.updateMark(conn, hotel_no, user_no) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		Mark mark = markDao.selectMark(conn, hotel_no, user_no);
		
		return mark;
		
	}
	
	@Override
	public int check(int hotel_no, int user_no) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		return markDao.checkMarkCount(conn, hotel_no, user_no);
	}
	
	@Override
	public void deleteMark(int hotel_no, int user_no) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		markDao.deleteMark(conn, hotel_no, user_no);
		
	}
	
//	@Override
//	public List<Mypage> total(int user_no) {
//		
//		Connection conn = JDBCTemplate.getConnection();
//		
//		markDao.myPageInfo(conn, user_no);
//		
//		return markDao.myPageInfo(conn, user_no);
//	}
	
	@Override
	public List<Hotel> markedHotelList(HttpServletRequest request) {

		Connection conn = JDBCTemplate.getConnection();
		String user_email = (String) request.getSession().getAttribute("user_email");

		return markDao.markedHotelList(conn, user_email);
		
	}

	@Override
	public List<Hotel> maxMarkedHotelList(HttpServletRequest request) {
		Connection conn = JDBCTemplate.getConnection();
		return markDao.selectHotelOrderbyMarkhit(conn);
	}
}