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
	
	// read
	/**
	 * 로그인
	 * @param uid
	 * @param password
	 * @return BoardUserDTO
	 */
	public BoardUserDTO selectUser(String uid, String password) {
		BoardUserDTO budto = new BoardUserDTO();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, password);
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
			e.printStackTrace();
		}
		return budto;
	}
	
	/**
	 * <p>2022/11/15 아이디 중복 체크</p>
	 * @author 심규영
	 * @param uid
	 * @return
	 */
	public int uidCheck(String uid) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.CHECK_UID);
			psmt.setString(1, uid);
			result = psmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// upload
	
	// delete
}
