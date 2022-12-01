package kr.co.Jboard2.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.Jboard2.Service.user.UserService;
import kr.co.Jboard2.vo.userVO;

@WebServlet("/user/findIdResult.do")
public class FindIdResultController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserService service = UserService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		userVO vo = service.selectUserEmail(req.getParameter("email"));
		req.setAttribute("vo", vo);
		
		req.getRequestDispatcher("/user/findIdResult.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
