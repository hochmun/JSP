package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommonService;
import service.WelcomeServiceImpl;

@WebServlet("/welcome.do")
public class WelcomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	public void requestProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 특정 주소 받기
		CommonService service = WelcomeServiceImpl.getInstance();
		String view = service.requestProc(req, resp);
		
		// 주소 위치 받기
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		// 포워드
		dispatcher.forward(req, resp);
	}
	
}
