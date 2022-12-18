package controller;

import java.io.File;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.Room;
import service.face.RoomService;
import service.impl.RoomServiceImpl;


@WebServlet("/room/info")
public class Roominfo extends HttpServlet {
   private static final long serialVersionUID = 1L;

   private RoomService roomService = new RoomServiceImpl();

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      req.getRequestDispatcher("/WEB-INF/views/roomAdd.jsp").forward(req, resp);

   }
   
   

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


      // 서버상의 실제경로를 찾아냄
      ServletContext context = getServletContext(); 
      String path = context.getRealPath("upload");

      File directory = new File(path);
      directory.mkdir();

      System.out.println(path);
      int maxPostSize = 10 * 1024 * 1024; // 10MB
      String encType="UTF-8";

      // MultipartRequest 객체 생성
      //   -> 파일 업로드 수행
      MultipartRequest mul = new MultipartRequest(req, path, maxPostSize, encType, new DefaultFileRenamePolicy());

      Room roomparam = new Room();


      roomparam.setHotel_no(Integer.parseInt(mul.getParameter("hotel_no")));
      roomparam.setRoom_type(mul.getParameter("room_type"));
      roomparam.setPeople(mul.getParameter("people"));
      roomparam.setMax_people(mul.getParameter("max_people"));
      roomparam.setRoom_price(mul.getParameter("room_price"));
      roomparam.setRoom_img(mul.getFilesystemName("room_img"));

      System.out.println("room param "+   roomparam);

      // 파라미터 저장
      Room insertResult = roomService.add(roomparam);
      System.out.println(insertResult);

      req.getRequestDispatcher("/WEB-INF/views/roomcheck.jsp").forward(req, resp);

   }
  
}