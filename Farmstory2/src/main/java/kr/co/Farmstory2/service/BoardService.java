package kr.co.Farmstory2.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Farmstory2.dao.ArticleDAO;
import kr.co.Farmstory2.vo.articleVO;

public enum BoardService {
	INSTANCE;
	private ArticleDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private BoardService() {
		dao = new ArticleDAO();
	}
	
	// create
	
	// read
	public List<articleVO> selectArticles(String cateName, int limitStart, String search) {
		List<articleVO> avos = dao.selectArticles(cateName, limitStart, search);
		// TODO - 검색어 강조하기 기능 추가하기
		return avos;
	}
	
	/**
	 * 카테고리별 총 게시물 갯수
	 * @param search
	 * @param cateName
	 * @return
	 */
	public int selectCountArticles(String search, String cateName) {
		return dao.selectCountArticles(search, cateName);
	}
	
	// upload
	
	// delete
	
	// service
	/**
	 * 게시물 페이징 기능
	 * @param req
	 * @param cateName
	 * @return
	 */
	public int boardPaging(HttpServletRequest req, String cateName, String search) {
		String pg = req.getParameter("pg");
		
		int currentPage = 1; // 현재 페이지
		int total = selectCountArticles(search, cateName); // 총 게시물 갯수
		int lastPageNum = 0; // 마지막 페이지 번호
		
		// 페이지 마지막 번호 계산
		if(total % 10 != 0) lastPageNum = (total/10)+1;
		else lastPageNum = (total/10);
		
		// 전체 페이지 게시물 limit 시작값 계산
		if(pg != null) currentPage = Integer.parseInt(pg);
		int limitStart = (currentPage - 1) * 10;
		
		// 페이지 그룹 계산
		int pageGroupCurrent = (int)Math.ceil(currentPage/10.0);
		int pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
		int pageGroupEnd = pageGroupCurrent * 10;
		
		if (pageGroupEnd > lastPageNum) pageGroupEnd = lastPageNum;
		
		// 페이지 시작 번호 계산
		int pageStartNum = total - limitStart;
		
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("pageGroupCurrent", pageGroupCurrent);
		req.setAttribute("pageGroupStart", pageGroupStart);
		req.setAttribute("pageGroupEnd", pageGroupEnd);
		req.setAttribute("pageStartNum", pageStartNum);
		
		return limitStart;
	}
	
	/**
	 * 카테고리 이름 변환
	 * @param cate
	 * @return
	 */
	public String titNameFormat(String cate, String tit) {
		// TODO
		String titName = "";
		switch(cate) {
			case "2":
				titName = "market";
				break;
			case "3":
				switch(tit) {
				case "1":
					titName = "story";
					break;
				case "2":
					titName = "grow";
					break;
				case "3":
					titName = "school";
					break;
				default:
					titName = "-1";
				}
			case "4":
				titName = "event";
				break;
			case "5":
				switch(tit) {
				case "1":
					titName = "";
					break;
				case "2":
					titName = "";
					break;
				case "3":
					titName = "";
					break;
				case "4":
					titName = "";
					break;
				case "5":
					titName = "";
					break;
				default:
					titName = "-1";
					break;
				}
			default:
				titName = "-1";
				break;
		}
		return titName;
	}
	
}
