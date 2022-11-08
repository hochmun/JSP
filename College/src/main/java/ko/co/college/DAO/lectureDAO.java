package ko.co.college.DAO;

import java.util.ArrayList;
import java.util.List;

import ko.co.college.bean.lectureBean;
import ko.co.college.config.DBCP;
import ko.co.college.config.Sql;

public class lectureDAO extends DBCP {
	public lectureDAO () {
		try {
			conn = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("lecture 연결 오류");
			logger.error(e.getMessage());
		}
	}
	
	// Create
	
	/**
	 * 강좌 등록
	 * @param lb
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statementsor (2) 0 for SQL statements that return nothing
	 */
	public int insertLecture(lectureBean lb) {
		int result = 0;
		try {
			psmt = conn.prepareStatement(Sql.INSERT_LECTURE);
			psmt.setInt(1, lb.getLecNo());
			psmt.setString(2, lb.getLecName());
			psmt.setInt(3, lb.getLecCredit());
			psmt.setInt(4, lb.getLecTime());
			psmt.setString(5, lb.getLecClass());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("lecture 등록 오류");
			logger.error(e.getMessage());
		}
		return result;
	}
	
	// read
	
	/**
	 * 강좌 리스트 불러오기
	 * @return List<lectureBean>
	 */
	public List<lectureBean> readLectureList() {
		List<lectureBean> lecturelist = new ArrayList<lectureBean>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_LECTURE_LIST);
			while(rs.next()) {
				lectureBean lb = new lectureBean();
				lb.setLecNo(rs.getInt("lecNo"));
				lb.setLecName(rs.getString("lecName"));
				lb.setLecCredit(rs.getInt("lecCredit"));
				lb.setLecTime(rs.getInt("lecTime"));
				lb.setLecClass(rs.getString("lecClass"));
				lecturelist.add(lb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("lecture list 불러오기 오류");
			logger.error(e.getMessage());
		}
		return lecturelist;
	}
	
	public List<lectureBean> readLecNameList() {
		List<lectureBean> lbs = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_LECTURE_NAME_LIST);
			while(rs.next()) {
				lectureBean lb = new lectureBean();
				lb.setLecName(rs.getString("lecName"));
				lb.setLecNo(rs.getInt("lecNo"));
				lbs.add(lb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("lecture name list 불러오기 오류");
			logger.error(e.getMessage());
		}
		return lbs;
	}
	
	// upload
	
	// delete
}
