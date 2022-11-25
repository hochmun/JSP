package kr.co.Jboard2.dao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.Jboard2.Service.file.FileService;
import kr.co.Jboard2.db.DBCP;
import kr.co.Jboard2.db.Sql;
import kr.co.Jboard2.vo.FileVO;
import kr.co.Jboard2.vo.articleVO;

public class ArticleDAO extends DBCP {
	
	FileService service = FileService.INSTANCE;
	
	// create
	/**
	 * 게시물 등록 및 파일 등록
	 * @param vo
	 * @param fname
	 * @param savePath
	 */
	public void InsertArticle(articleVO vo, String fname, String savePath) {
		try {
			logger.info("InsertArticle...");
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			stmt = conn.createStatement();
			
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setInt(3, fname == null ? 0 : 1);
			psmt.setString(4, vo.getUid());
			psmt.setString(5, vo.getRegip());
			
			psmt.executeUpdate();
			rs = stmt.executeQuery(Sql.SELECT_MAX_NO);
			
			conn.commit();
			
			int parent = 0;
			if(rs.next()) parent = rs.getInt(1);
			
			if(fname != null) {
				// 첨부 파일이 있을 경우 파일명 변경
				// 새로운 파일명 생성
				String now = new SimpleDateFormat("yyyyMMddHHmmss_").format(new Date());
				String ext = fname.substring(fname.lastIndexOf("."));
				String newFileName = now + vo.getUid() + ext;
				
				// 파일명 변경
				File oldFile = new File(savePath + File.separator + fname);
				File newFile = new File(savePath + File.separator + newFileName);
				oldFile.renameTo(newFile);
				
				// 파일 테이블 Insert
				service.insertFile(parent, newFileName, fname);
			}
			
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 댓글 등록
	 * @param vo
	 * @return
	 */
	public int insertComment(articleVO vo) {
		int result = 0;
		try {
			logger.info("insertComment...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_COMMENT);
			psmt.setInt(1, vo.getParent());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getUid());
			psmt.setString(4, vo.getRegip());
			result = psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	// read
	public List<articleVO> selectarticles(int limitStart) {
		List<articleVO> lists = new ArrayList<>();
		try {
			logger.info("selectarticles...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLE_LIST);
			psmt.setInt(1, limitStart);
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
				lists.add(vo);
			}
			
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return lists;
	}
	
	/**
	 * 게시물 보기
	 * @param no
	 * @return
	 */
	public Map<String, Object> selectArticle(String no) {
		Map<String, Object> vos = new HashMap<>();
		articleVO avo = new articleVO();
		FileVO fvo = new FileVO();
		try {
			logger.info("selectArticle...");
			conn = getConnection();
			
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				avo.setNo(rs.getInt(1));
				avo.setParent(rs.getInt(2));
				avo.setComment(rs.getInt(3));
				avo.setCate(rs.getString(4));
				avo.setTitle(rs.getString(5));
				avo.setContent(rs.getString(6));
				avo.setFile(rs.getInt(7));
				avo.setHit(rs.getInt(8));
				avo.setUid(rs.getString(9));
				avo.setRegip(rs.getString(10));
				avo.setRdate(rs.getString(11));
				fvo.setFno(rs.getInt(12));
				fvo.setParent(rs.getInt(13));
				fvo.setNewName(rs.getString(14));
				fvo.setOriName(rs.getString(15));
				fvo.setDownload(rs.getInt(16));
			}
			
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		vos.put("avo", avo);
		vos.put("fvo", fvo);
		return vos;
	}
	
	public List<articleVO> selectArticleComment(String parent) {
		List<articleVO> vos = new ArrayList<>();
		try {
			logger.info("selectArticleComment...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.COMMENT_LIST);
			psmt.setString(1, parent);
			rs = psmt.executeQuery();
			while(rs.next()) {
				articleVO vo = new articleVO();
				vo.setNo(rs.getInt("no"));
				vo.setParent(rs.getInt("parent"));
				vo.setCate(rs.getString("cate"));
				vo.setContent(rs.getString("content"));
				vo.setUid(rs.getString("uid"));
				vo.setRegip(rs.getString("regip"));
				vo.setRdate(rs.getString("rdate").substring(2, 10));
				vo.setNick(rs.getString("nick"));
				vos.add(vo);
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vos;
	}
	
	public int selectCountArticles() {
		int total = 0;
		try {
			logger.info("selectCountArticles...");
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_COUNT_TOTAL_ARTICLE);
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("total : "+total);
		return total;
	}
	
	// upload
	public void updateHitCount(String no) {
		try {
			logger.info("updateHitCount...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_HIT);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void updateArticle(articleVO vo) {
		try {
			logger.info("updateArticle...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setInt(3, vo.getNo());
			psmt.executeUpdate();
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 댓글 갯수 증가, 마지막 댓글 등록 시간, 등록 번호 리턴
	 * @param no
	 * @return
	 */
	public articleVO updateCommentNumber(String no) {
		articleVO vo = new articleVO();
		try {
			logger.info("updateCommentNumber...");
			conn = getConnection();
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(Sql.UPDATE_COMMENT_NUMBER);
			psmt.setString(1, no);
			psmt.executeUpdate();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.LAST_COMMENT_TIME);
			if(rs.next()) {
				vo.setRdate(rs.getString("rdate"));
				vo.setNo(rs.getInt("no"));
			}
			
			conn.commit();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	/**
	 * 댓글 수정
	 * @param content
	 * @param no
	 * @return
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
	 * 댓글 갯수 감소
	 * @param parnet
	 */
	public void deleteCommentNumber(String parnet){
		try {
			logger.info("deleteCommentNumber...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.DELETE_COMMENT_NUMBER);
			psmt.setString(1, parnet);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	// delete
	public int removeComment(String no) {
		int result = 0;
		try {
			logger.info("removeComment...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.REMOVE_COMMENT);
			psmt.setString(1, no);
			result = psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 게시물 삭제 및 연결된 파일 및 댓글 삭제
	 * @param no
	 * @param filecheck
	 * @param path
	 */
	public void deleteArticle(String no, int filecheck, String path){
		try {
			logger.info("deleteArticle...");
			conn = getConnection();
			conn.setAutoCommit(false);
			String fileName = null;
			
			if (filecheck > 0) {
				psmt = conn.prepareStatement(Sql.SELECT_FILE);
				psmt.setString(1, no);
				rs = psmt.executeQuery();
				if(rs.next()) {
					fileName = rs.getString("newName");
				}
				if(fileName != null) {
					File file = new File(path, fileName);
					if(file.exists()) {
						file.delete();
					}
				}
			}
			
			psmt = conn.prepareStatement(Sql.DELETE_ARTICLE);
			psmt.setString(1, no);
			psmt.setString(2, no);
			psmt.setString(3, no);
			psmt.executeUpdate();
			conn.commit();
			
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
}
