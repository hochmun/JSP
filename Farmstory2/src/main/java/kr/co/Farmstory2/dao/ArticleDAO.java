package kr.co.Farmstory2.dao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.co.Farmstory2.db.DBCP;
import kr.co.Farmstory2.db.Sql;
import kr.co.Farmstory2.service.FileService;
import kr.co.Farmstory2.vo.articleVO;

public class ArticleDAO extends DBCP {

	private FileService service = FileService.INSTANCE;
	
	// create
	/**
	 * 글작성 - 글 정보 저장
	 * @param vo
	 * @param fileName
	 * @param saveDirectory
	 */
	public void insertArticle(articleVO vo, String fileName, String saveDirectory) {
		try {
			logger.info("insertArticle...");
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			stmt = conn.createStatement();
			
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getCate());
			psmt.setInt(4, fileName == null ? 0 : 1);
			psmt.setString(5, vo.getUid());
			psmt.setString(6, vo.getRegip());
			
			psmt.executeUpdate();
			rs = stmt.executeQuery(Sql.SELECT_MAX_NO);
			
			conn.commit();
			
			int parent = 0;
			if (rs.next()) parent = rs.getInt(1);
			
			if(fileName != null) {
				// 첨부 파일이 있을 경우 파일명 변경
				// 새로운 파일명 생성
				String now = new SimpleDateFormat("yyyyMMddHHmmss_").format(new Date());
				String ext = fileName.substring(fileName.lastIndexOf("."));
				String newFileName = now + vo.getUid() + ext;
				
				// 파일명 변경
				File oldFile = new File(saveDirectory + File.separator + fileName);
				File newFile = new File(saveDirectory + File.separator + newFileName);
				oldFile.renameTo(newFile);
				
				// 파일 테이블 Insert
				service.insertFile(parent, newFileName, fileName);
			}
			
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	// read
	public List<articleVO> selectArticles(String cateName, int limitStart, String search) {
		List<articleVO> avos = new ArrayList<>();
		String word = "";
		if (search != null) word = "%"+search+"%";
		else word = "%%";
		try {
			logger.info("selectArticle...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setString(1, cateName);
			psmt.setString(2, word);
			psmt.setString(3, word);
			psmt.setInt(4, limitStart);
			rs = psmt.executeQuery();
			while(rs.next()) {
				articleVO vo = new articleVO();
				vo.setNo(rs.getInt(1));
				vo.setParent(rs.getInt(2));
				vo.setComment(rs.getInt(3));
				vo.setCate(rs.getString(4));
				vo.setTitle(rs.getString(5));
				vo.setContent(rs.getString(6));
				vo.setFile(rs.getInt(7));
				vo.setHit(rs.getInt(8));
				vo.setUid(rs.getString(9));
				vo.setRegip(rs.getString(10));
				vo.setRdate(rs.getString(11).substring(2, 10));
				vo.setNick(rs.getString(12));
				avos.add(vo);
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return avos;
	}
	
	/**
	 * 카테고리별 전체 게시물 갯수 + 검색기능
	 * @param search
	 * @param cateName
	 * @return
	 */
	public int selectCountArticles(String search, String titName) {
		int total = 0;
		String word = "";
		if (search != null) word = "%"+search+"%";
		else word = "%%";
		try {
			logger.info("selectCountArticles...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_ARTICLES);
			psmt.setString(1, titName);
			psmt.setString(2, word);
			psmt.setString(3, word);
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return total;
	}
	
	// upload
	
	// delete
	
}
