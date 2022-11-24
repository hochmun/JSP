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
import kr.co.Jboard2.vo.userVO;

@WebServlet("/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		
		Map<String, Object> vos = service.selectArticle(no); // 게시물 불러오기
		articleVO avo = (articleVO)vos.get("avo");
		
		// 로그인한 아이디와 게시글의 글쓴이의 아이디가 같지 않을때
		if (!((userVO)req.getSession().getAttribute("sessUser")).getUid().equals(avo.getUid())) {
			//게시물 조회수 증가
			service.updateHitCount(no);
		}
		
		req.setAttribute("avo", avo); // 게시물 정보
		req.setAttribute("avo2", service.selectArticleComment(no)); // 댓글 불러오기
		req.setAttribute("fvo", (FileVO)vos.get("fvo")); // 파일 정보
		
		req.getRequestDispatcher("/view.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
