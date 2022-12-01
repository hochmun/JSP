package kr.co.Farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.Farmstory2.service.BoardService;

@WebServlet("/board/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BoardService service = BoardService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service.deleteArticle(req.getParameter("no"), req.getParameter("file"), req.getServletContext().getRealPath("/file"));
		// TODO - 삭제 후 리스트로 바로 가지 않고 삭제 확인 JSP 페이지 만들어서 삭제완료 메세지 출력후 리스트 페이지로 이동 하게 만들기
		resp.sendRedirect("/Farmstory2/board/list.do?cate="+req.getParameter("cate")+"&tit="+req.getParameter("tit")+"&pg="+req.getParameter("pg"));
	}
}
