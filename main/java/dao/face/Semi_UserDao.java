package dao.face;


import java.sql.Connection;

import dto.Semi_User;

public interface Semi_UserDao {
	
	
	/**
	 * 이메일과 비번 동시에 만족하는 회원 수 조회
	 * 
	 * @param sUer
	 * @return int - 0: 존재x, 1: 존재o
	 */
	public int selectCntByUserEmailPw(Connection conn, Semi_User sUer);
	
	
	/**
	 * 이메일 일치하는 회원 정보 조회
	 * 
	 * @param conn
	 * @param sUser
	 * @return
	 */
	public Semi_User selectUserByUseremail(Connection conn, Semi_User sUser);
	
	
	//----------------------------------비밀번호 찾기-------------------------------------------

	
	/**
	 * 이메일과 폰번호 동시에 만족하는 회원 수 조회
	 * 
	 * @param connection
	 * @param sUser
	 * @return
	 */
	public int selectCntByEmailPhone(Connection conn, Semi_User sUser);
	
	
	/**
	 * 생성된 임시비번 db에 업뎃 (이멜/폰 일치하는 유저에 한해)
	 * 
	 * @param conn
	 * @param sUser
	 * @return
	 */
	public int updateTempPw(Connection conn, Semi_User sUser);
	
	
	//-----------------------------------회원가입------------------------------------------

	
	/**
	 * 이메일 중복 확인을 위한 cnt 조회
	 * 
	 * @param conn
	 * @param sUer
	 * @return
	 */
	public int selectCntByUserEmail(Connection conn, Semi_User sUser);
	
	
	/**
	 * 회원정보 삽입
	 * 
	 * @param conn
	 * @param sUser
	 * @return int - 1: 인서트 성공, 0: 인서트 실패
	 */
	public int insert(Connection conn, Semi_User sUser);
	
	
	//---------------------회원 정보 수정---------------------------------------------
	
	public int  updateInfo(Connection conn, Semi_User sUser);
	
}
