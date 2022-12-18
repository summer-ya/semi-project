package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Board;
import service.face.BoardService;
import service.impl.BoardServiceImpl;
import util.Paging;

@WebServlet("/list")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//Service 객체 생성
	private BoardService boardSerivce = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//전달파라미터에서 현재 페이징 객체 계산하기
		Paging paging = boardSerivce.getPaging(req);
		System.out.println( paging );
		
		//게시글 전체 조회
//		List<Board> boardList = boardSerivce.getList();
		
		//게시글 페이징 목록 조회
		List<Board> boardList = boardSerivce.getList( paging );

		//조회결과 MODEL값 전달
		req.setAttribute("boardList", boardList);
		
		//페이징 객체 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//View 지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/boardlist.jsp").forward(req, resp);
		
	}
	
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		System.out.println("keyword : post요청 성공");
		String keyword = req.getParameter("keyword");
		//String searchWord = req.getParameter("searchWord");
		System.out.println("키워드 : " + keyword);
		
		Paging paging = boardSerivce.getSearchPaging(req,keyword);
		System.out.println(paging);
	
		List<Board> searchlist = boardSerivce.getBoardSearchList(paging, keyword);
		System.out.println(searchlist.toString());
		
		req.setAttribute("keyWord", keyword);
		
		req.setAttribute("paging", paging);
		req.setAttribute("searchlist", searchlist);
		
		req.getRequestDispatcher("/WEB-INF/views/searchList.jsp").forward(req, resp);
	}
}














