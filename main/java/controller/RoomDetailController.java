package controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Room;
import service.face.RoomService;
import service.impl.RoomServiceImpl;


@WebServlet("/room/detail")
public class RoomDetailController extends HttpServlet {
   private static final long serialVersionUID = 1L;

   //서비스 객체
   RoomService roomService = new RoomServiceImpl();


   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


      int hotel_no = Integer.parseInt(req.getParameter("hotel_no"));
	  HttpSession session = req.getSession();
	  Integer user_no = (Integer) session.getAttribute("user_no");
	  System.out.println("user_no : " + user_no);
	  
      List<Room> roominfo = roomService.detail(hotel_no);
      req.setAttribute("roominfo", roominfo);
      req.setAttribute("user_no", user_no);
      req.getRequestDispatcher("/WEB-INF/views/roomDetail.jsp").forward(req, resp);

   }
 
}