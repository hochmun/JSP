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
import kr.co.Jboard2.vo.articleVO;

@WebServlet("/modify.do")
public class ModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("ModifyController...");
		
		req.setAttribute("avo", (service.selectArticle(req.getParameter("no"))).get("avo"));
		
		req.getRequestDispatcher("/modify.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		
		articleVO vo = new articleVO();
		vo.setTitle(req.getParameter("title"));
		vo.setContent(req.getParameter("content"));
		vo.setNo(no);
		
		service.updateArticle(vo);
		
		resp.sendRedirect("/Jboard2/view.do?no="+no+"&pg="+req.getParameter("pg")+"&result=201");
	}
}
