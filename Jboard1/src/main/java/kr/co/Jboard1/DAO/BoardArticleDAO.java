package kr.co.Jboard1.DAO;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.Jboard1.bean.BoardArticleBean;
import kr.co.Jboard1.bean.BoardFileBean;
import kr.co.Jboard1.config.DBCP;
import kr.co.Jboard1.config.Sql;

public class BoardArticleDAO extends DBCP {
	/*
	private static BoardArticleDAO instance = new BoardArticleDAO();
	public static BoardArticleDAO getInstance() {
		try {
			conn = getConnection();
		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류");
			e.printStackTrace();
		}
		return instance;
	}
	*/
	public BoardArticleDAO() {
		try {
			conn = getConnection();
		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류");
			e.printStackTrace();
		}
	}
	
	/**
	 * 게시물 등록
	 * @author 심규영
	 * @since 2022/10/24
	 * @param title 제목
	 * @param content 내용
	 * @param uid 아이디
	 * @param regip 아이피
	 */
	public void InsertBoardArticleDAO(String title, String content
			, String fname, String uid, String regip, String savePath) {
		try {
			// 등록 겹칩 방지
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			stmt = conn.createStatement();
			
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, fname == null ? 0 : 1);
			psmt.setString(4, uid);
			psmt.setString(5, regip);
			
			psmt.executeUpdate();
			rs = stmt.executeQuery(Sql.SELECT_MAX_NO);
			
			// 작업 확정
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
				String newName = now+uid+ext;
				
				File f1 = new File(savePath+"/"+fname);
				File f2 = new File(savePath+"/"+newName);
				
				f1.renameTo(f2);
				
				// 파일 테이블 Insert
				BoardFileDAO bfdao = new BoardFileDAO();//BoardFileDAO.getInstance();
				bfdao.BoardFileInsert(parent, newName, fname);
				bfdao.close();
			}
		} catch (Exception e) {
			System.out.println("아티클 인설트 오류");
			e.printStackTrace();
		}
	}

	public int insertComment() {
		int result = 0;
		try {
			psmt = conn.prepareStatement(Sql.INSERT_COMMENT);
		} catch(Exception e) {
			System.out.println("댓글 등록 오류");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * BoardArticle list view
	 * <p>게시물 모든 리스트 보기 메소드<p>
	 * @author 심규영
	 * @since 2022/10/27
	 * @return List<BoardArticleBean>
	 */
	public List<BoardArticleBean> ViewAllListBoardArticleDAO(int limitStart) {
		List<BoardArticleBean> babs = new ArrayList<BoardArticleBean>();
		
		try {
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLE_LIST);
			psmt.setInt(1, limitStart);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardArticleBean bab = new BoardArticleBean();
				bab.setNo(rs.getInt(1));
				bab.setParent(rs.getInt(2));
				bab.setComment(rs.getInt(3));
				bab.setCate(rs.getString(4));
				bab.setTitle(rs.getString(5));
				bab.setContent(rs.getString(6));
				bab.setFile(rs.getInt(7));
				bab.setHit(rs.getInt(8));
				bab.setUid(rs.getString(9));
				bab.setRegip(rs.getString(10));
				bab.setRdate(rs.getString(11));
				bab.setNick(rs.getString(12));
				babs.add(bab);
			}
		} catch (Exception e) {
			System.out.println("모든 게시물 리스트 보기 오류");
			e.printStackTrace();
		}
		
		return babs;
	}
	
	/**
	 * 해당 게시물 보기
	 * @date 2022/10/28 <br> 2022/10/31 수정
	 * @author 심규영
	 * @since 1.1
	 * @param no
	 * @return Map<Object, Object> - (BoardArticleBean - bab, BoardFileBean - bfb) 
	 */
	public Map<Object, Object> ViewBoardArticleDAO (String no) {
		Map<Object, Object> beans = new HashMap<>();
		BoardArticleBean bab = new BoardArticleBean();
		BoardFileBean bfb = new BoardFileBean();
		
		try {
			psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				bab.setNo(rs.getInt(1));
				bab.setParent(rs.getInt(2));
				bab.setComment(rs.getInt(3));
				bab.setCate(rs.getString(4));
				bab.setTitle(rs.getString(5));
				bab.setContent(rs.getString(6));
				bab.setFile(rs.getInt(7));
				bab.setHit(rs.getInt(8));
				bab.setUid(rs.getString(9));
				bab.setRegip(rs.getString(10));
				bab.setRdate(rs.getString(11));
				bfb.setFno(rs.getInt(12));
				bfb.setParent(rs.getInt(13));
				bfb.setNewName(rs.getString(14));
				bfb.setOriName(rs.getString(15));
				bfb.setDownload(rs.getInt(16));
			}
			
		} catch (Exception e) {
			System.out.println("게시물 보기 오류");
			e.printStackTrace();
		}
		beans.put("bab", bab);
		beans.put("bfb", bfb);
		return beans;
	}
	
	/**
	 * 총 게시물 갯수 찾는 메소드
	 * @author 심규영
	 * @since 2022/10/27
	 * @return totalCount - 총 게시물 갯수
	 */
	public int SelectCountTotalBoardArticleDao() {
		int totalCount = 0;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_COUNT_TOTAL_ARTICLE);
			if(rs.next()) { totalCount = rs.getInt(1); }
			
		} catch (Exception e) {
			System.out.println("게시물 총갯수 찾기 오류");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	/**
	 * 게시물 조회수 증가 메소드
	 * @author 심규영
	 * @since 2022/10/27
	 * @param no - 게시물 번호, 해당 게시물의 조회수를 증가하기위해 필요
	 */
	public void UpdateHitCount(String no) {
		try {
			psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_HIT);
			psmt.setString(1, no);
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 조회 카운트 업데이트 오류");
			e.printStackTrace();
		}
	}
}
