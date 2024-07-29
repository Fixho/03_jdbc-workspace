package com.br.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete {
	
	public static void main(String[] args) {
		
		// 사용자에게 번호를 입력받아 해당 번호의 데이터를 삭제하시오.
		// DELETE FROM TEST WHERE TNO = 사용자가입력한번호 
		// delete문 => 처리된 행수 (int) => 트랜잭션 처리
		
		Scanner sc = new Scanner(System.in);
		
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		System.out.print("삭제하고자하는 번호: ");
		int no = sc.nextInt();
		
		String sql = "DELETE FROM TEST WHERE TNO = " + no;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if(result > 0) {
			System.out.println("성공적으로 삭제되었습니다.");
		}else {
			System.out.println("삭제하는데 실패했습니다.");
		}
		
		
		
	}

}
