package com.br.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update {

	public static void main(String[] args) {
		
		// * 특정 행 수정 
		/*
		 * UPDATE TEST
		 *    SET TNAME = '사용자가입력한값'
		 *  WHERE TNO = 사용자가입력한번호
		 */
		
		// update문 => 처리된 행수 (int) => 트랜잭션 처리
		Scanner sc = new Scanner(System.in);
		
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		System.out.print("수정하고자 하는 번호: ");
		int no = sc.nextInt();
		sc.nextLine();
		
		System.out.print("수정할 내용: ");
		String name = sc.nextLine();
		
		String sql = "UPDATE TEST "
					  + "SET TNAME = '" + name + "' "
					+ "WHERE TNO = " + no;
		
		//System.out.println(sql);
		
		try {
			// 1)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			// 3)
			stmt = conn.createStatement();
			// 4, 5)
			result = stmt.executeUpdate(sql);
			// 6) 
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
				// 7)
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(result > 0) {
			System.out.println("성공적으로 수정되었습니다.");
		}else {
			System.out.println("수정하는데 실패하였습니다.");
		}

	}

}
