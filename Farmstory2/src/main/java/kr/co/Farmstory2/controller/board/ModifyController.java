package kr.co.Farmstory2.controller.board;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Farmstory2.service.BoardService;

@WebServlet("/board/modify.do")
public class ModifyController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BoardService service = BoardService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> vos = service.selectArticle(req.getParameter("no"));
		req.setAttribute("avo", vos.get("avo"));
		req.getRequestDispatcher("/board/modify.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service.updateArticle(req.getParameter("title"), req.getParameter("content"), req.getParameter("no"));
		// TODO - search 넘길때 한글이 깨짐 -> view.do로 바로 보내면 윈도우창의 한글은 처리가 안됨 -> 중간 페이지나 컨트롤러 또는 필터를 넣어서 get에서 get방식으로 보내기
		resp.sendRedirect("/Farmstory2/board/view.do?cate="+req.getParameter("cate")+"&tit="+req.getParameter("tit")+"&pg="+req.getParameter("pg")+"&no="+req.getParameter("no")+"&result=201");
	}
}
