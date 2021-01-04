package rnak.persistence;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AccessToExcel {
   static Connection conn = null;
   static PreparedStatement pstmt = null;

   static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
   static String jdbc_url = "jdbc:mysql://product-database.ci5ui27outxn.ap-northeast-2.rds.amazonaws.com:3306/crdb?characterEncoding=utf8&serverTimezone=UTC";


   public static void main(String[] args) {
      // TODO Auto-generated method stub
      accessToExcel();
      
   }
   

   public static void connect() { // 커넥터
      try {
         Class.forName(jdbc_driver);
         conn = DriverManager.getConnection(jdbc_url, "admin", "fkdlxmgudwp");
      } catch (Exception e) {
         // TODO: handle exception
         e.printStackTrace();
      }

   }

   public static void disConnect() { //dis커넥터
      if (pstmt != null) {
         try {
            pstmt.close();
         } catch (Exception e) {
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
   
   public static void accessToExcel() {

      try {
            // 엑셀파일
            File file = new File("C://DevelopTool//eclipse-workspace/test.xlsx");
 
            // 엑셀 파일 오픈
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
           
       
           
            // 첫번재 sheet 내용 읽기
            for (Row row : wb.getSheetAt(0)) { 
                // 셋째줄부터..
     
                if (row.getRowNum() < 1) {
                    continue;
                }
                
                // 두번째 셀이 비어있으면 for문을 멈춘다.
                if(row.getCell(1) == null){
                    break;
                }
                
                
                insertData(row.getCell(0).toString(),row.getCell(1).toString(),row.getCell(2).toString(),row.getCell(3).toString(),row.getCell(4).toString(),row.getCell(5).toString(),row.getCell(6).toString(),row.getCell(7).toString(),row.getCell(8).toString(),row.getCell(9).toString(),row.getCell(10).toString(),row.getCell(11).toString(),row.getCell(12).toString());
                // 콘솔 출력
                
             
              //  System.out.println("[row] 코드 : " + row.getCell(0).toString() + ", url: " + row.getCell(1)
               //                 + ", 판매자코드 : " + row.getCell(2) + ", 상품명: " + row.getCell(3)+ ", 키워드1: " + row.getCell(4)+ ", 키워드2: " + row.getCell(5)
              //                  + ", 키워드3: " + row.getCell(6)+ ", 키워드4: " + row.getCell(7));
               
               
            }
            
            // 엑셀 파일 저장
            FileOutputStream fileOut = new FileOutputStream(file);
            wb.write(fileOut);
        } catch (FileNotFoundException fe) {
            System.out.println("FileNotFoundException >> " + fe.toString());
        } catch (IOException ie) {
            System.out.println("IOException >> " + ie.toString());
        }

      
   }
   
   public static void insertData(String priority,String code,String url,String code_seller, String title, String key1,String key1_r,String key2,String key2_r,String key3,String key3_r,String key4,String key4_r) {
      connect();
      String sql = "INSERT INTO product_list(priority,product_code,product_link,product_code_seller,product_name,keyword_first,keyword_first_rank,keyword_second,keyword_second_rank,keyword_third,keyword_third_rank,keyword_four,keyword_four_rank) VALUES"
            + "(?,?,?,?,?,?,?,?,?,?,?,?,?); ";
      try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, priority);
      pstmt.setString(2,code);
        pstmt.setString(3,url);
        pstmt.setString(4,code_seller);
        pstmt.setString(5,title);
        pstmt.setString(6,key1);
        pstmt.setString(7,key1_r);
        pstmt.setString(8,key2);
        pstmt.setString(9,key2_r);
        pstmt.setString(10, key3);
        pstmt.setString(11,key3_r);
        pstmt.setString(12, key4);
        pstmt.setString(13,key4_r);
        pstmt.executeUpdate();
        System.out.println("호출됨");
        disConnect();
      }catch (Exception e) {
         // TODO: handle exception
         e.printStackTrace();
      } finally {
         disConnect();
      }
   }

}