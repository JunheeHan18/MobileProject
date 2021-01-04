package rnak.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import rank.domain.ProductVO;
import rank.domain.RankVO;

public class ProductDAO {

	
	Connection conn = null;
	PreparedStatement pstmt = null;

	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://product-database.ci5ui27outxn.ap-northeast-2.rds.amazonaws.com:3306/crdb?characterEncoding=utf8&serverTimezone=UTC";

	public void connect() { // 커넥터
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "admin", "fkdlxmgudwp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void disConnect() { //dis커넥터
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	}
	
	public ArrayList<ProductVO> getProductListToAll() { // 전체 상품 추적
		connect();
		ArrayList<ProductVO> Productlist = new ArrayList<ProductVO>();
		String sql = "select * from product_list order by priority, idx desc";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductVO product = new ProductVO();
				product.setPriority(rs.getString("priority"));
				product.setIdx(rs.getString("idx"));
				product.setProduct_code(rs.getString("product_code"));
				product.setProduct_code_seller(rs.getString("product_code_seller"));
				product.setProduct_link(rs.getString("product_link"));
				product.setProduct_name(rs.getString("product_name"));
				product.setKeyword_first(rs.getString("keyword_first"));
				product.setKeyword_second(rs.getString("keyword_second"));
				product.setKeyword_third(rs.getString("keyword_third"));
				product.setKeyword_four(rs.getString("keyword_four"));
				product.setKeyword_first_rank(rs.getString("keyword_first_rank"));
				product.setKeyword_second_rank(rs.getString("keyword_second_rank"));
				product.setKeyword_third_rank(rs.getString("keyword_third_rank"));
				product.setKeyword_four_rank(rs.getString("keyword_four_rank"));
				Productlist.add(product);

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Productlist;
	}
	
	public boolean updateProduct(ProductVO pvo) { // 상품정보 수정 ..
		connect();
		String sql = "update product_list set product_code=(?), product_code_seller=(?), product_name=(?), keyword_first=(?), keyword_second=(?), keyword_third=(?), keyword_four=(?), priority=(?) where product_link=(?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pvo.getProduct_code());
			pstmt.setString(2, pvo.getProduct_code_seller());
			pstmt.setString(3, pvo.getProduct_name());
			pstmt.setString(4, pvo.getKeyword_first());
			pstmt.setString(5, pvo.getKeyword_second());
			pstmt.setString(6, pvo.getKeyword_third());
			pstmt.setString(7, pvo.getKeyword_four());
			pstmt.setString(8, pvo.getPriority());
			pstmt.setString(9, pvo.getProduct_link());
			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			disConnect();
		}
		System.out.println("업데이트 SQL 돌아감");
		return true;
	}
	

	public boolean deleteProduct(String idx) { // 상품 목록에서 삭제 ..
		connect();
		String sql = "delete from product_list where idx=(?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			disConnect();
		}

		return true;
	}
	
	public boolean addProduct(ProductVO pvo) { // 상품 추가 하기..
		connect();
		String sql = "insert into product_list(priority,product_code,product_link,product_code_seller,product_name,keyword_first,keyword_second,keyword_third,keyword_four) values(?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pvo.getPriority());
			pstmt.setString(2, pvo.getProduct_code());
			pstmt.setString(3, pvo.getProduct_link());
			pstmt.setString(4, pvo.getProduct_code_seller());
			pstmt.setString(5, pvo.getProduct_name());
			pstmt.setString(6, pvo.getKeyword_first());
			pstmt.setString(7, pvo.getKeyword_second());
			pstmt.setString(8, pvo.getKeyword_third());
			pstmt.setString(9, pvo.getKeyword_four());


			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		} finally {
			disConnect();
		}
		return true;
	}


}
