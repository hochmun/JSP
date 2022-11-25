package kr.co.Jboard2.controller.list;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.co.Jboard2.Service.article.ArticleService;
import kr.co.Jboard2.util.FileUpload;
import kr.co.Jboard2.vo.articleVO;
import kr.co.Jboard2.vo.userVO;

@WebServlet("/write.do")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.파일 업로드 차리
		// 업로드 디렉터리의 물리적 경로 확인
		String saveDirectory = req.getServletContext().getRealPath("/file");
		
		// 초기화 매개변수로 설정한 첨부 파일 최대 용량 확인
		ServletContext application = getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		// 파일 업로드
		MultipartRequest mr = FileUpload.uploadFile(req, saveDirectory, maxPostSize);
		
		// 2.파일 업로드 외 처리
		// 폼값을 VO에 저장
		userVO vo2 = (userVO)req.getSession().getAttribute("sessUser");
		articleVO vo = new articleVO();
		
		vo.setTitle(mr.getParameter("title"));
		vo.setContent(mr.getParameter("content"));
		vo.setRegip(req.getRemoteAddr());
		vo.setUid(vo2.getUid());
		
		// 원본 파일명과 저장된 파일 이름 설정
		String fileName = mr.getFilesystemName("file");
		
		// 데이터 베이스 처리
		service.insertArticle(vo, fileName, saveDirectory);
		
		// 목록으로 이동
		resp.sendRedirect("/Jboard2/list.do");
	}
}
