package kr.co.Farmstory_1.DAO;

import kr.co.Farmstory_1.DTO.BoardUserDTO;
import kr.co.Farmstory_1.config.DBCP;
import kr.co.Farmstory_1.config.Sql;

public class BoardUserDAO extends DBCP {
	private static BoardUserDAO instance = new BoardUserDAO();
	public static BoardUserDAO getInstance() {
		return instance;
	}
	private BoardUserDAO() {}
	
	// create
	/**
	 * 2022/11/16<br/>유저 회원가입
	 * @author 심규영
	 * @param budto
	 * @return 유저 회원가입 성공시 1 값 리턴 실패시 0 리턴<br/>
	 * either (1) the row count for SQL Data Manipulation Language (DML) statementsor (2) 0 for SQL statements that return nothing
	 */
	public int insertUser(BoardUserDTO budto) {
		int result = 0;
		try {
			logger.info("insertUser...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_USER);
			psmt.setString(1, budto.getUid());
			psmt.setString(2, budto.getPass());
			psmt.setString(3, budto.getName());
			psmt.setString(4, budto.getNick());
			psmt.setString(5, budto.getEmail());
			psmt.setString(6, budto.getHp());
			psmt.setString(7, budto.getZip());
			psmt.setString(8, budto.getAddr1());
			psmt.setString(9, budto.getAddr2());
			psmt.setString(10, budto.getRegip());
			result = psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("result : "+result);
		return result;
	}
	
	// read
	/**
	 * 로그인
	 * @param uid
	 * @param password
	 * @return BoardUserDTO
	 */
	public BoardUserDTO selectUser(String uid, String pass) {
		BoardUserDTO budto = new BoardUserDTO();
		try {
			logger.info("selectUser...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			if(rs.next()) {
				budto.setUid(rs.getString("uid"));
				budto.setPass(rs.getString("pass"));
				budto.setName(rs.getString("name"));
				budto.setNick(rs.getString("nick"));
				budto.setEmail(rs.getString("email"));
				budto.setHp(rs.getString("hp"));
				budto.setGrade(rs.getString("grade"));
				budto.setZip(rs.getString("zip"));
				budto.setAddr1(rs.getString("addr1"));
				budto.setAddr2(rs.getString("addr2"));
				budto.setRegip(rs.getString("regip"));
				budto.setRdate(rs.getString("rdate"));
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("budto : " + budto);
		return budto;
	}
	
	/**
	 * <p>2022/11/15 아이디 중복 체크</p>
	 * @author 심규영
	 * @param uid
	 * @return 동일한 아이디 갯수 리턴
	 */
	public int uidCheck(String uid) {
		int result = 0;
		try {
			logger.info("uidCheck...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("result : "+result);
		return result;
	}
	
	/**
	 * <p>2022/11/16 닉네임 중복 체크</p>
	 * @author 심규영
	 * @param nick
	 * @return 동일한 닉네임 갯수 리턴
	 */
	public int nickCheck(String nick) {
		int result = 0;
		try {
			logger.info("nickCheck...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.COUNT_NICK);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("result : "+result);
		return result;
	}
	
	// upload
	
	// delete
}
