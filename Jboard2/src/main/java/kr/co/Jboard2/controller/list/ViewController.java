package kr.co.Jboard2.controller.list;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.Jboard2.Service.article.ArticleService;
import kr.co.Jboard2.vo.FileVO;
import kr.co.Jboard2.vo.articleVO;

@WebServlet("/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		String pg = req.getParameter("pg");
		
		Map<String, Object> vos = service.selectArticle(no); // 게시물 불러오기
		
		req.setAttribute("avo", (articleVO)vos.get("avo")); 
		req.setAttribute("fvo", (FileVO)vos.get("fvo"));
		
		req.getRequestDispatcher("/view.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
