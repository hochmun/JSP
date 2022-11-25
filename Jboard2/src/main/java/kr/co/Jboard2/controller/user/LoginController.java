package kr.co.Jboard2.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.Service.user.UserService;
import kr.co.Jboard2.vo.userVO;

@WebServlet("/user/login.do")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService service = UserService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		userVO vo = service.selectUser(req.getParameter("uid"), req.getParameter("pass"));
		
		if(vo.getUid() != null) {
			// 회원이 맞을경우
			HttpSession sess = req.getSession();
			sess.setAttribute("sessUser", vo);
			
			if(req.getParameter("auto") != null) {
				logger.debug("auto cookie create");
				
				String sessId = sess.getId();
				
				// 쿠키 생성
				Cookie cookie = new Cookie("SESSID", sessId);
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*3);
				resp.addCookie(cookie);
				
				// sessId 데이터베이스 저장
				service.updateUserForSession(sessId, vo.getUid());
			}
			
			resp.sendRedirect("/Jboard2/list.do");
		} else {
			// 회원이 아닐경우
			resp.sendRedirect("/Jboard2/user/login.do?success=100");
		}
	}
}
