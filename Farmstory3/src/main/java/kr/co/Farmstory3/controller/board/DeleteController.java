package kr.co.Farmstory3.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.Farmstory3.service.BoardService;

@WebServlet("/board/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BoardService service = BoardService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service.deleteArticle(req.getParameter("no"), req.getParameter("file"), req.getServletContext().getRealPath("/file"));
		String search = req.getParameter("search");
		HttpSession sess = req.getSession();
		sess.setAttribute("search", search);
		sess.setAttribute("success", "200");
		resp.sendRedirect("/Farmstory3/LoadingPage.do?"
				+ "cate="+req.getParameter("cate")
				+ "&tit="+req.getParameter("tit")
				+ "&pg="+req.getParameter("pg"));
	}
}
