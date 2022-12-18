package service.face;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import dto.Board;
import dto.BoardFile;
import util.Paging;

public interface BoardService {

	

	/**
	 * 게시글 전체 조회
	 * 
	 * @return List<Board> - 게시글 전체 조회 결과 목록
	 */
	public List<Board> getList();

	/**
	 * 전달파라미터를 Board DTO로 저장하여 반환
	 * 
	 * @param req - 요청 정보 객체
	 * @return Board - 전달파라미터 boardno를 저장한 객체
	 */
	public Board getBoardno(HttpServletRequest req);

	/**
	 * 전달된 boardno를 이용하여 게시글을 조회한다
	 * 조회된 게시글의 조회수를 1 증가시킨다
	 * 
	 * @param boardno - 조회할 boardno를 가진 DTO객체
	 * @return Board - 조회된 게시글 정보
	 */
	public Board view(Board boardno);

	/**
	 * 게시글 작성자 ID를 이용하여 usernick 을 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @param viewBoard - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String getWriteNick(Board viewBoard);

	/**
	 * 게시글 전체 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Board> - 게시글 전체 조회 결과 목록
	 */
	public List<Board> getList(Paging paging);

	/**
	 * 게시글 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 객체
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 게시글 작성
	 * 입력한 게시글을 DB에 저장한다
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void write(HttpServletRequest req);

	

	/**
	 * 첨부파일 정보 조회하기
	 * 
	 * @param viewBoard - 첨부파일과 연결된 게시글의 번호
	 * @return BoardFile - 첨부파일 정보 DTO객체
	 */
	public BoardFile viewFile(Board viewBoard);

	/**
	 * 게시글 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void update(HttpServletRequest req);

	/**
	 * 게시글 삭제
	 * 
	 * @param board - 삭제할 게시글 번호 객체
	 */
	public void delete(Board board);

	public List<Board> getBoardSearchList(Paging paging, String keyword);

	public List<Board> getBoardList(Paging paging);

	public Paging getSearchPaging(HttpServletRequest req, String keyword);

}
	
