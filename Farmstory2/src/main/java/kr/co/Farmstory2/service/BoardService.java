package kr.co.Farmstory2.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.Farmstory2.dao.ArticleDAO;
import kr.co.Farmstory2.vo.articleVO;
import kr.co.Farmstory2.vo.userVO;

public enum BoardService {
	INSTANCE;
	private ArticleDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private BoardService() {
		dao = new ArticleDAO();
	}
	
	// create
	public void insertArticle(articleVO vo, String fileName, String saveDirectory) {
		dao.insertArticle(vo, fileName, saveDirectory);
	}
	
	// read
	public List<articleVO> selectArticles(String cateName, int limitStart, String search) {
		List<articleVO> avos = dao.selectArticles(cateName, limitStart, search);
		// TODO - 검색어 강조하기 기능 추가하기
		return avos;
	}
	
	/**
	 * view - 글 정보 가져오기
	 * @param no
	 * @return
	 */
	public Map<String, Object> selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	/**
	 * view - 댓글들 정보 가져오기
	 * @param parent
	 * @return
	 */
	public List<articleVO> selectArticleComment(String parent) {
		return dao.selectArticleComment(parent);
	}
	
	/**
	 * 카테고리별 총 게시물 갯수
	 * @param search
	 * @param cateName
	 * @return
	 */
	public int selectCountArticles(String search, String titName) {
		return dao.selectCountArticles(search, titName);
	}
	
	// upload
	/**
	 * view - 게시물 조회수 증가
	 * @param no
	 */
	public void updateHitCount(String no) {
		dao.updateHitCount(no);
	}
	
	// delete
	
	// service
	/**
	 * 게시물 페이징 기능
	 * @param req
	 * @param cateName
	 * @return
	 */
	public int boardPaging(HttpServletRequest req, String titName, String search) {
		String pg = req.getParameter("pg");
		
		int currentPage = 1; // 현재 페이지
		int total = selectCountArticles(search, titName); // 총 게시물 갯수
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
				break;
			case "4":
				titName = "event";
				break;
			case "5":
				switch(tit) {
				case "1":
					titName = "notice";
					break;
				case "2":
					titName = "menu";
					break;
				case "3":
					titName = "chef";
					break;
				case "4":
					titName = "qna";
					break;
				case "5":
					titName = "faq";
					break;
				default:
					titName = "-1";
					break;
				}
				break;
			default:
				titName = "-1";
				break;
		}
		logger.debug("tit : "+titName);
		return titName;
	}
	
	/**
	 * 파일 업로드 처리
	 * @param req
	 * @param saveDirectory
	 * @param maxPostSize
	 * @return
	 */
	public MultipartRequest uploadFile(HttpServletRequest req, String saveDirectory, int maxPostSize) {
		try {
			// 파일 업로드
			return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
		} catch (Exception e) {
			// 업로드 실패
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 글쓰기 - VO에 폼값 저장
	 * @param req
	 * @param mr
	 * @return
	 */
	public articleVO saveVOFromForm(HttpServletRequest req, MultipartRequest mr) {
		articleVO vo = new articleVO();
		
		vo.setTitle(mr.getParameter("title"));
		vo.setContent(mr.getParameter("content"));
		vo.setCate(titNameFormat(mr.getParameter("cate"), mr.getParameter("tit")));
		vo.setRegip(req.getRemoteAddr());
		vo.setUid(((userVO)req.getSession().getAttribute("sessUser")).getUid());
		
		return vo;
	}
}
