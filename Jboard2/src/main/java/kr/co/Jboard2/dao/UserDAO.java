package kr.co.Jboard2.dao;

import kr.co.Jboard2.db.DBCP;
import kr.co.Jboard2.db.Sql;
import kr.co.Jboard2.vo.termsVO;
import kr.co.Jboard2.vo.userVO;

public class UserDAO extends DBCP {
	/*
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInsatnce() {
		return instance;
	}
	private UserDAO() {}
	*/

	// create
	/**
	 * 유저 회원 등록
	 * @param vo
	 */
	public void insertUser(userVO vo) {
		try {
			logger.info("insertUser...");
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
	 * 사용약관 불러오기
	 * @return {@link termsVO}
	 */
	public termsVO selectTerms() {
		termsVO vo = new termsVO();
		try {
			logger.info("selectTerms...");
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_TERMS);
			if(rs.next()) {
				vo.setTerms(rs.getString(1));
				vo.setPrivacy(rs.getString(2));
			}
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	public void selectUser() {}
	
	public void selectUsers() {}
	
	/**
	 * 유저 아이디 중복 검사
	 * @param uid
	 * @return result 중복되는 아이디 갯수 리턴
	 */
	public int selectCountUserUid(String uid) {
		int result = 0;
		try {
			logger.info("selectCountUserUid...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_USER_UID);
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
	
	public int selectCountUserNick(String nick) {
		int result = 0;
		try {
			logger.info("selectCountUserNick...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_USER_NICK);
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

	/**
	 * 유저 이름 이메일 검사
	 * @param name
	 * @param email
	 * @return result
	 */
	public int selectCountUserName(String name, String email) {
		int result = 0;
		try {
			logger.info("selectCountUserUid...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_USER_NAME_EMAIL);
			psmt.setString(1, name);
			psmt.setString(2, email);
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
	 * 유저 아이디 이메일 검사
	 * @param uid
	 * @param email
	 * @return result
	 */
	public int selectCountUserUid(String uid, String email) {
		int result = 0;
		try {
			logger.info("selectCountUserUid...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_USER_UID_EMAIL);
			psmt.setString(1, uid);
			psmt.setString(2, email);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	// upload
	
	// delete
}
