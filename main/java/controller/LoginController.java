package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Semi_User;
import service.face.Semi_UserService;
import service.impl.Semi_UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체
	private Semi_UserService sUserService = new Semi_UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("##################");
		
		//전달파라미터로 로그인 정보 얻기
		Semi_User sUser = sUserService.getLoginUser(req);
		
		//로그인 인증
		boolean login = sUserService.login(sUser);
		System.out.println(login);
						
		if(login) {
			
			
			
			//로그인 사용자 정보 얻어오기
			sUser = sUserService.info(sUser);
			System.out.println("~~" + sUser);
			
			
			//세션정보 저장
			HttpSession session = req.getSession();
			
			session.setAttribute("login", login);
			session.setAttribute("username", sUser.getUser_name());
			session.setAttribute("userpw", sUser.getUser_pw());
			session.setAttribute("user_email", sUser.getUser_email());
			session.setAttribute("user_no", sUser.getUser_no());
			
		}
		
		resp.sendRedirect("/main");
		
	}

}





