package kr.co.Farmstory_1.DAO;

import kr.co.Farmstory_1.DTO.BoardTermsDTO;
import kr.co.Farmstory_1.config.DBCP;
import kr.co.Farmstory_1.config.Sql;

public class BoardTermsDAO extends DBCP {
	private static BoardTermsDAO instance = new BoardTermsDAO();
	public static BoardTermsDAO getInstance() {
		return instance;
	}
	private BoardTermsDAO() {}
	
	// create
	
	// read
	public BoardTermsDTO selectTerms() {
		BoardTermsDTO btdto = new BoardTermsDTO();
		try {
			logger.info("selectTerms...");
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_TERMS);
			if(rs.next()) {
				btdto.setTerms(rs.getString("terms"));
				btdto.setPrivacy(rs.getString("privacy"));
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("btdto : "+btdto);
		return btdto;
	}
	
	// upload
	
	// delete
}
