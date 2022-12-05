package kr.co.Jboard2.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.co.Jboard2.Service.user.UserService;
import kr.co.Jboard2.vo.userVO;

@WebServlet("/user/myInfo.do")
public class MyInfoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService service = UserService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/user/myInfo.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		userVO vo = new userVO();
		vo.setUid(req.getParameter("uid"));
		vo.setPass(req.getParameter("pass"));
		vo.setName(req.getParameter("name"));
		vo.setNick(req.getParameter("nick"));
		vo.setEmail(req.getParameter("email"));
		vo.setHp(req.getParameter("hp"));
		vo.setZip(req.getParameter("zip"));
		vo.setAddr1(req.getParameter("addr1"));
		vo.setAddr2(req.getParameter("addr2"));
		
		int result = 0;
		
		if(vo.getPass().equals("") || vo.getPass() == null ) result = service.updateUserNotPass(vo);
		else result = service.updateUser(vo);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		resp.getWriter().print(json.toString());
	}
}
