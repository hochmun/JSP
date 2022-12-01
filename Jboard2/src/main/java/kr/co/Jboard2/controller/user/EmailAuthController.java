package kr.co.Jboard2.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.co.Jboard2.Service.user.UserService;

@WebServlet("/user/emailAuth.do")
public class EmailAuthController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService service = UserService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String receiverEmail = req.getParameter("email");
		String name = req.getParameter("name");
		String uid = req.getParameter("uid");
		
		int result2 = 1;
		
		// 보안 - 이름 값과 아이디 값이 동시에 들어올때 또는 이메일 값이 없을 때
		if((name != null && uid != null) || receiverEmail == null) {
			resp.sendRedirect("/Jboard2/");
			return;
		}
		
		// 이름 값이 있을경우 이름 이메일 중복 검사
		if (name != null) {  
			result2 = service.selectCountUserName(name, receiverEmail);
		}
		
		// 아이디 값이 있을경우 아이디 이메일 중복 검사
		if (uid != null) { 
			result2 = service.selectCountUserUid(uid, receiverEmail);
		}
		
		int[] result = new int [2];
		
		if (result2 == 1) { // 기본 값 1, 중복 검사 후 중복 없을시 0
			result = service.sendEmailCode(receiverEmail);
		} else { // 중복 값 0 일시 실패 리턴
			result[0] = 0;
			result[1] = 0;
		}
		
		// Json 출력
		JsonObject json = new JsonObject();
		json.addProperty("status", result[0]);
		json.addProperty("code", result[1]);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
