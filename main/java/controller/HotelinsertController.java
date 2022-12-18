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

import dto.Hotel;
import service.face.HotelService;
import service.impl.HotelServiceImpl;

@WebServlet("/hotel/insert")
public class HotelinsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HotelService hotelService = new HotelServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/hotelInsertForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 서버상의 실제경로를 찾아냄
		ServletContext context = getServletContext();
		String path = context.getRealPath("upload");
		System.out.println(path);

		File directory = new File(path);
		directory.mkdir();

		System.out.println(path);
		int maxPostSize = 10 * 1024 * 1024; // 10MB
		String encType = "UTF-8";

		// MultipartRequest 객체 생성
		// -> 파일 업로드 수행
		MultipartRequest mul = new MultipartRequest(req, path, maxPostSize, encType, new DefaultFileRenamePolicy());

		Hotel hotelparam = new Hotel();

		// 파라미터 얻기
		hotelparam.setHotel_name(mul.getParameter("hotel_name"));
		hotelparam.setHotel_addr(mul.getParameter("hotel_addr"));
		hotelparam.setHotel_tel(mul.getParameter("hotel_tel"));
		String hotel_info = mul.getParameter("hotel_info");
		hotel_info = hotel_info.replaceAll("\r\n", "<br>");
		hotelparam.setHotel_info(hotel_info);
		hotelparam.setHotel_photo(mul.getFilesystemName("hotel_photo"));
		hotelparam.setMark_hit(Integer.parseInt(mul.getParameter("mark_hit")));
		hotelparam.setHotel_intime(mul.getParameter("hotel_intime"));
		hotelparam.setHotel_outtime(mul.getParameter("hotel_outtime"));

		// 파라미터 저장
		Hotel insertResult = hotelService.join(hotelparam);
		System.out.println(insertResult);

		req.getRequestDispatcher("/WEB-INF/views/hotelInsertOk.jsp").forward(req, resp);

	}

}
