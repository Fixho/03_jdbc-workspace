package com.br.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.br.model.vo.Member;

/*
 * Dao (Data Access Object)
 * DB와 직접적으로 접근해서 sql문 실행 결과 받기 (JDBC과정)
 * 결과를 Controller로 반환
 */
public class MemberDao {
	
	public List<Member> selectMemberList() {
		
		// select문(여러행 조회) => ResultSet객체 => 각 행들은 Member객체 => 리스트에 쌓기
		
		// 필요한 변수들 미리 세팅
		List<Member> list = new ArrayList<>(); // 텅빈리스트
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 실행할 sql문
		String sql = "SELECT * FROM MEMBER";
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2) Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			// 3) Statement 생성
			stmt = conn.createStatement();
			// 4, 5) sql문 실행 후 결과받기
			rset = stmt.executeQuery(sql);
			// 6) 조회된 데이터값 뽑아서 자바 객체로 담기
			//    한 행의 컬럼값들 => Member객체 생성 후 필드에 담기 => list에 추가
			while(rset.next()) {
				list.add(new Member(rset.getInt("user_no")
								  , rset.getString("user_id")
								  , rset.getString("user_pwd")
								  , rset.getString("user_name")
								  , rset.getString("gender")
								  , rset.getInt("age")
								  , rset.getString("email")
								  , rset.getString("phone")
								  , rset.getString("hobby")
								  , rset.getDate("regist_date")
								  ));
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
		
		return list; // 조회결과가없었을경우 텅빈리스트 | 조회결과가있었을경우 뭐라도담긴리스트
		
	}
	
	public int insertMember(Member m) {
		
		// insert문 => 처리된 행수 (int) => 트랜잭션 처리
		
		int result = 0;		// 처리된 결과(행수)를 받아줄 변수
		Connection conn = null; // DB연결정보를 담는 객체
		Statement stmt = null;	// "완성된 sql문(실제 값들이 채워진)" 전달해서 실행하는 객체
		
		// 실행할 sql문(완성형태 == 실제값들이 다 채워진)
		// INSERT INTO MEMBER VALUES(SEQ_UNO.NEXTVAL, 'XXXX', 'XXXXX', 'XXX', 'X', XX, 'XXXX', 'XXXXX', 'XXXXX', SYSDATE);
		String sql = "INSERT INTO MEMBER VALUES(SEQ_UNO.NEXTVAL, "
							+ "'" + m.getUserId() 	+ "', "
							+ "'" + m.getUserPwd()	+ "', "
							+ "'" + m.getUserName() + "', "
							+ "'" + m.getGender() 	+ "', "
								  + m.getAge()		+  ", "
							+ "'" + m.getEmail()	+ "', "
							+ "'" + m.getPhone()	+ "', "
							+ "'" + m.getHobby()	+ "', SYSDATE)";
		
		System.out.println(sql);
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2) Connection 객체 생성 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			// 4,5) sql문 실행 후 결과받기
			result = stmt.executeUpdate(sql);
			// 6) 트랜잭션 처리
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
				// 7) 자원반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
		
	}
	
	public Member selectMemberByUserId(String userId) {
		// select문(한행) => ResultSet객체 => Member객체
		
		Member m = null;		// 최종 조회결과를 담아낼 변수
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 실행할 sql문 (완성형태로)
		// SELECT * FROM MEMBER WHERE USER_ID = 'XXXXX'
		String sql = "SELECT * FROM MEMBER WHERE USER_ID = '" + userId + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				// 조회된 한 행에 대한 모든 컬럼값 뽑아서 Member객체 생성해서 각필드에 대입
				m = new Member(rset.getInt("user_no")
							 , rset.getString("user_id")
							 , rset.getString("user_pwd")
							 , rset.getString("user_name")
							 , rset.getString("gender")
							 , rset.getInt("age")
							 , rset.getString("email")
							 , rset.getString("phone")
							 , rset.getString("hobby")
							 , rset.getDate("regist_date"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return m;		// null | 조회결과가담긴Member객체
	}
	
	public List<Member> selectMemberByUserName(String userName) {
		// select문(여러행) => ResultSet객체 => 각 행 Member객체 => 리스트에 쌓기
		
		List<Member> list = new ArrayList<>();  // 텅빈리스트
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// SELECT * FROM MEMBER WHERE USER_NAME LIKE '%XX%'
		String sql = "SELECT * FROM MEMBER WHERE USER_NAME LIKE '%" + userName + "%'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				list.add(new Member(rset.getInt("user_no")
								  , rset.getString("user_id")
								  , rset.getString("user_pwd")
								  , rset.getString("user_name")
								  , rset.getString("gender")
								  , rset.getInt("age")
								  , rset.getString("email")
								  , rset.getString("phone")
								  , rset.getString("hobby")
								  , rset.getDate("regist_date")));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list; // 텅빈리스트 | 조회결과가담겨있는리스트
		
	}
	
	public int updateMember(Member m) {
		
		// update문 => 처리된 행수(int) => 트랜잭션 처리
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		/*
		 * UPDATE MEMBER
		 *    SET USER_PWD = 'XXXX'
		 *    	, EMAIL = 'XXXXX'
		 *    	, PHONE = 'XXXXXXX'
		 *    	, HOBBY = 'XXXXXX'
		 *  WHERE USER_ID = 'XXXXX'
		 */
		String sql = "UPDATE MEMBER "
					+   "SET USER_PWD = '" + m.getUserPwd() + "'"
					+     ", EMAIL = '" + m.getEmail() + "'"
					+	  ", PHONE = '" + m.getPhone() + "'"
					+	  ", HOBBY = '" + m.getHobby() + "'"
					+ "WHERE USER_ID = '" + m.getUserId() + "'";
		
		System.out.println(sql);
		
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
		
		return result;
		
	}
	
	public int deleteMember(String userId) {
		// delete 문 => 처리된 행수 => 트랜잭션 처리
		
		// DELETE FROM MEMBER WHERE USER_ID = 'XXXX'
		
	}
	
	
	
	
	
	

}
