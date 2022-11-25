package kr.co.Jboard2.controller.list;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.Service.article.ArticleService;

@WebServlet("/delete.do")
public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("DeleteController...");
		service.deleteArticle(req.getParameter("no"), req.getParameter("file"), req.getServletContext().getRealPath("/file"));
		resp.sendRedirect("/Jboard2/list.do?pg="+req.getParameter("pg")+"&result=202");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
