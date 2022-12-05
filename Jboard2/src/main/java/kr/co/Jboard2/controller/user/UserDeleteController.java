package kr.co.Jboard2.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import kr.co.Jboard2.Service.user.UserService;

@WebServlet("/user/userDelete.do")
public class UserDeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService service = UserService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		HttpSession sess = req.getSession();
		
		int result = service.deleteUser(uid);
		
		// 세션 해제
		sess.removeAttribute("sessUser");
		sess.invalidate();
		
		// 쿠키 삭제
		Cookie cookie = new Cookie("SESSID", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		resp.getWriter().print(json.toString());
	}
}
