package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import bean.Board_Terms_Bean;
import config.DBCP;
/**
 * java2_board 의 board_terms DAO
 * @date 2022/10/19
 * @author 심규영
 * @category GetBoardTermsDAO() - Read
 */
public class BoardTermsDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
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
	public Board_Terms_Bean GetBoardTermsDAO() {
		Board_Terms_Bean btb = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `board_terms`");
		
			while(rs.next()) {
				btb = new Board_Terms_Bean();
				btb.setTerms(rs.getString(1));
				btb.setPrivacy(rs.getString(2));
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return btb;
	}
}
