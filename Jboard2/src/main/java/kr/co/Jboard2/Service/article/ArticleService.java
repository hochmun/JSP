package kr.co.Jboard2.Service.article;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	/**
	 * 게시물 등록
	 * @param vo
	 * @param fname
	 * @param savePath
	 */
	public void insertArticle(articleVO vo, String fname, String savePath) {
		dao.InsertArticle(vo, fname, savePath);
	}
	
	public int insertComment(articleVO vo) {
		return dao.insertComment(vo);
	}
	
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
	
	public List<articleVO> selectArticleComment(String parent) {
		return dao.selectArticleComment(parent);
	}
	
	
	
	// upload
	public void updateHitCount(String no) {
		dao.updateHitCount(no);
	}
	
	public articleVO updateCommentNumber(String parent) {
		return dao.updateCommentNumber(parent);
	}
	
	public int updateComment(String content, String no) {
		return dao.updateComment(content, no);
	}
	
	public void deleteCommentNumber(String parent) {
		dao.deleteCommentNumber(parent);
	}
	
	public void updateArticle(articleVO vo) {
		dao.updateArticle(vo);
	}
	
	// delete
	public int removeComment(String no) {
		return dao.removeComment(no);
	}
	
	/**
	 * 게시물 삭제 및 연결된 파일 및 댓글 삭제
	 * @param no
	 * @param filecheck
	 * @param path
	 */
	public void deleteArticle(String no, String filecheck, String path){
		dao.deleteArticle(no, Integer.parseInt(filecheck), path);
	}
	
	// service
	public String renameFile(String fname, String uid, String savePath) {
		// 첨부 파일이 있을 경우 파일명 변경
		// 새로운 파일명 생성
		String now = new SimpleDateFormat("yyyyMMddHHmmss_").format(new Date());
		String ext = fname.substring(fname.lastIndexOf("."));
		String newFileName = now + uid + ext;
		
		// 파일명 변경
		File oldFile = new File(savePath + File.separator + fname);
		File newFile = new File(savePath + File.separator + newFileName);
		oldFile.renameTo(newFile);
		
		return newFileName;
	}

	public String searchSqlQuery(String search) {
		String query = "";
		
		if(search != null) {
			
		} else {
			query = "SELECT MAX(`no`) FROM `board_article`";
		}
		
		return query;
	}
	
}
