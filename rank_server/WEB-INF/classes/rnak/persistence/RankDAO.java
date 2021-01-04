package rnak.persistence;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import rank.domain.AddRankVO;
import rank.domain.RankVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RankDAO {

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
	

	public ArrayList<RankVO> getRankListToStore(String store) { // 상점명기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where store like CONCAT('%',(?),'%') order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, store);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyword = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String title = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				
				Rank.setImage_link(image_link);
				Rank.setIdx(idx);
				Rank.setKeyword(keyword);
				Rank.setStore(stored);
				Rank.setTitle(title);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);
				Rank.setPage_num(page_num);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	public ArrayList<RankVO> getRankListToStoreDate(String store, String date, String date2) { // 상점명기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where store like CONCAT('%',(?),'%') and search_date>=(?) AND search_date<=(?) order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, store);
			pstmt.setString(2, date);
			pstmt.setString(3, date2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyword = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String title = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				
				Rank.setImage_link(image_link);
				Rank.setIdx(idx);
				Rank.setKeyword(keyword);
				Rank.setStore(stored);
				Rank.setTitle(title);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);
				Rank.setPage_num(page_num);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}

	public ArrayList<RankVO> getRankListToCode(String product_code,String keyword) { // 상품코드 기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where product_code=(?) and keyword=(?) group by rank,DATE_FORMAT(search_date,'%y%m%d%p') order by search_date DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product_code);
			pstmt.setString(2, keyword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String store = rs.getString("store");
				String product_coded = rs.getString("product_code");
				String title = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(store);
				Rank.setTitle(title);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_coded);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}

	public ArrayList<RankVO> getRankListToTitle(String title) { // 상품명기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where title like CONCAT('%',(?),'%') order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyword = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyword);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}

	public ArrayList<RankVO> getRankListToKeyword(String keyword) { // 키워드기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where keyword like CONCAT('%',(?),'%') order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	public ArrayList<RankVO> getRankListToKeywordDate(String keyword,String date,String date2) { // 키워드기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where keyword like CONCAT('%',(?),'%') and search_date>=(?) AND search_date<=(?) order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, date);
			pstmt.setString(3, date2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<RankVO> getRankListToUrl(String url) { // url기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where product_link like CONCAT('%',(?),'%') order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, url);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyword = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyword);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	public ArrayList<RankVO> getRankListToUrlDate(String url, String date,String date2) { // url기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where product_link like CONCAT('%',(?),'%') and search_date>=(?) AND search_date<=(?) order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, url);
			pstmt.setString(2,date);
			pstmt.setString(3, date2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyword = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyword);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<RankVO> getRankListToMultiKP(String keyword, String store) { // 키워드, 상점명기반 상품 추적

		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where keyword like CONCAT('%',(?),'%') and store like CONCAT('%',(?),'%') order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, store);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<RankVO> getRankListToMultiKPDate(String keyword, String store, String date,String date2) { // 키워드, 상점명기반 상품 추적

		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where keyword like CONCAT('%',(?),'%') and store like CONCAT('%',(?),'%') and search_date>=(?) AND search_date<=(?) order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, store);
			pstmt.setString(3, date);
			pstmt.setString(4, date2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<RankVO> getRankResultToKP(String keyword, String store) { // 키워드, 상점명기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where keyword like CONCAT('%',(?),'%') and store like CONCAT('%',(?),'%') group by search_date order by search_date desc limit 0,1";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, store);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<RankVO> getRankListToMultiPU(String store, String url) { // 상점명, url기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where store like CONCAT('%',(?),'%') and product_link like  CONCAT('%',(?),'%') order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, store);
			pstmt.setString(2, url);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	
	public ArrayList<RankVO> getRankListToMultiPUDate(String store, String url,String date,String date2) { // 상점명, url기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where store like CONCAT('%',(?),'%') and product_link like  CONCAT('%',(?),'%') and search_date>=(?) AND search_date<=(?) order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, store);
			pstmt.setString(2, url);
			pstmt.setString(3, date);
			pstmt.setString(4, date2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}

	public ArrayList<RankVO> getRankResultToSU(String store, String url) { // 상점명, url기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where store like CONCAT('%',(?),'%') and product_link like  CONCAT('%',(?),'%') and search_date=current_date() group by search_date order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, store);
			pstmt.setString(2, url);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<RankVO> getRankListToMultiKU(String keyword, String url) { // 키워드, url기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where keyword like CONCAT('%',(?),'%') and product_link like CONCAT('%',(?),'%') order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, url);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<RankVO> getRankListToMultiKUDate(String keyword, String url, String date,String date2) { // 키워드, url기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where keyword like CONCAT('%',(?),'%') and product_link like CONCAT('%',(?),'%') and search_date>=(?) AND search_date<=(?) order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, url);
			pstmt.setString(3, date);
			pstmt.setString(4, date2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<RankVO> getRankResultToKU(String keyword, String url) { // 키워드, url기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where keyword like CONCAT('%',(?),'%') and product_link like CONCAT('%',(?),'%') order by search_date desc limit 0,1";
//		String sql = "select * from cr_result_test where keyword like CONCAT('%',(?),'%') and product_link =(?) and search_date=current_date() order by search_date desc";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, url);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<RankVO> getRankListToMultiKPU(String keyword,String store, String url) { // 키워드, 상점명, url기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where keyword like CONCAT('%',(?),'%') and store like CONCAT('%',(?),'%') and product_link like CONCAT('%',(?),'%') order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, store);
			pstmt.setString(3, url);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	public ArrayList<RankVO> getRankListToMultiKPUDate(String keyword,String store, String url, String date,String date2) { // 키워드, 상점명, url기반 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where keyword like CONCAT('%',(?),'%') and store like CONCAT('%',(?),'%') and product_link like CONCAT('%',(?),'%') and search_date>=(?) AND search_date<=(?) order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, store);
			pstmt.setString(3, url);
			pstmt.setString(4, date);
			pstmt.setString(5, date2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyworded = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String titled = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyworded);
				Rank.setStore(stored);
				Rank.setTitle(titled);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<RankVO> getRankListToAll() { // 전체 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			// pstmt.setString(1, store);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyword = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String title = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyword);
				Rank.setStore(stored);
				Rank.setTitle(title);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<RankVO> getRankListToAllDate(String date,String date2) { // 전체 상품 추적
		connect();
		ArrayList<RankVO> Ranklist = new ArrayList<RankVO>();
		String sql = "select * from cr_result_test where search_date>=(?) AND search_date<=(?) order by search_date desc";
		try {
			pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, date);
			 pstmt.setString(2, date2);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				RankVO Rank = new RankVO();
				String idx = rs.getString("idx");
				String keyword = rs.getString("keyword");
				String stored = rs.getString("store");
				String product_code = rs.getString("product_code");
				String title = rs.getString("title");
				String review = rs.getString("review");
				String purchase = rs.getString("purchase");
				String zzim = rs.getString("zzim");
				String regist_date = rs.getString("regist_date");
				String search_date = rs.getString("search_date");
				String rank = rs.getString("rank");
				String cheap_bool = rs.getString("cheap_bool");
				String product_link = rs.getString("product_link");
				String page_link = rs.getString("page_link");
				String page_num =rs.getString("page_num");
				String image_link = rs.getString("image_link");
				
				Rank.setImage_link(image_link);
				Rank.setPage_num(page_num);
				Rank.setIdx(idx);
				Rank.setKeyword(keyword);
				Rank.setStore(stored);
				Rank.setTitle(title);
				Rank.setReview(review);
				Rank.setPurchase(purchase);
				Rank.setZzim(zzim);
				Rank.setRegist_date(regist_date);
				Rank.setSearch_date(search_date);
				Rank.setRank(rank);
				Rank.setProduct_code(product_code);
				Rank.setCheap_bool(cheap_bool);
				Rank.setProcduct_link(product_link);
				Rank.setPage_link(page_link);

				Ranklist.add(Rank);

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	

	public ArrayList<AddRankVO> getRankResultToAdd(String store, String keyword) { // 상품코드명 기반 광고순위 추적
		connect();
		ArrayList<AddRankVO> Ranklist = new ArrayList<AddRankVO>();
		String sql = "select * from cr_add_result_test where keyword like CONCAT('%',(?),'%') and store like CONCAT('%',(?),'%') order by search_date desc limit 0,1";
		System.out.println(store+","+keyword);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, store);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				AddRankVO Rank = new AddRankVO();
				String idx = rs.getString("idx");
				String keywored = rs.getString("keyword");
				String title = rs.getString("title");
				String page_num = rs.getString("page_num");
				String add_rank = rs.getString("add_rank");
				String page_link = rs.getString("page_link");
				String search_date = rs.getString("search_date");
				

				Rank.setIdx(idx);
				Rank.setKeyword(keywored);
				Rank.setTitle(title);
				Rank.setPage_num(page_num);
				Rank.setAdd_rank(add_rank);
				Rank.setPage_link(page_link);
				Rank.setSearch_date(search_date);

				Ranklist.add(Rank);

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<AddRankVO> getRankListToAdd(String store, String keyword) { // 상품코드명 기반 광고순위 추적내역 조회
		connect();
		ArrayList<AddRankVO> Ranklist = new ArrayList<AddRankVO>();
		String sql = "select * from cr_add_result_test where keyword like CONCAT('%',(?),'%') and store like CONCAT('%',(?),'%') order by search_date desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, store);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				AddRankVO Rank = new AddRankVO();
				String idx = rs.getString("idx");
				String keywored = rs.getString("keyword");
				String title = rs.getString("title");
				String page_num = rs.getString("page_num");
				String add_rank = rs.getString("add_rank");
				String page_link = rs.getString("page_link");
				String search_date = rs.getString("search_date");
				

				Rank.setIdx(idx);
				Rank.setKeyword(keywored);
				Rank.setTitle(title);
				Rank.setPage_num(page_num);
				Rank.setAdd_rank(add_rank);
				Rank.setPage_link(page_link);
				Rank.setSearch_date(search_date);

				Ranklist.add(Rank);

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<AddRankVO> getRankListToAddAll() { // 상품코드명 기반 광고순위 추적내역 조회
		connect();
		ArrayList<AddRankVO> Ranklist = new ArrayList<AddRankVO>();
		String sql = "select * from cr_add_result_test order by search_date desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				AddRankVO Rank = new AddRankVO();
				String idx = rs.getString("idx");
				String keywored = rs.getString("keyword");
				String title = rs.getString("title");
				String page_num = rs.getString("page_num");
				String add_rank = rs.getString("add_rank");
				String page_link = rs.getString("page_link");
				String search_date = rs.getString("search_date");
				

				Rank.setIdx(idx);
				Rank.setKeyword(keywored);
				Rank.setTitle(title);
				Rank.setPage_num(page_num);
				Rank.setAdd_rank(add_rank);
				Rank.setPage_link(page_link);
				Rank.setSearch_date(search_date);

				Ranklist.add(Rank);

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<AddRankVO> getRankListToAddDate(String store, String keyword, String date,String date2) { // 상품코드명 기반 광고순위 추적내역 조회
		connect();
		ArrayList<AddRankVO> Ranklist = new ArrayList<AddRankVO>();
		String sql = "select * from cr_add_result_test where keyword like CONCAT('%',(?),'%') and store like CONCAT('%',(?),'%') and search_date>=(?) AND search_date<=(?) order by search_date desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, store);
			pstmt.setString(3, date);
			pstmt.setString(4, date2);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				AddRankVO Rank = new AddRankVO();
				String idx = rs.getString("idx");
				String keywored = rs.getString("keyword");
				String title = rs.getString("title");
				String page_num = rs.getString("page_num");
				String add_rank = rs.getString("add_rank");
				String page_link = rs.getString("page_link");
				String search_date = rs.getString("search_date");
				

				Rank.setIdx(idx);
				Rank.setKeyword(keywored);
				Rank.setTitle(title);
				Rank.setPage_num(page_num);
				Rank.setAdd_rank(add_rank);
				Rank.setPage_link(page_link);
				Rank.setSearch_date(search_date);

				Ranklist.add(Rank);

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	
	public ArrayList<AddRankVO> getRankListToAddDateAll(String date,String date2) { // 상품코드명 기반 광고순위 추적내역 조회
		connect();
		ArrayList<AddRankVO> Ranklist = new ArrayList<AddRankVO>();
		String sql = "select * from cr_add_result_test where search_date>=(?) AND search_date<=(?) order by search_date desc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2,date2);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				AddRankVO Rank = new AddRankVO();
				String idx = rs.getString("idx");
				String keywored = rs.getString("keyword");
				String title = rs.getString("title");
				String page_num = rs.getString("page_num");
				String add_rank = rs.getString("add_rank");
				String page_link = rs.getString("page_link");
				String search_date = rs.getString("search_date");
				

				Rank.setIdx(idx);
				Rank.setKeyword(keywored);
				Rank.setTitle(title);
				Rank.setPage_num(page_num);
				Rank.setAdd_rank(add_rank);
				Rank.setPage_link(page_link);
				Rank.setSearch_date(search_date);

				Ranklist.add(Rank);

			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Ranklist;
	}
	public boolean deleteRankListToAll() { // 상품추적내용 전체 삭제
		connect();
		String sql = "delete from cr_result_test ";
		try {
			pstmt = conn.prepareStatement(sql);
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
	
	public boolean deleteRankListToSelected(String idx) { // 상품추적내용 일부 삭제
		connect();
		String sql = "delete from cr_result_test where idx=(?)";
		
		try {
			System.out.println(idx);
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
	
	
	public boolean deleteAddListToAll() { // 광고 상품추적내용 전체 삭제
		connect();
		String sql = "delete from cr_add_result_test ";
		try {
			pstmt = conn.prepareStatement(sql);
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
	
	public boolean deleteAddListToSelected(String idx) { // 광고 상품추적내용 일부 삭제
		connect();
		String sql = "delete from cr_add_result_test where idx = (?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			pstmt.executeUpdate();
			System.out.println(idx+"번 광고 데이터 삭제됨.");
			
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
