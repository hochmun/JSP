package kr.co.Jboard2.Service.article;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.Jboard2.dao.ArticleDAO;
import kr.co.Jboard2.vo.articleVO;

public enum ArticleService {
	
	INSTANCE;
	private ArticleDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ArticleService() {
		dao = new ArticleDAO();
	}
	
	// create
	
	// read
	public List<articleVO> selectarticles(int limitStart) {
		return dao.selectarticles(limitStart);
	}
	
	public int selectCountArticles() {
		return dao.selectCountArticles();
	}

	/**
	 * 게시물 보기
	 * @param no
	 * @return 
	 */
	public Map<String, Object> selectArticle(String no) {
		return dao.selectArticle(no);
	}
	// upload
	/**
	 * 게시물 등록
	 * @param vo
	 * @param fname
	 * @param savePath
	 */
	public void insertArticle(articleVO vo, String fname, String savePath) {
		dao.InsertArticle(vo, fname, savePath);
	}
	
	// delete
}
