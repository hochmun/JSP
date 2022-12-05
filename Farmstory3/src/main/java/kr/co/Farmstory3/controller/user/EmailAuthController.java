package kr.co.Farmstory3.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.Farmstory3.service.UserService;

@WebServlet("/user/emailAuth.do")
public class EmailAuthController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService service = UserService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("EmailAuthController...");
		
		String receiverEmail = req.getParameter("email");
		String name = req.getParameter("name");
		String uid = req.getParameter("uid");
		
		logger.debug("name : "+name+", uid : "+uid);
		
		int result2 = 1;
		
		// 보안 - 이름 값과 아이디 값이 동시에 들어올때 또는 이메일 값이 없을 때
		if((name != null && uid != null) || receiverEmail == null) {
			resp.sendRedirect("/Farmstory3/");
			return;
		}
		
		// 이름 값이 있을 경우 이름 이메일 매칭 검사
		if (name != null) result2 = service.selectCountUserName(name, receiverEmail);
		// 아이디 값이 있을 경우 아이디 이메일 매칭 검사
		if (uid != null) result2 = service.selectCountUserUid(uid, receiverEmail);
		// 아이디 값이 없고 이름 값이 없을 경우 이메일 중복 검사
		if (uid == null && name == null) result2 = service.selectCountUserEmail(receiverEmail);
		
		int [] result = new int [2];
		if (result2 > 0) result = service.sendEmailCode(receiverEmail);
		else if(result2 == -1) { // 이메일 중복일때
			result[0] = -1;
			result[1] = 0;
		} else { // 아이디 또는 이름 이메일 매칭 실패 일때
			result[0] = 0;
			result[1] = 0;
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("status", result[0]);
		json.addProperty("code", result[1]);
		
		resp.getWriter().print(json.toString());
	}
}
