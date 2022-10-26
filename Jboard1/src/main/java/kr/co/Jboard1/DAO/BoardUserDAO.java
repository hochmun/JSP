package kr.co.Jboard1.DAO;

import kr.co.Jboard1.bean.BoardUserBean;
import kr.co.Jboard1.config.DBCP;
import kr.co.Jboard1.config.Sql;
/**
 * 유저 DAO
 * @date 2022/10/20
 * @author 심규영
 * @method 	BoardUserDAO() - 데이터베이스 연결 <br>
 * 		   	RegisterBoardUser(String) - 회원 가입<br>
 * 		  	CheckId(uid) - 아이디 중복 체크<br>
 * 			CheckNick(nick) - 별명 중복 체크<br>
 * 			Close() - 닫기
 */
public class BoardUserDAO extends DBCP {
	/**
	 * 데이터베이스 연결
	 */
	public BoardUserDAO() {
		try {
			conn = getConnection();
		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류");
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원가입
	 * @category create
	 */
	public void RegisterBoardUser(String uid, String pass, String name, 
			String nick, String email, String hp, String zip, 
			String addr1, String addr2, String regip) {
		try {
			psmt = conn.prepareStatement(Sql.INSERT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			psmt.setString(3, name);
			psmt.setString(4, nick);
			psmt.setString(5, email);
			psmt.setString(6, hp);
			psmt.setString(7, zip);
			psmt.setString(8, addr1);
			psmt.setString(9, addr2);
			psmt.setString(10, regip);
			
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("회원가입 오류");
			e.printStackTrace();
		}
	}
	
	/**
	 * 중복 아이디 체크
	 * @return 중복된 아이디 갯수 반환
	 */
	public int CheckUid(String uid) {
		int result = -1;
		try {
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("중복 아이디 체크 오류");
			e.printStackTrace();
		}
		return result;
	 }
	 
	/**
	 * 중복 별명 체크
	 * @return 중복된 별명 갯수 반환
	 */
	public int CheckNick(String nick) {
		int result = -1;
		try {
			psmt = conn.prepareStatement(Sql.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("중복 별명 체크 오류");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 로그인 처리
	 * @author 심규영
	 * @since 2022/10/24
	 * @param uid
	 * @param pass
	 * @return BoardUserBean
	 */
	public BoardUserBean Login(String uid, String pass) {
		BoardUserBean bub = null;
		
		try {
			psmt = conn.prepareStatement(Sql.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			if (rs.next()) {
				bub = new BoardUserBean();
				bub.setUid(rs.getString(1));
				bub.setPass(rs.getString(2));
				bub.setName(rs.getString(3));
				bub.setNick(rs.getString(4));
				bub.setEmail(rs.getString(5));
				bub.setHp(rs.getString(6));
				bub.setGrade(rs.getInt(7));
				bub.setZip(rs.getString(8));
				bub.setAddr1(rs.getString(9));
				bub.setAddr2(rs.getString(10));
				bub.setRegip(rs.getString(11));
				bub.setRdate(rs.getString(12));
			}
		} catch(Exception e) {
			System.out.println("로그인 오류");
			e.printStackTrace();
		}
		
		return bub;
	}
}
