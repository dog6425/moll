package dao;

import java.sql.*;
import java.util.*;

import commons.DBUtil;
import vo.*;

public class ProductDao {
	
	public Product selectProductOne(int productId) throws Exception {
		Product product = null;
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "select * from product where product_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, productId);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			product = new Product();
			product.setProductId(rs.getInt("product_id"));
			product.setCategoryId(rs.getInt("category_id"));
			product.setProductName(rs.getString("product_name"));
			product.setProductPrice(rs.getInt("product_price"));
			product.setProductContent(rs.getString("product_content"));
			product.setProductSoldout(rs.getString("product_soldout"));
			
		}
		return product;
	}
	
	public void updateProductSoldout(int productId, String productSoldout) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "update product set product_soldout = ? where product_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		if(productSoldout.equals("Y")) {
			stmt.setString(1,"N");
		}else {
			stmt.setNString(1, "Y");
		}
		stmt.setInt(2, productId);
		int row= stmt.executeUpdate();
		System.out.println(row+"Çà ¼öÁ¤");
		}
		
	public Product selectProductOne1(int productId) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
			Product product = null;
			
			String sql = "select * from product where product_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPrice(rs.getInt("product_price"));
				product.setProductContent(rs.getString("product_content"));
				product.setProductSoldout(rs.getString("product_soldout"));
			}
			return product;
		}
	
	
	public ArrayList<Product> selectProductListByCategoryId(int categoryId) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		ArrayList<Product> list = new ArrayList<Product>();
		
		String sql = "select * from product where category_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Product p = new Product();
			p.setProductId(rs.getInt("product_id"));
			p.setCategoryId(rs.getInt("category_id"));
			p.setProductName(rs.getString("product_name"));
			p.setProductPrice(rs.getInt("product_price"));
			p.setProductSoldout(rs.getString("product_soldout"));
			list.add(p);
		}
		return list;	
	}
	
	public ArrayList<Product> selectProductList() throws Exception {
		DBUtil dbUtil=new DBUtil();
		Connection conn = dbUtil.getConnection();
		ArrayList<Product> list = new ArrayList<Product>();
		
		String sql = "select product_id,category_id,product_name,product_price,product_soldout from product";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Product p = new Product();
			p.setProductId(rs.getInt("product_id"));
			p.setCategoryId(rs.getInt("category_id"));
			p.setProductName(rs.getString("product_name"));
			p.setProductPrice(rs.getInt("product_price"));
			p.setProductSoldout(rs.getString("product_soldout"));
			list.add(p);
		}
		conn.close();
		
		return list;	
	}


	
	
	public void insertProduct(Product product) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String driver = "org.mariadb.jdbc.Driver";
		String dbaddr = "jdbc:mariadb://localhost:3306/mall";
		String dbid = "root";
		String dbpw = "java1004";
		Class.forName(driver);
		Connection conn1 = DriverManager.getConnection(dbaddr, dbid, dbpw);
		String sql = "insert into product(category_id,product_name,product_price,product_content,product_soldout) values(?,?,?,?,?)  ";
		PreparedStatement stmt = conn1.prepareStatement(sql);
		
		stmt.setString(1, product.getCategoryId());
		stmt.setString(2, product.getProductName());
		stmt.setInt(3, product.getProductPrice());
		stmt.setString(4, product.getProductContent());
		stmt.setString(5, product.getProductSoldout());
		
		stmt.executeLargeUpdate();
		
	
		}
	}
