package kr.co.Farmstory3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.Farmstory3.service.BoardService;

@WebServlet("/index.do")
public class IndexController extends HttpServlet {

	// TODO - 메인 페이지 표시 되는 게시물 없을 시 표시할 게시물 없음 표시 띄우기 (enhancement)
	
	private static final long serialVersionUID = 1L;
	private BoardService service = BoardService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// latest 게시물 정보 가져오기
		req.setAttribute("vos", service.selectArticleLatests("grow", "school", "story"));
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
