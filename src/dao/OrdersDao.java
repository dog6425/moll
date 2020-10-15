package dao;

import java.util.*;

import commons.DBUtil;
import vo.*;
import java.sql.*;

;
public class OrdersDao {
	public void insertMember(Member member) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "insert into member(member_email, member_pw, member_name, member_date) values(?,?,?,now())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberEmail());
		stmt.setString(2, member.getMemberPw());
		stmt.setString(3, member.getMemberName());
		stmt.executeLargeUpdate();
		conn.close();

		
		
	}
	public void updateOrdersState(Orders orders) { 
		String sql = "update orders set orders_state=? where orders_id = ?";
	}
	
	public Orders selectOrdersOne(int ordersId) {
		String sql = "select orders_id, orders_state from orders where orders_id=?";
		return null;
	}
	
	public ArrayList<String> selectOrdersStateList() throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		//sql 서버
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";

		// 드라이버 로드 및 DB에 연결
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn + "<-conn"); 
		// sql문
		String sql = "SELECT DISTINCT orders_state FROM orders";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.println(stmt + "<-stmt");

		ResultSet rs = stmt.executeQuery();
		System.out.println(rs + "<-rs");
		while (rs.next()) {
			list.add(rs.getString("orders_state"));
		}

		conn.close();

		return list;
	}

	//
	public ArrayList<OrdersAndProduct> selectOrdersList() throws Exception {
		ArrayList<OrdersAndProduct> list = new ArrayList<OrdersAndProduct>();
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
		System.out.println(conn + "<-conn"); // 테스트용 출력
		/*
		 * select o.orders_id, 
		 * 		  o.product_id, 
		 * 		  o.orders_amount, 
		 * 		  o.orders_price, 
		 * 		  o.member_email, 
		 * 		  o.orders_addr, 
		 * 		  o.orders_state, 
		 * 		  o.orders_date, 
		 * 		  p.product_name
		 *		 from orders o inner join product p
		 * 		on o.product_id = p.product_id
		 */
		String sql = "select o.*, p.product_name from orders o inner join product p on o.product_id = p.product_id";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			OrdersAndProduct oap = new OrdersAndProduct();
			
			oap.setOrdersId( rs.getInt("o.orders_id"));
			oap.setProductId(rs.getInt("o.product_id"));
			oap.setOrdersAmount(rs.getInt("o.orders_amount"));
			oap.setOrdersPrice(rs.getInt("o.orders_price"));
			oap.setMemberEmail(rs.getString("o.member_email"));
			oap.setOrdersAddr(rs.getString("o.orders_addr"));
			oap.setOrdersState(rs.getString("o.orders_state"));
			oap.setOrdersDate(rs.getString("o.orders_date"));
			oap.setProductName(rs.getString("p.product_name"));
			list.add(oap);
		}
		
		return list;
	}

	public ArrayList<OrdersAndProduct> selectOrdersListByState(String ordersState) throws Exception {
		
		return null;
	}
}
