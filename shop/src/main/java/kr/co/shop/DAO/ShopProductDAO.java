package kr.co.shop.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.shop.bean.productBean;
import kr.co.shop.config.DBCP;


public class ShopProductDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private Statement stmt;
	private ResultSet rs;
	
	public ShopProductDAO() {
		try {
			conn = DBCP.getConnection();
		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류");
			e.printStackTrace();
		}
	}
	
	public List<productBean> ShopProductListDAO(){
		List<productBean> pbs = new ArrayList<>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `product`");
			while(rs.next()) {
				productBean pb = new productBean();
				pb.setProdNo(rs.getInt(1));
				pb.setProdName(rs.getString(2));
				pb.setStock(rs.getInt(3));
				pb.setPrice(rs.getInt(4));
				pb.setCompany(rs.getString(5));
				pbs.add(pb);
			}
		} catch (Exception e) {
			System.out.println("고객 리스트 불러오기 오류");
			e.printStackTrace();
		}
		
		return pbs;
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
