package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Semi_User;
import service.face.Semi_UserService;
import service.impl.Semi_UserServiceImpl;


@WebServlet("/loginChk")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체
	private Semi_UserService sUserService = new Semi_UserServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파라미터 받아오기
		String useremail = req.getParameter("useremail");
		String userpw = req.getParameter("userpw");
		
		//객체 생성
		Semi_User sUser = new Semi_User();
		
		sUser.setUser_email(useremail);
		sUser.setUser_pw(userpw);
		
		boolean res = sUserService.login(sUser);
		
		resp.getWriter().print((res == true) ? 0 : 1);
		
	}

}












