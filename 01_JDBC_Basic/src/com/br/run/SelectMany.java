package com.br.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.br.model.vo.Test;

public class SelectMany {

	public static void main(String[] args) {
		
		// * 내 pc DB상에 JDBC계정에 있는 TEST 테이블의 모든 데이터 조회
		//   SELECT TNO, TNAME, TDATE FROM TEST  (여러행 조회될 sql문)
		
		// select문 => 조회결과 ResultSet 객체 => 한 행씩 Test객체 => ArrayList에 쌓기
		
		// 최종적으로 조회결과를 담아낼 ArrayList세팅
		List<Test> list = new ArrayList<>();  // 텅빈리스트
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT TNO, TNAME, TDATE FROM TEST";
		
		try {
			// 1)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2) 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			// 3) 
			stmt = conn.createStatement();
			// 4, 5)
			rset = stmt.executeQuery(sql);
			// 6) 
			while(rset.next()) { // 조회된 모든 행을 스캔하기 위해
				
				//Test t = new Test(rset.getInt("TNO"), rset.getString("TNAME"), rset.getDate("TDATE"));
				//list.add(t);
				
				list.add(new Test(rset.getInt("TNO")
								, rset.getString("TNAME")
								, rset.getDate("TDATE")));
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				// 7)
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		// 조회된 결과 출력
		if(list.isEmpty()) { // 비어있을 경우 => 조회결과가 없음
			System.out.println("조회된 데이터가 없습니다.");
		}else {
			/*
			for(int i=0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
			*/
			for(Test t : list) {
				System.out.println(t);
			}
		}
		
		
		
		
		
	}

}
