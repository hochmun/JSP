package kr.co.Farmstory3.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import kr.co.Farmstory3.service.LoadingService;

@WebServlet("/LoadingPage.do")
public class LoadingPageController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	private LoadingService service = LoadingService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		Writer out = resp.getWriter();
		String success = req.getParameter("success");
		
		
		if(success == null || success == "") resp.sendRedirect("/Farmstory3/");
		else {
			if(success.equals("100")) service.loginFail(out);
			if(success.equals("101")) service.logout(out);
			if(success.equals("102")) service.insertUser(out);
			
			if(success.equals("200")) service.deleteArticle(out, req);
			if(success.equals("201")) service.modifyArticle(out, req);
		}
		
		out.flush();
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
