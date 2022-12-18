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

@WebServlet("/findPw")
public class FindPwController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체
	private Semi_UserService sUserService = new Semi_UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/findPw.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("#######################");
		
		//전달파라미터로 비번찾기(이메일/폰번호) 정보 얻기
		Semi_User sUser = sUserService.getEmailPhone(req);
		System.out.println("전달 파라미터 : " + sUser);
		
		int num = sUserService.exists(sUser);
		System.out.println("전달파라미터 db조회 결과 : " + num);
		
		System.out.println("랜덤비번 dto에 저장 : " + sUserService.createTempPw(sUser));
		
		boolean res = sUserService.isOkUpdateTempPw(sUser);
		System.out.println("db업데이트 결과 : " + res);
		System.out.println("최종 : " + sUser);
		
		if(res) {
			sUser = sUserService.info(sUser);
			System.out.println("tempPw 발급 후 정보 : " + sUser);
			
			HttpSession session = req.getSession();
			
			session.setAttribute("res", res);
			session.setAttribute("userpw", sUser.getUser_pw());
		}
		
		resp.sendRedirect("/findPw/result");
		
	}

}
