package kr.co.Farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.Farmstory2.service.BoardService;

@WebServlet("/board/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BoardService service = BoardService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cateName = service.titNameFormat(req.getParameter("cate"), req.getParameter("tit"));
		String search = req.getParameter("search");
		
		// 게시물 페이징
		int limitStart = service.boardPaging(req, cateName, search);
		
		// 게시물 불러오기
		req.setAttribute("vos", service.selectArticles(cateName, limitStart, search));
		req.getRequestDispatcher("/board/list.jsp").forward(req, resp);
	}
}
