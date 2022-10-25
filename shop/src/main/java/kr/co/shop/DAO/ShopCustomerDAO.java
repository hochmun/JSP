package kr.co.shop.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.shop.bean.customerBean;
import kr.co.shop.config.DBCP;


public class ShopCustomerDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private Statement stmt;
	private ResultSet rs;
	
	public ShopCustomerDAO() {
		try {
			conn = DBCP.getConnection();
		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류");
			e.printStackTrace();
		}
	}
	
	public List<customerBean> CustomerListDAO() {
		List<customerBean> cbs = new ArrayList<>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `customer`");
			while(rs.next()) {
				customerBean cb = new customerBean();
				cb.setCustid(rs.getString(1));
				cb.setName(rs.getString(2));
				cb.setHp(rs.getString(3));
				cb.setAddr(rs.getString(4));
				cb.setRdate(rs.getString(5));
				cbs.add(cb);
			}
		} catch (Exception e) {
			System.out.println("고객 리스트 불러오기 오류");
			e.printStackTrace();
		}
		
		return cbs;
	}
	
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(psmt!=null) psmt.close();
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			System.out.println("클래스 닫기 오류");
			e.printStackTrace();
		}
	}
}
