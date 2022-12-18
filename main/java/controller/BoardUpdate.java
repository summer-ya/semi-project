package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardFile;
import dto.Board;
import service.face.BoardService;
import service.impl.BoardServiceImpl;


@WebServlet("/update")
public class BoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	//서비스 객체
	private BoardService boardService = new BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("BoardUpdateController doGet() - 전달파라미터 boardno : " + req.getParameter("boardno"));
		
		//전달파라미터 저장 객체 얻기
		Board boardno = boardService.getBoardno(req);
		System.out.println("BoardUpdateController doGet() - 전달파라미터 객체 : " + boardno);
		
		
		
		//상세보기 결과 조회
		Board updateBoard = boardService.view(boardno);
		System.out.println("BoardUpdateController doGet() - 상세보기 객체 : " + updateBoard);
		
		//조회결과 MODEL값 전달
		req.setAttribute("updateBoard", updateBoard);

		
		
		//작성자 닉네임 전달
		req.setAttribute("writerNick", boardService.getWriteNick(updateBoard));
		
		
		
		//첨부파일 정보 조회
		BoardFile boardFile = boardService.viewFile(updateBoard);
		
		//첨부파일 정보를 MODEL값 전달
		req.setAttribute("boardFile", boardFile);
		
		
		
		//VIEW 지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/boardupdate.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		boardService.update(req);
		
		resp.sendRedirect("/list");
		
	}
}
