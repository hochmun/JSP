package kr.co.Jboard2.Service.article;

import java.util.List;

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
	
	// upload
	
	// delete
}
