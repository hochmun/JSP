package kr.co.Jboard1.DAO;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.co.Jboard1.config.DBCP;
import kr.co.Jboard1.config.Sql;

public class BoardArticleDAO extends DBCP {
	/**
	 * 데이터 베이스 연결
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
				BoardFileDAO bfdao = new BoardFileDAO();
				bfdao.BoardFileInsert(parent, newName, fname);
				bfdao.close();
			}
			
		} catch (Exception e) {
			System.out.println("아티클 인설트 오류");
			e.printStackTrace();
		}
	}
}
