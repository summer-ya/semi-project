package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.HotelDao;
import dao.face.ReviewDao;
import dao.face.ReviewImageDao;
import dao.face.Semi_UserDao;
import dao.impl.HotelDaoImpl;
import dao.impl.ReviewDaoImpl;
import dao.impl.ReviewImageDaoImpl;
import dao.impl.Semi_UserDaoImpl;
import dto.Hotel;
import dto.Review;
import dto.ReviewImage;
import dto.Semi_User;
import service.face.ReviewService;


public class ReviewServiceImpl implements ReviewService {

	ReviewDao reviewDao = new ReviewDaoImpl();
	ReviewImageDao reviewImageDao = new ReviewImageDaoImpl();
	HotelDao hotelDao = new HotelDaoImpl();
	Semi_UserDao semi_UserDao = new Semi_UserDaoImpl();
	
	@Override
	public void writeReview(HttpServletRequest req) {

		//--- 첨부파일 추가하여 리뷰 게시글 작성 처리하기 시작---
		System.out.println("writeReview()");
	
		//--- DB에 최종 데이터 삽입하기 위한 준비 ---
		Connection conn = JDBCTemplate.getConnection();

		// 리뷰 게시글 번호 생성
		int reviewno = reviewDao.selectNextReviewno(conn);
		
		int res = 0;
		int reviewInt = 0;
		
		//multipart/form-data 인코딩 확인
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
				
		//multipart형식이 아닐 경우 처리 중단
		if( !isMultipart ) {
			System.out.println("[ERROR] 파일 업로드 형식 데이터가 아님");
			return;
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리 처리 사이즈 설정
		int maxMem = 1 * 1024 * 1024;	// 1 MB == 1048576 B
		factory.setSizeThreshold(maxMem);

		//서블릿 컨텍스트 객체
		ServletContext context = req.getServletContext();
		
		//임시 파일 저장 폴더
		String path = context.getRealPath("tmp");
		File tmpRepository = new File(path);
		tmpRepository.mkdir();
		factory.setRepository(tmpRepository);

		//파일 업로드 수행 객체
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//파일 업로드 용량 제한
		int maxFile = 10 * 1024 * 1024; // 10MB
		upload.setFileSizeMax(maxFile);

		//파일 업로드된 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//게시글 정보 DTO객체
		Review review = new Review();
		
		//게시글 번호 삽입
		review.setReview_no(reviewno);
		
		
		
		
		//파일아이템의 반복자
		Iterator<FileItem> iter = items.iterator();

		while( iter.hasNext() ) {
			FileItem item = iter.next();
			
			//--- 1) 빈 파일에 대한 처리 ---
			if( item.getSize() <= 0 ) { //전달 데이터의 크기
				//빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}
			//--- 2) 폼 필드에 대한 처리 ---
			if( item.isFormField() ) {
				
				//키(key) 추출하기
				String key = item.getFieldName();
				
				//값(value) 추출하기
				String value = null;
				try {
					value = item.getString("UTF-8"); //한글 인코딩 지정
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
								
				//key에 맞게 value를 DTO에 삽입하기
				if( "pay_no".equals(key) ) {
					review.setPay_no(Integer.parseInt(value));
				}
				if( "hotel_no".equals(key) ) {
					review.setHotel_no(Integer.parseInt(value));
				}
				if( "booking_no".equals(key) ) {
					review.setBooking_no(Integer.parseInt(value));
				}
				if( "review_content".equals(key) ) {
					review.setReview_content(value);
				}
				if( "review_score".equals(key) ) {
					review.setReview_score(Integer.parseInt(value));
				}
				if( "user_no".equals(key) ) {		
					review.setUser_no(Integer.parseInt(value));
				}			
				if( "room_type".equals(key) ) {		
					review.setRoom_type(value);
				}
				if( "user_email".equals(key) ) {		
					review.setUser_email(value);
				}
								
			} 

		
		
			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
				for (int i = 0; i < 1; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Sleep "+i);
				}
				
				
				if (reviewInt == 0) {
				if( reviewDao.insert(conn, review) > 0 ) {
					reviewInt++;
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
					} }
				//게시글 첨부파일 정보 DTO객체
				ReviewImage reviewImage = new ReviewImage();
				
				//저장 파일명 처리
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
				String rename = sdf.format(new Date()); //현재시간
				
				//파일 업로드 폴더
				File uploadFolder = new File( context.getRealPath("upload") );
				uploadFolder.mkdir();
				
				//업로드할 파일 객체 생성하기
				File up = new File(uploadFolder, rename);
				try {
					item.write(up);	//임시파일을 실제 업로드 파일로 출력한다
					item.delete(); //임시파일 제거
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보를 DTO객체에 저장하기
				reviewImage.setOriginname(item.getName());
				reviewImage.setStoredname(rename);

				//리뷰이미지
				reviewImage.setReview_no(reviewno);
				res = reviewImageDao.insert(conn, reviewImage);

				if( res > 0 ) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
			}
		}

			
	
	}

	@Override
	public Hotel selectHotelByHotelNo(HttpServletRequest request, int hotel_no) {
		Connection conn = JDBCTemplate.getConnection();
		Hotel hotel = hotelDao.selectHotelByHotelNo(conn, hotel_no);
		return hotel;
	}

	
	
	@Override
	public List<Map<String, Object>> reviewListByDate(HttpServletRequest request, int hotel_no) {
	
				
		Connection conn = JDBCTemplate.getConnection();

		List<Map<String, Object>> list = reviewDao.selectReviewsByDateByHotelNo(conn, hotel_no);
		
		return list;
	}
	
	
	//-------------------------별점순으로 불러오는 메서드------------------------------

	@Override
	public List<Map<String, Object>> reviewListByScore(HttpServletRequest request, int hotel_no) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<Map<String, Object>> list = reviewDao.selectReviewsByScoreByHotelNo(conn, hotel_no);
		
		return list;
	}


	@Override
	public void modifyReview(HttpServletRequest request) {

		Connection conn = JDBCTemplate.getConnection();
		
		int review_no = Integer.parseInt(request.getParameter("review_no"));
		String review_content = request.getParameter("review_content");
		
		System.out.println("review_no"+review_no);
		System.out.println("review_content"+review_content);
		
		int res = 0;
		res = reviewDao.update(conn,review_no,review_content);
		
		if( res > 0 ) {
			System.out.println("댓글 수정 성공!");
			JDBCTemplate.commit(conn);
		} else {
			System.out.println("댓글 수정 실패!");
			JDBCTemplate.rollback(conn);
		}
		
	}
}
