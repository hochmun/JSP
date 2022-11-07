package ko.co.college.DAO;

import java.util.ArrayList;
import java.util.List;

import ko.co.college.bean.registerBean;
import ko.co.college.config.DBCP;
import ko.co.college.config.Sql;

public class registerDAO extends DBCP {
	public registerDAO () {
		try {
			conn = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("register 연결 오류");
			logger.error(e.getMessage());
		}
	}
	
	// Create
	
	// read
	/**
	 * 수강테이블 리스트 읽기
	 * @return
	 */
	public List<registerBean> readRegisterList() {
		List<registerBean> rbs = new ArrayList<registerBean>(); 
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_REGISTER_LIST);
			while(rs.next()) {
				registerBean rb = new registerBean();
				rb.setRegStdNo(rs.getString(1));
				rb.setStdName(rs.getString(2));
				rb.setLecName(rs.getString(3));
				rb.setRegLecNo(rs.getInt(4));
				rb.setRegMidScore(rs.getInt(5));
				rb.setRegFinalScore(rs.getInt(6));
				rb.setRegTotalScore(rs.getInt(7));
				rb.setRegGrade(rs.getString(8));
				rbs.add(rb);
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("register 리스트 읽기 오류");
			logger.error(e.getMessage());
		}
		return rbs;
	}
	
	/**
	 * 수강현황 검색 결과 읽기
	 * @param searchRegister
	 * @return
	 */
	public List<registerBean> searchRegisterList(String searchRegister) {
		List<registerBean> rbs = new ArrayList<registerBean>(); 
		try {
			psmt = conn.prepareStatement(Sql.SEARCH_REGISTER_LIST);
			psmt.setString(1, searchRegister);
			rs = psmt.executeQuery();
			while(rs.next()) {
				registerBean rb = new registerBean();
				rb.setRegStdNo(rs.getString(1));
				rb.setStdName(rs.getString(2));
				rb.setLecName(rs.getString(3));
				rb.setRegLecNo(rs.getInt(4));
				rb.setRegMidScore(rs.getInt(5));
				rb.setRegFinalScore(rs.getInt(6));
				rb.setRegTotalScore(rs.getInt(7));
				rb.setRegGrade(rs.getString(8));
				rbs.add(rb);
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("register 검색 리스트 읽기 오류");
			logger.error(e.getMessage());
		}
		return rbs;
	}
	
	// upload
	
	// delete
}
