package kr.co.Farmstory2.dao;

import kr.co.Farmstory2.db.DBCP;
import kr.co.Farmstory2.db.Sql;
import kr.co.Farmstory2.vo.termsVO;
import kr.co.Farmstory2.vo.userVO;

public class UserDAO extends DBCP {
	// create
	/**
	 * 유저 회원 등록
	 * @param vo
	 */
	public void insertUser(userVO vo) {
		try {
			logger.info("insertUser... create UserId");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_USER);
			psmt.setString(1, vo.getUid());
			psmt.setString(2, vo.getPass());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getNick());
			psmt.setString(5, vo.getEmail());
			psmt.setString(6, vo.getHp());
			psmt.setString(7, vo.getZip());
			psmt.setString(8, vo.getAddr1());
			psmt.setString(9, vo.getAddr2());
			psmt.setString(10, vo.getRegip());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	// read
	/**
	 * 이용약관 불러오기
	 * @return
	 */
	public termsVO selectTerms() {
		termsVO vo = new termsVO();
		try {
			logger.info("selectTerms...");
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_TERMS);
			if(rs.next()) {
				vo.setPrivacy(rs.getString("privacy"));
				vo.setTerms(rs.getString("terms"));
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	/**
	 * 아이디 중복 검사
	 * @param uid
	 * @return
	 */
	public int selectCountUid(String uid) {
		int result = 0;
		try {
			logger.info("selectCountUid... uid overlap check");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 닉네임 중복 검사
	 * @param nick
	 * @return
	 */
	public int selectCountNick(String nick) {
		int result = 0;
		try {
			logger.info("selectCountNick... User Nick overlap check");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	// upload
	
	// delete
}
