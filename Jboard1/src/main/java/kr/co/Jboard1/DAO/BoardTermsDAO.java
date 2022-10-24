package kr.co.Jboard1.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.Jboard1.bean.BoardTermsBean;
import kr.co.Jboard1.config.DBCP;
import kr.co.Jboard1.config.Sql;
/**
 * java2_board 의 board_terms DAO
 * @date 2022/10/19
 * @author 심규영
 * @category GetBoardTermsDAO() - Read<br>
 * 			 Close() - close
 */
public class BoardTermsDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	/**
	 * BoardTermsDAO 생성자
	 */
	public BoardTermsDAO() {
		try {
			conn = DBCP.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 사이트 이용약관, 개인정보 취급방침 불러오기
	 * @return Board_Terms_Bean
	 */
	public BoardTermsBean GetBoardTermsDAO() {
		BoardTermsBean btb = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_TERMS);

			while(rs.next()) {
				btb = new BoardTermsBean();
				btb.setTerms(rs.getString(1));
				btb.setPrivacy(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return btb;
	}
	
	/**
	 * 클래스 종료
	 */
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
