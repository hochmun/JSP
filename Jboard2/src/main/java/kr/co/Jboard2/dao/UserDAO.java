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
	
	/**
	 * 이메일로 유저 정보 불러오기(아이디 찾기, 비밀번호 찾기 공통 사용)
	 * @param email
	 * @return {@link userVO}
	 */
	public userVO selectUserEmail(String email) {
		userVO vo = new userVO();
		try {
			logger.info("selectUserEmail...");
			logger.debug(email);
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER_EMAIL);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setUid(rs.getString("uid"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setNick(rs.getString("nick"));
				vo.setEmail(rs.getString("email"));
				vo.setHp(rs.getString("hp"));
				vo.setRdate(rs.getString("rdate"));
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("vo : "+vo);
		return vo;
	}
	
	/**
	 * 유저 정보 가져오기 (유저 로그인)
	 * @param uid
	 * @param pass
	 * @return
	 */
	public userVO selectUser(String uid, String pass) {
		userVO vo = new userVO();
		try {
			logger.info("selectUser...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setUid(rs.getString(1));
				vo.setPass(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setNick(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setHp(rs.getString(6));
				vo.setGrade(rs.getInt(7));
				vo.setZip(rs.getString(8));
				vo.setAddr1(rs.getString(9));
				vo.setAddr2(rs.getString(10));
				vo.setRegip(rs.getString(11));
				vo.setRdate(rs.getString(12));
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("vo : "+vo);
		return vo;
	}
	
	/**
	 * 회원정보 접근 - 아이디 비밀번호 체크
	 * @param uid
	 * @param pass
	 * @return
	 */
	public int selectUserCheck(String uid, String pass) {
		int result = 0;
		try {
			logger.info("selectUserCheck...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER_COUNT_CHECK);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			if(rs.next())  {
				result = rs.getInt(1);
			}
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
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
	
	/**
	 * 유저 닉네임 중복 검사
	 * @param nick
	 * @return
	 */
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
			logger.info("selectCountUserName...");
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
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 유저 자동 로그인
	 * @param sessId
	 * @return
	 */
	public userVO selectUserBySessId(String sessId) {
		userVO vo = new userVO();
		try {
			logger.info("selectUserBySessId...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER_BY_SESSID);
			psmt.setString(1, sessId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setUid(rs.getString(1));
				vo.setPass(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setNick(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setHp(rs.getString(6));
				vo.setGrade(rs.getInt(7));
				vo.setZip(rs.getString(8));
				vo.setAddr1(rs.getString(9));
				vo.setAddr2(rs.getString(10));
				vo.setRegip(rs.getString(11));
				vo.setRdate(rs.getString(12));
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("vo : "+ vo);
		return vo;
	}
	
	// upload
	/**
	 * 유저 회원 정보 갱신
	 * @param vo
	 * @return
	 */
	public int updateUser(userVO vo) {
		int result = 0;
		try {
			logger.info("updateUser...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER);
			psmt.setString(1, vo.getPass());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getNick());
			psmt.setString(4, vo.getEmail());
			psmt.setString(5, vo.getHp());
			psmt.setString(6, vo.getZip());
			psmt.setString(7, vo.getAddr1());
			psmt.setString(8, vo.getAddr2());
			psmt.setString(9, vo.getUid());
			result = psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("updateUser result : "+result);
		return result;
	}
	
	/**
	 * 유저 회원 정보 갱신 - 비밀번호 변경 안함
	 * @param vo
	 * @return
	 */
	public int updateUserNotPass(userVO vo) {
		int result = 0;
		try {
			logger.info("updateUserNotPass...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_NOT_PASS);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getNick());
			psmt.setString(3, vo.getEmail());
			psmt.setString(4, vo.getHp());
			psmt.setString(5, vo.getZip());
			psmt.setString(6, vo.getAddr1());
			psmt.setString(7, vo.getAddr2());
			psmt.setString(8, vo.getUid());
			result = psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("updateUserNotPass result : "+result);
		return result;
	}
	
	/**
	 * 유저 비밀번호 변경
	 * @param uid
	 * @param pass
	 * @return
	 */
	public int updateUserPass(String uid, String pass) {
		int result = 0;
		try {
			logger.info("updateUserPass...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_PASS);
			psmt.setString(1, pass);
			psmt.setString(2, uid);
			result = psmt.executeUpdate();
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 데이터베이스에 자동로그인 정보 업데이트
	 * @param sessId
	 * @param uid
	 */
	public void updateUserForSession(String sessId, String uid) {
		try {
			logger.info("updateUserForSession...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_FOR_SESSION);
			psmt.setString(1, sessId);
			psmt.setString(2, uid);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void updateUserForSessLimitDate(String sessId) {
		try {
			logger.info("updateUserForSessLimitDate...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_FOR_SESS_LIMIT_DATE);
			psmt.setString(1, sessId);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void updateUserForSessionOut(String uid) {
		try {
			logger.info("updateUserForSessionOut...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_USER_FOR_SESSION_OUT);
			psmt.setString(1, uid);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	// delete
	/**
	 * 유저 정보 삭제
	 * @param uid
	 * @return
	 */
	public int deleteUser(String uid) {
		int result = 0;
		try {
			logger.info("deleteUser...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.DELETE_USER);
			psmt.setString(1, uid);
			result = psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
}
