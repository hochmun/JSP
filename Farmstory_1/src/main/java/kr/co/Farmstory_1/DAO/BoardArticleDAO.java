package kr.co.Farmstory_1.DAO;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.Farmstory_1.DTO.BoardArticleDTO;
import kr.co.Farmstory_1.DTO.BoardFileDTO;
import kr.co.Farmstory_1.config.DBCP;
import kr.co.Farmstory_1.config.Sql;

public class BoardArticleDAO extends DBCP {
	private static BoardArticleDAO instance = new BoardArticleDAO();
	public static BoardArticleDAO getInstance() {
		return instance;
	}
	private BoardArticleDAO() {}
	
	// create
	/**
	 * 2022/11/16<br/>insertArticle
	 * @author 심규영
	 * @param badto
	 */
	public void insertArticle(BoardArticleDTO badto, String fname, String savePath) {
		try {
			logger.info("insertArticle...");
			
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			stmt = conn.createStatement();
			
			psmt.setString(1, badto.getCate());
			psmt.setString(2, badto.getTitle());
			psmt.setString(3, badto.getContent());
			psmt.setInt(4, fname == null ? 0 : 1);
			psmt.setString(5, badto.getUid());
			psmt.setString(6, badto.getRegip());
			
			psmt.executeUpdate();
			rs = stmt.executeQuery(Sql.SELECT_MAX_NO);
			
			conn.commit();
			
			int parent = 0;
			if (rs.next()) {
				parent = rs.getInt(1);
			}
			
			// 파일을 첨부했으면
			if(fname != null) {
				// 파일명 수정
				int i = fname.lastIndexOf(".");
				String ext = fname.substring(i);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_");
				String now = sdf.format(new Date());
				String newName = now+badto.getUid()+ext;
				
				File f1 = new File(savePath+"/"+fname);
				File f2 = new File(savePath+"/"+newName);
				
				f1.renameTo(f2);
				
				BoardFileDTO bfdto = new BoardFileDTO();
				bfdto.setParent(parent);
				bfdto.setNewName(newName);
				bfdto.setOriName(fname);
				
				// 파일 테이블 INSERT
				BoardFileDAO.getInstance().insertFile(bfdto);
			}
			
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 2022/11/17<br/>댓글 작성
	 * @param badto
	 * @return Map - int result, {@link BoardArticleDTO}
	 */
	public Map<String, Object> insertComment(BoardArticleDTO badto) {
		Map<String, Object> map = new HashMap<>();
		int result = 0;
		BoardArticleDTO badto2 = new BoardArticleDTO();
		try {
			logger.info("insertCommnet");
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(Sql.INSERT_COMMENT);
			psmt.setInt(1, badto.getParent());
			psmt.setString(2, badto.getContent());
			psmt.setString(3, badto.getUid());
			psmt.setString(4, badto.getRegip());
			result = psmt.executeUpdate();
			
			psmt = conn.prepareStatement(Sql.UPDATE_COMMENT_COUNT);
			psmt.setInt(1, badto.getParent());
			psmt.executeUpdate();
			
			conn.commit();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_LAST_COMMENT_TIME);
			if(rs.next()) {
				badto2.setRdate(rs.getString("rdate"));
				badto2.setNo(rs.getInt("no"));
			}
			conn.commit();
			
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		map.put("result", result);
		map.put("badto", badto2);
		return map;
	}
	
	// read
	/**
	 * 2022/11/16<br/>게시물, 댓글 보기
	 * @param no
	 * @return Map<String, Object> => {@link BoardArticleDTO}, {@link BoardFileDTO}, List<{@link BoardArticleDTO}>
	 */
	public Map<String, Object> selectArticle(String no) {
		Map<String, Object> dtos = new HashMap<>();
		List<BoardArticleDTO> badtos = new ArrayList<>();
		BoardArticleDTO badto = new BoardArticleDTO();
		BoardFileDTO bfdto = new BoardFileDTO();
		try {
			logger.info("selectArticle...");
			
			conn = getConnection();
			
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			if(rs.next()) {
				badto.setNo(rs.getString("no"));
				badto.setParent(rs.getString("parent"));
				badto.setComment(rs.getString("comment"));
				badto.setCate(rs.getString("cate"));
				badto.setTitle(rs.getString("title"));
				badto.setContent(rs.getString("content"));
				badto.setFile(rs.getString("file"));
				badto.setHit(rs.getString("hit"));
				badto.setUid(rs.getString("uid"));
				badto.setRegip(rs.getString("regip"));
				badto.setRdate(rs.getString("rdate"));
				bfdto.setFno(rs.getInt("fno"));
				bfdto.setParent(rs.getInt("parent"));
				bfdto.setNewName(rs.getString("newName"));
				bfdto.setOriName(rs.getString("oriName"));
				bfdto.setDownload(rs.getInt("download"));
			}
			
			psmt = conn.prepareStatement(Sql.SELECT_COMMENT_LIST);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BoardArticleDTO badto2 = new BoardArticleDTO();
				badto2.setNo(rs.getInt("no"));
				badto2.setParent(rs.getInt("parent"));
				badto2.setCate(rs.getString("cate"));
				badto2.setContent(rs.getString("content"));
				badto2.setUid(rs.getString("uid"));
				badto2.setRegip(rs.getString("regip"));
				badto2.setRdate(rs.getString("rdate"));
				badto2.setNick(rs.getString("nick"));
				badtos.add(badto2);
			}
			
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		dtos.put("bfdto", bfdto);
		dtos.put("badto", badto);
		dtos.put("badtos", badtos);
		return dtos;
	}
	
	/**
	 * 게시판 리스트 목록 불러오기<br/>
	 * 2022/11/16
	 * @author 심규영
	 * @param cate
	 * @param start
	 * @return
	 */
	public List<BoardArticleDTO> selectArticles(String cate, int start) {
		List<BoardArticleDTO> badtos = new ArrayList<>();
		try {
			logger.info("selectArticles...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
			psmt.setString(1, cate);
			psmt.setInt(2, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardArticleDTO badto = new BoardArticleDTO();
				badto.setNo(rs.getString("no"));
				badto.setParent(rs.getString("parent"));
				badto.setComment(rs.getString("comment"));
				badto.setCate(rs.getString("cate"));
				badto.setTitle(rs.getString("title"));
				badto.setContent(rs.getString("content"));
				badto.setFile(rs.getString("file"));
				badto.setHit(rs.getString("hit"));
				badto.setUid(rs.getString("uid"));
				badto.setRegip(rs.getString("regip"));
				badto.setRdate(rs.getString("rdate"));
				badto.setNick(rs.getString("nick"));
				badtos.add(badto);
			}
			
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("badtos : "+badtos);
		return badtos;
	}
	
	/**
	 * 해당 카테고리의 게시물 전체 갯수 찾기<br/>2022/11/16
	 * @author 심규영
	 * @param cate
	 * @return
	 */
	public int selectCountArticle(String cate) {
		int totalCount = 0;
		try {
			logger.info("selectCountArticle...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_ARTICLE);
			psmt.setString(1, cate);
			rs = psmt.executeQuery();
			if(rs.next()) totalCount = rs.getInt(1);
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("totalCount : "+totalCount);
		return totalCount;
	}
	
	/**
	 * 메인에 테이블 불러오기 5개씩
	 * @param cate1
	 * @param cate2
	 * @param cate3
	 * @return
	 */
	public List<BoardArticleDTO> selectLatests(String cate1, String cate2, String cate3) {
		
		List<BoardArticleDTO> badtos = new ArrayList<>();
		
		try {
			logger.info("selectLatests...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_LATESTS);
			psmt.setString(1, cate1);
			psmt.setString(2, cate2);
			psmt.setString(3, cate3);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardArticleDTO badto = new BoardArticleDTO();
				badto.setNo(rs.getInt("no"));
				badto.setCate(rs.getString("cate"));
				badto.setTitle(rs.getString("title"));
				badto.setRdate(rs.getString("rdate").substring(2, 10));
				badtos.add(badto);
			}
			
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("badtos : "+badtos.toString());
		return badtos;
	}
	
	/**
	 * 메인 게시물 테이블 불러오기 3개씩
	 * @param cate
	 * @return
	 */
	public List<BoardArticleDTO> selectLatests(String cate) {
		
		List<BoardArticleDTO> badtos = new ArrayList<>();
		
		try {
			logger.info("selectLatests...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_LATEST);
			psmt.setString(1, cate);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardArticleDTO badto = new BoardArticleDTO();
				badto.setNo(rs.getInt("no"));
				badto.setTitle(rs.getString("title"));
				badto.setRdate(rs.getString("rdate").substring(2, 10));
				badtos.add(badto);
			}
			
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("badtos : "+badtos.toString());
		return badtos;
	}
	
	// upload
	/**
	 * 2022/11/16<br/>게시물 수정 메소드 작성
	 * @param badto
	 */
	public void updateArticle(BoardArticleDTO badto) {
		try {
			logger.info("updateArticle...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE);
			psmt.setString(1, badto.getTitle());
			psmt.setString(2, badto.getContent());
			psmt.setInt(3, badto.getNo());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 2022/11/17<br/>댓글 수정 메소드
	 * @param content
	 * @param no
	 * @return result
	 */
	public int updateComment(String content, String no) {
		int result = 0;
		try {
			logger.info("updateComment...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_COMMENT);
			psmt.setString(1, content);
			psmt.setString(2, no);
			result = psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 2022/11/16<br/>게시물 조회수 증가
	 * @param no
	 */
	public void updateHitArticle(String no) {
		try {
			logger.info("updateHitArticle...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_HIT_ARTICLE);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	// delete
	/**
	 * 게시물 삭제, 연결된 파일 및 댓글 삭제
	 * @param no
	 * @param filecheck
	 * @param path
	 */
	public void deleteArticle(String no, int filecheck, String path) {
		try {
			logger.info("deleteArticle...");
			conn = getConnection();
			conn.setAutoCommit(false);
			String fileName = null;
			
			if (filecheck > 0) {
				psmt = conn.prepareStatement(Sql.SELECT_FILE_NEWNAME);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if(rs.next()) fileName = rs.getString("newName");
				if(fileName != null) {
					File file = new File(path, fileName);
					if(file.exists()) file.delete();
				}
			}
			
			psmt = conn.prepareStatement(Sql.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			psmt.setString(3, no);
			psmt.executeUpdate();
			conn.commit();
			
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 댓글 삭제, 댓글 갯수 감소
	 * @param parent
	 * @param no
	 * @return result
	 */
	public int deleteComment(String parent, String no) {
		int result = 0;
		try {
			logger.info("deleteCommnet...");
			conn = getConnection();
			
			// 댓글 숫자 감소
			psmt = conn.prepareStatement(Sql.UPDATE_COMMENT_COUNT_MINUS);
			psmt.setString(1, parent);
			psmt.executeUpdate();
			
			// 댓글 삭제
			psmt = conn.prepareStatement(Sql.DELETE_COMMENT);
			psmt.setString(1, no);
			result = psmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
