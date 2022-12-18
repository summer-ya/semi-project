package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Board;
import dto.BoardFile;
import service.face.BoardService;
import service.impl.BoardServiceImpl;


@WebServlet("/view")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//전달 파라미터 저장 객체 얻기
		Board boardno = boardService.getBoardno(req);
		System.out.println("BoardViewController doGet() - 전달파라미터 객체 : " + boardno);

		Board viewBoard = boardService.view(boardno);
		System.out.println("BoardViewController doGet() - 상세보기 객체 : " + viewBoard);

		//조회결과 MODEL값 전달
		req.setAttribute("viewBoard", viewBoard);


		//작성자 이메일 전달
		req.setAttribute("writerEmail", boardService.getWriteNick(viewBoard));

		//첨부파일 정보 조회
		BoardFile boardFile = boardService.viewFile(viewBoard);

		//첨부파일 정보를 MODEL값 전달
		req.setAttribute("boardFile", boardFile);


		//VIEW 지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/boardview.jsp").forward(req, resp);

	}

}
