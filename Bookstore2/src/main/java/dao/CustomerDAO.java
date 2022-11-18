package dao;

import java.util.ArrayList;
import java.util.List;

import config.DBCP;
import config.Sql;
import vo.CustomerVO;

public class CustomerDAO extends DBCP {
	private static CustomerDAO instance = new CustomerDAO();
	public static CustomerDAO getInstance() {
		return instance;
	}
	private CustomerDAO() {}
	
	// create
	/**
	 * 고객 등록
	 * @param cv
	 */
	public void insertCustomer(CustomerVO cv) {
		try {
			logger.info("insertCustomer...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_CUSTOMER);
			psmt.setString(1, cv.getName());
			psmt.setString(2, cv.getAddress());
			psmt.setString(3, cv.getPhone());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	// read
	/**
	 * 고객 리스트 불러오기
	 * @return
	 */
	public List<CustomerVO> selectCustomers() {
		List<CustomerVO> cvs = new ArrayList<>();
		try {
			logger.info("selectCustomers...");
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_CUSTOMERS);
			while(rs.next()) {
				CustomerVO cv = new CustomerVO();
				cv.setCustId(rs.getInt("custId"));
				cv.setName(rs.getString("name"));
				cv.setAddress(rs.getString("address"));
				cv.setPhone(rs.getString("phone"));
				cvs.add(cv);
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return cvs;
	}
	
	/**
	 * 고객 정보 불러오기
	 * @param custId
	 * @return
	 */
	public CustomerVO selectCustomer(String custId) {
		CustomerVO cv = new CustomerVO();
		try {
			logger.info("selectCustomer...");
			logger.debug(custId);
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_CUSTOMER);
			psmt.setString(1, custId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				cv.setCustId(rs.getInt("custId"));
				cv.setName(rs.getString("name"));
				cv.setAddress(rs.getString("address"));
				cv.setPhone(rs.getString("phone"));
			}
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("cv : "+cv);
		return cv;
	}
	
	// upload
	/**
	 * 고객 정보 수정
	 * @param cv
	 */
	public void updateCustomer(CustomerVO cv) {
		try {
			logger.info("updateCustomer...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_CUSTOMER);
			psmt.setString(1, cv.getName());
			psmt.setString(2, cv.getAddress());
			psmt.setString(3, cv.getPhone());
			psmt.setInt(4, cv.getCustId());
			psmt.executeUpdate();
			close();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	// delete
	public void deleteCustomer(String custId) {
		try {
			logger.info("deleteCustomer...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.DELETE_CUSTOMER);
			psmt.setString(1, custId);
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
