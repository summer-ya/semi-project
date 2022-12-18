package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.Semi_UserDao;
import dao.impl.Semi_UserDaoImpl;
import dto.Review;
import dto.ReviewImage;
import dto.Semi_User;
import service.face.Semi_UserService;

public class Semi_UserServiceImpl implements Semi_UserService {
	
	//Dao객체
	private Semi_UserDao sUserDao = new Semi_UserDaoImpl();

	@Override
	public Semi_User getLoginUser(HttpServletRequest req) {
		
		Semi_User sUser = new Semi_User();
		
		sUser.setUser_email(req.getParameter("useremail"));
		sUser.setUser_pw(req.getParameter("userpw"));
		
		return sUser;
	}
	
	
	@Override
	public boolean login(Semi_User sUser) {
		
		if(sUserDao.selectCntByUserEmailPw(JDBCTemplate.getConnection(), sUser) > 0) {
			return true;
		} else {
		return false;
		}
	}

	
	@Override
	public Semi_User info(Semi_User sUser) {
		return sUserDao.selectUserByUseremail(JDBCTemplate.getConnection(), sUser);
	}
	
	
	//--------------------------------------비밀번호 찾기------------------------------------------------------

	
	@Override
	public Semi_User getEmailPhone(HttpServletRequest req) {
		
		Semi_User sUser = new Semi_User();
		
		sUser.setUser_email(req.getParameter("useremail"));
		sUser.setUser_phone(req.getParameter("userphone"));
		
		return sUser;
	}
	
	
	@Override
	public int exists(Semi_User sUser) {
		if(sUserDao.selectCntByEmailPhone(JDBCTemplate.getConnection(), sUser) > 0) {
			return 1; //조회 성공
		} else {
		return 0; //실패
		}
	}
	
	
	
	@Override
	public String getRamdomPassword(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 
				'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
				'U', 'V', 'W', 'X', 'Y', 'Z' };

		  int idx = 0;
		  StringBuffer sb = new StringBuffer();
		  
		  for (int i = 0; i < len; i++) {
			
			  idx = (int) (charSet.length * Math.random());
			  sb.append(charSet[idx]);
		  }

		  return sb.toString();
	}
	
	
	@Override
	public Semi_User createTempPw(Semi_User sUser) {

		if(exists(sUser) == 1) {
			
			//dto에 임시비번 10자리 생성해서 저장
			sUser.setUser_pw(getRamdomPassword(10));
		}
		return sUser;
	}
	
	
	@Override
	public boolean isOkUpdateTempPw(Semi_User sUser) {
		Connection conn = JDBCTemplate.getConnection();
		
		if(sUserDao.updateTempPw(conn, sUser) > 0) {
			JDBCTemplate.commit(conn);
			return true; //업뎃 성공
		} else {
			JDBCTemplate.rollback(conn);
			return false; //실패
		}
		
	}
	
	
	//---------------------------------------회원가입-----------------------------------------------------

	@Override
	public Semi_User getJoinMember(HttpServletRequest req) {
		
		//한글 인코딩
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Semi_User sUser = new Semi_User();
		
		sUser.setUser_name(req.getParameter("username"));
		sUser.setUser_email(req.getParameter("useremail"));
		sUser.setUser_phone(req.getParameter("userphone"));
		sUser.setUser_pw(req.getParameter("userpw"));
		
		return sUser;
	}
	
	
	@Override
	public int existsEmail(Semi_User sUser) {
		if(sUserDao.selectCntByUserEmail(JDBCTemplate.getConnection(), sUser) > 0) {
			return 1;
		} else {
		return 0;
		}
	}
	
	@Override
	public void join(Semi_User sUser) {
		
		//db연결
		Connection conn = JDBCTemplate.getConnection();
		
		//인서트 잘 됐으면 커밋 아니면 롤백
		if(sUserDao.insert(conn, sUser) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}

//-----------------------------회원 정보 수정-----------------------------------------------------------
	@Override
	public void modify(HttpServletRequest req) {
		//--- 첨부파일 추가하여 회원 정보 시작---
		System.out.println("writeReview()");

		// 회원 정보 수정을 요청한 사람의 user_no 추출
		int user_no = (int) req.getSession().getAttribute("user_no");

		
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
		
		//유저 DTO객체
		Semi_User semi_User = new Semi_User();
		
		//객체에 user_no 저장
		semi_User.setUser_no(user_no);
		
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
				if( "user_name".equals(key) ) {
					semi_User.setUser_name(value);
				}
				if( "user_pw".equals(key) ) {
					semi_User.setUser_pw(value);
				}
				
				System.out.println(semi_User);
								
			} //--------------수정된 이름, 수정된 pw 값 받아 객체에 저장 완료

			
		
			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
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
				semi_User.setUser_pic(rename);
				
			} // if( !item.isFormField() ) end
		}// while( iter.hasNext() ) end					//객체에 프로필 이미지 user_pic 저장 완료

		
		//--- DB에 최종 데이터 삽입하기 위한 준비 ---
		Connection conn = JDBCTemplate.getConnection();
		
		int res = 0;
		
		//전달 파라미터 넘겨주기
		res = sUserDao.updateInfo(conn, semi_User);
		
		if( res > 0 ) {
			System.out.println("회원정보 수정 성공!");
			JDBCTemplate.commit(conn);
		} else {
			System.out.println("회원정보 수정 실패!");
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	
	
}











