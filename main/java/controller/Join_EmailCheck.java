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

@WebServlet("/emailChk")
public class Join_EmailCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체
	private Semi_UserService sUserService = new Semi_UserServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String useremail = req.getParameter("useremail");
		
		Semi_User sUser = new Semi_User(); 
		
		sUser.setUser_email(useremail);
		
		int res = sUserService.existsEmail(sUser);
		
		resp.getWriter().print((res > 0) ? 1 : 0);
		
	}

}









