package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.PaymentService;
import service.impl.PaymentServiceImpl;


@WebServlet("/payedList")
public class PayedListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PaymentService paymentService = new PaymentServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//유저의 이메일이 request로 넘어옴: 서비스에서 추출하면 되므로 pass
		
		//유저 이용(결제)한 적 있는 숙소 dto, 객실 dto, 호텔명 String들을 모두 불러오는 service 작동
		List<Map<String, Object>> list = paymentService.selectAllPayedHotel(request);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/payedList.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
