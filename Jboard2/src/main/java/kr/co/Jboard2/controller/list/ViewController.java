package kr.co.Jboard2.controller.list;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.Jboard2.Service.article.ArticleService;
import kr.co.Jboard2.vo.FileVO;
import kr.co.Jboard2.vo.articleVO;
import kr.co.Jboard2.vo.userVO;

@WebServlet("/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

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
		logger.info("ViewController...");
		resp.setContentType("text/html;charset=UTF-8");
		
		String content 	= req.getParameter("content");
		String no		= req.getParameter("no");
		String parent	= req.getParameter("parent");
		String type		= req.getParameter("type");
		userVO uvo = (userVO)req.getSession().getAttribute("sessUser");
		
		logger.debug(type);
		
		articleVO vo = new articleVO();
		vo.setParent(no);
		vo.setContent(content);
		vo.setRegip(req.getRemoteAddr());
		vo.setUid(uvo.getUid());
		
		articleVO vo2 = new articleVO();
		int result = 0;
		
		JsonObject json = new JsonObject();
		
		if (type.equals("1")) {
			// 댓글 작성
			result = service.insertComment(vo);
			// 댓글 수 증가, 마지막 댓글 시간 등록 번호 리턴
			vo2 = service.updateCommentNumber(no); 
			
			json.addProperty("nick", uvo.getNick());
			json.addProperty("date", vo2.getRdate().substring(2, 10));
			json.addProperty("content", content);
			json.addProperty("no", String.valueOf(vo2.getNo()));
			json.addProperty("parent", no);
		} else if(type.equals("2")) {
			// 댓글 수정
			result = service.updateComment(content, no);
		} else if(type.equals("3")) {
			// 댓글 삭제
			service.deleteCommentNumber(parent); // 댓글 삭제시 댓글 갯수 감소
			result = service.removeComment(no); // 댓글 삭제
		}
		
		json.addProperty("result", result);
		
		String jsonData = json.toString();
		PrintWriter writer = resp.getWriter();
		writer.print(jsonData);
	}
}
