package kr.co.shop.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.shop.bean.orderBean;
import kr.co.shop.config.DBCP;


public class ShopOrderDAO {
	private Connection conn;
	private PreparedStatement psmt;
	private Statement stmt;
	private ResultSet rs;
	
	public ShopOrderDAO() {
		try {
			conn = DBCP.getConnection();
		} catch (Exception e) {
			System.out.println("데이터 베이스 연결 오류");
			e.printStackTrace();
		}
	}
	
	public List<orderBean> ShopOrderListDAO(){
		List<orderBean> obs = new ArrayList<>();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"select "
					+ "a.`orderNo`,"
					+ "b.`name`,"
					+ "c.`prodName`,"
					+ "a.`orderCount`,"
					+ "a.`orderDate` "
					+ "from `order` as a"
					+ " join `customer` as b on a.orderId = b.custid"
					+ " join `product` as c on a.orderProduct = c.prodNo");
			while(rs.next()) {
				orderBean ob = new orderBean();
				ob.setOrderNo(rs.getInt(1));
				ob.setOrderName(rs.getString(2));
				ob.setProductName(rs.getString(3));
				ob.setOrderCount(rs.getInt(4));
				ob.setOrderDate(rs.getString(5));
				obs.add(ob);
			}
		} catch (Exception e) {
			System.out.println("주문 리스트 불러오기 오류");
			e.printStackTrace();
		}
		
		return obs;
	}
	
	
	public int ShopOrderAdd(String orderId, String orderProduct, String orderCount) {
		int result = 0;
		
		try {
			psmt = conn.prepareStatement(
					"insert into `order` (`orderId`, `orderProduct`, `orderCount`"
					+ ", `orderDate`) values (?,?,?,NOW())");
			psmt.setString(1, orderId);
			psmt.setString(2, orderProduct);
			psmt.setString(3, orderCount);
			
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("주문 등록 오류!");
			e.printStackTrace();
		}
		
		return result;
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
