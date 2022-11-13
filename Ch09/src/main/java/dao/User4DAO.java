package dao;

import java.util.ArrayList;
import java.util.List;

import db.DBCP;
import vo.User4VO;

public class User4DAO extends DBCP {
	private static User4DAO instance = new User4DAO();
	public static User4DAO getInstance() {
		return instance;
	}
	private User4DAO() {}
	
	// create
	public void insertUser4(User4VO uvo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("insert into `user4` (`name`,`gender`,`age`,`addr`) values (?,?,?,?)");
			psmt.setString(1, uvo.getName());
			psmt.setInt(2, uvo.getGender());
			psmt.setInt(3, uvo.getAge());
			psmt.setString(4, uvo.getAddr());
			psmt.executeUpdate();
			close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// read
	public User4VO selectUser4(String seq) {
		User4VO vo = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("select * from `user4` where `seq`=?");
			psmt.setString(1, seq);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new User4VO();
				vo.setSeq(rs.getString("seq"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setAge(rs.getInt("age"));
				vo.setAddr(rs.getString("addr"));
			}
			
			close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public List<User4VO> selectUsers4() {
		List<User4VO> users = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `user4`");
			
			while(rs.next()) {
				User4VO vo = new User4VO();
				vo.setSeq(rs.getInt("seq"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getInt("gender"));
				vo.setAge(rs.getInt("age"));
				vo.setAddr(rs.getString("addr"));
				users.add(vo);
			}
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	// upload
	public void updateUser4(User4VO vo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement
					("update `user4` set `name`=?, `gender`=?, `age`=?, `addr`=? where `seq`=?");
			psmt.setString(1, vo.getName());
			psmt.setInt(2, vo.getGender());
			psmt.setInt(3, vo.getAge());
			psmt.setString(4, vo.getAddr());
			psmt.setInt(5, vo.getSeq());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// delete
	public void deleteUser4(String seq) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("delete from `user4` where `seq`=?");
			psmt.setString(1, seq);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
