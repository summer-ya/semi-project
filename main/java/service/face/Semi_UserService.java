package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Semi_User;

public interface Semi_UserService {
	
	/**
	 * 로그인 정보 추출
	 * 
	 * @param req
	 * @return
	 */
	public Semi_User getLoginUser(HttpServletRequest req);
	
	
	/**
	 * 로그인 인증 처리
	 * 
	 * @param sUser
	 * @return boolean - true: 인증성공, false: 실패
	 */
	public boolean login(Semi_User sUser);
	
	
	/**
	 * 로그인 한 유저 정보
	 * 
	 * @param sUser
	 * @return
	 */
	public Semi_User info(Semi_User sUser);

	
	//---------------------------비밀번호 찾기-------------------------------

	
	/**
	 * 비밀번호 찾기를 위해 이메일, 폰번호 얻어오기
	 * 
	 * @param req
	 * @return
	 */
	public Semi_User getEmailPhone(HttpServletRequest req);
	
	
	/**
	 * 이메일, 폰번호 조합으로 조회한 회원이 존재하는지
	 * 
	 * @param sUser
	 * @return
	 */
	public int exists(Semi_User sUser);
	
	
	/**
	 * 임시비번 생성 메소드
	 * 
	 * @param len
	 * @return
	 */
	public String getRamdomPassword(int len);
	
	
	/**
	 * 임시비번 생성해서 sUser에 저장
	 * 
	 * @param sUser
	 * @param random
	 * @return
	 */
	public Semi_User createTempPw(Semi_User sUser);
	
	
	/**
	 * 임시비번이 db에 잘 들어갔는지
	 * 
	 * @param sUser
	 */
	public boolean isOkUpdateTempPw(Semi_User sUser);
	
	
	
	//-------------------------회원가입---------------------------------
	
	/**
	 * 회원가입 정보 추출
	 * 
	 * @param req
	 * @return
	 */
	public Semi_User getJoinMember(HttpServletRequest req);
	
	
	/**
	 * 이메일 중복확인
	 * 
	 * @param sUser
	 * @return
	 */
	public int existsEmail(Semi_User sUser);
	
	
	/**
	 * 회원가입 처리
	 * 
	 * @param sUser
	 */
	public void join(Semi_User sUser);


	


	/**
	 * 회원 정보 수정
	 * 
	 * @param req
	 */
	public void modify(HttpServletRequest req);

}












