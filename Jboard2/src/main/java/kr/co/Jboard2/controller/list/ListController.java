package kr.co.Jboard2.controller.list;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.Jboard2.Service.article.ArticleService;

@WebServlet("/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pg = req.getParameter("pg");
		
		// 게시판 번호 관련 선언
		int limitStart = 0; // 현재 페이지의 시작 게시물 번호
		int currentPage = 1; // 현제 페이지
		int total = service.selectCountArticles(); // 총 게시물 갯수
		int lastPageNum = 0; // 마지막 페이지 번호
		int pageGroupCurrent = 1; // 현재 그룹 페이지 값
		int pageGroupStart = 1; // 현재 그룹 시작 페이지 번호
		int pageGroupEnd = 0; // 현재 그룹 마지막 페이지 번호
		int pageStartNum = 0; // 현재 페이지 게시물 시작 번호
		
		// 페이지 마지막 번호 계산
		if(total % 10 != 0) lastPageNum = (total/10)+1;
		else lastPageNum = (total/10);
		
		// 전체 페이지 게시물 limit 시작값 계산
		if(pg != null) currentPage = Integer.parseInt(pg);
		limitStart = (currentPage - 1) * 10;
		
		// 페이지 그룹 계산
		pageGroupCurrent = (int)Math.ceil(currentPage/10.0);
		pageGroupStart 	 = (pageGroupCurrent - 1) * 10 + 1;
		pageGroupEnd 	 = pageGroupCurrent * 10;
		
		if (pageGroupEnd > lastPageNum) pageGroupEnd = lastPageNum;
		
		// 페이지 시작 번호 계산
		pageStartNum = total - limitStart;
		
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("pageGroupCurrent", pageGroupCurrent);
		req.setAttribute("pageGroupStart", pageGroupStart);
		req.setAttribute("pageGroupEnd", pageGroupEnd);
		req.setAttribute("pageStartNum", pageStartNum);
		req.setAttribute("vos", service.selectarticles(limitStart));
		
		req.getRequestDispatcher("/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
