package kr.co.Jboard2.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.Jboard2.Service.user.UserService;
import kr.co.Jboard2.vo.userVO;

@WebServlet("/user/logout.do")
public class LogoutController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService service = UserService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sess = req.getSession();
		userVO vo = (userVO) sess.getAttribute("sessUser");
		
		// 세션 해제0
		sess.removeAttribute("sessUser");
		sess.invalidate();
		
		// 쿠키 삭제
		Cookie cookie = new Cookie("SESSID", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		
		// 데이터베이스 사용자 sessId update
		service.updateUserForSessionOut(vo.getUid());
		
		resp.sendRedirect("/Jboard2/user/login.do?success=200");
		
	}
}
