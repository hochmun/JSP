package ko.co.college.DAO;

import java.util.ArrayList;
import java.util.List;

import ko.co.college.bean.studentBean;
import ko.co.college.config.DBCP;
import ko.co.college.config.Sql;

public class studentDAO extends DBCP {
	public studentDAO() {
		try {
			conn = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("student 연결 오류");
			logger.error(e.getMessage());
		}
	}
	
	// Create
	/**
	 * 학생 등록
	 * @param sb
	 * @return
	 */
	public int studentInsert(studentBean sb) {
		int result = 0;
		try {
			psmt = conn.prepareStatement(Sql.INSERT_STUDENT);
			psmt.setString(1, sb.getStdno());
			psmt.setString(2, sb.getStdName());
			psmt.setString(3, sb.getStdHp());
			psmt.setInt(4, sb.getStdYear());
			psmt.setString(5, sb.getStdAddress());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("student 등록 오류!");
			logger.error(e.getMessage());
		}
		return result;
	}
	
	// read
	/**
	 * 학생 테이블 리스트 읽기
	 * @return List<studentBean>
	 */
	public List<studentBean> studentList() {
		List<studentBean> sbs = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_STUDENT_LIST);
			while(rs.next()) {
				studentBean sb = new studentBean();
				sb.setStdno(rs.getString(1));
				sb.setStdName(rs.getString(2));
				sb.setStdHp(rs.getString(3));
				sb.setStdYear(rs.getInt(4));
				sb.setStdAddress(rs.getString(5));
				sbs.add(sb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("student 리스트 읽기 오류");
			logger.error(e.getMessage());
		}
		return sbs;
	}
	
	
	// upload
	
	// delete
}
