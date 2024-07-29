package com.br.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Insert {

	public static void main(String[] args) {
	
		// * 한 행 insert 
		//   INSERT INTO TEST VALUES(SEQ_TNO.NEXTVAL, '테스트4', SYSDATE)
		
		// insert문 => 처리된 행수 (int) => 트랜잭션 처리
		
		// 처리된 행수를 받아줄 변수
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		
		Scanner sc = new Scanner(System.in);
		System.out.print("추가하고자 하는 이름: ");
		String name = sc.nextLine(); // 홍길동
		
		
		String sql = "INSERT INTO TEST VALUES(SEQ_TNO.NEXTVAL, '" + name + "', SYSDATE)";
		
		try {
			// 1)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			// 3)
			stmt = conn.createStatement();
			// 4, 5)
			result = stmt.executeUpdate(sql);
			
			// 6) 트랜잭션
			if(result > 0) { // 변경사항 생김 => commit
				conn.commit();
			}else { // => rollback
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 7)
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(result > 0) {
			System.out.println("성공적으로 추가되었습니다.");
		}else {
			System.out.println("추가하는데 실패했습니다.");
		}
		
	}
	
	
}
