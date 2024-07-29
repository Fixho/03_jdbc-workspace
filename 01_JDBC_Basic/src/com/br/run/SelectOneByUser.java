package com.br.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.br.model.vo.Test;

public class SelectOneByUser {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 필요한 변수 미리 세팅
		Test t = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		System.out.print("조회하고자 하는 번호 입력: ");
		int no = sc.nextInt();
		
		String sql = "SELECT TNO, TNAME, TDATE FROM TEST WHERE TNO = " + no;
		
		try {
			
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성 == db에 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement 객체 생성 
			stmt = conn.createStatement();
			
			// 4, 5) sql문 실행후 결과받기
			rset = stmt.executeQuery(sql);
			
			// 6) rset으로부터 데이터값 뽑아서 Test객체대입
			if(rset.next()) {
				t = new Test(rset.getInt("TNO"), rset.getString("tname"), rset.getDate("tdate"));
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 7) 자원반납
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 조회된 결과 출력
		if(t == null) {
			System.out.println("조회 결과가 없습니다.");
		}else {
			System.out.println(t);
		}
		

	}

}
