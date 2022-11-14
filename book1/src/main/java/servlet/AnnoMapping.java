package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 날짜 : 2022/11/14
 * 이름 : 심규영
 * 내용 : 요청을 처리할 서블릿 클래스, p455
 */
@WebServlet("/Ch13Servlet/AnnoMapping.do")
public class AnnoMapping extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", "@WebServlet으로 매핑");
		req.getRequestDispatcher("/Ch13Servlet/AnnoMapping.jsp")
		.forward(req, resp);
	}
}
