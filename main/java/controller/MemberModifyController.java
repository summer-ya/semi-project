package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.Semi_UserService;
import service.impl.Semi_UserServiceImpl;


@WebServlet("/member/modify")
public class MemberModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Semi_UserService semi_UserService = new Semi_UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/member/modify [GET] 실행");
		
		request.getRequestDispatcher("/WEB-INF/views/memberModify.jsp").forward(request,response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/member/modify [POST] 실행");
		
		int user_no = (int) request.getSession().getAttribute("user_no");
		System.out.println("user_no"+user_no);
		
		semi_UserService.modify(request);
		
		response.sendRedirect("/main");
	}

}
