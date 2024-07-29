package com.br.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.br.model.vo.Test;

public class SelectOne {
	
	/*
	 * < JDBC >
	 * 1. Java DataBase Connectivity
	 * 2. 자바 프로그램상에서 DB와 연동할 수 있게 도와주는 API 
	 * 3. JDBC용 객체 
	 * 	  ㄴ DriverManager 		 : Connection 객체를 생성하기 위한 객체
	 * 	  ㄴ Connection			 : DB에 접속해서 DB의 연결정보를 담고있는 객체
	 * 	  ㄴ [Prepared]Statement : 연결된 DB에 sql문을 전달해서 실행하고 그 결과를 받아내는 객체 **
	 * 	  ㄴ ResultSet 			 : select문 실행 시 조회된 결과물들이 담겨있는 객체 
	 * 4. JDBC 절차
	 * 	  1) jdbc driver 등록 : 해당 DBMS(오라클)가 제공하는 Driver 클래스 등록
	 * 	  2) Connection 생성  : 연결하고자하는 DB정보를 입력해서 해당 DB와 연결하면서 생성 
	 *    3) Statement 생성   : Connection 객체를 이용해서 생성 
	 *    4) sql문 전달하면서 실행 : Statement 객체를 이용해서 sql문 실행 
	 *    5) 결과 받기
	 *    	  ㄴ select문 실행 : ResultSet객체 (조회된 데이터들이 담겨있음)	=> 6_1)
	 *    	  ㄴ    DML문 실행 : int (처리된 행수)							=> 6_2)
	 *    6_1) ResultSet에 담겨있는 데이터들을 하나씩 뽑아서 vo객체에 주섬주섬 옮겨 담기 [+ List에 차곡차곡 쌓기]
	 *    6_2) 트랜잭션 처리 (성공일경우 commit, 실패일경우 rollback)
	 *    7) 다 사용한 JDBC용 객체 자원 반납 (close) => 생성된 역순으로 
	 *    
	 */

	public static void main(String[] args) {
		
		// * 내 PC(localhost) DB상 JDBC 계정에 있는 TEST 테이블에 1번 데이터 조회해보기
		//   SELECT TNO, TNAME, TDATE FROM TEST WHERE TNO = 1;   (한 행 조회될 SQL문)
		
		// select문 => 실행결과를 ResultSet객체로 먼저 받기 
		//		    => ResultSet객체로부터 행을 스캔해서 각 컬럼값 뽑기
		//			=> Test Vo객체에 담기 
		
		// 최종적으로 조회결과를 담아낼 자바 객체 세팅
		Test t = null;
		
		// JDBC 과정중에 필요한 객체 미리 세팅 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 실행할 sql문 (유의사항: 절대 세미콜론이 있어서는 안됨 ***)
		String sql = "SELECT TNO, TNAME, TDATE FROM TEST WHERE TNO = 1";
		
		try {
			
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			// 예외발생될수 있는 case1. 해당 프로젝트에 ojdbc6.jar 파일을 연동 안했을 경우
			// 예외발생될수 있는 case2. 연동은 했으나 오타가 있을 경우 
			
			// 2) Connection 객체 생성 == DB에 연결 (url, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement 객체 생성 (sql문 실행을 위한 객체)
			stmt = conn.createStatement();
			
			// 4, 5) sql문(select문)을 전달하면서 실행 후 결과(ResultSet) 받기 
			rset = stmt.executeQuery(sql);
						// select문 실행일 경우 executeQuery(sql문)  : ResultSet
						//    DML문 실행일 경우 executeUpdate(sql문) : int 
			
			// 6) ResultSet에 담겨있는 데이터값(컬럼값)들을 뽑아서 vo객체의 각 필드에 옮겨담기
			if(rset.next()) { // next() : 행 커서를 움직여주는 역할, 
							  //		  뿐만아니라 해당 행이 존재할경우 true / 그게 아닐 경우 false반환 
							  //          => 조회결과가 있는지 판단 
				
				// 현재 rset의 커서가 가리키고 있는 한 행의 컬럼값들을 하나씩 뽑아서 Test 객체의 필드에 담기
				// rset으로 부터 "어떤 컬럼"의 값을 "어떤 자바타입"으로 뽑을 건지 제시
				// rset.getInt("컬럼명"|컬럼순번), rset.getString("컬럼명"|컬럼순번), rset.getDate("컬럼명"|컬럼순번)
				
				// * 기본생성자로 생성한 후 setter이용해서 담기
				/*
				t = new Test();
				t.setTestNo(rset.getInt("TNO"));
				t.setTestName(rset.getString("TNAME"));
				t.setTestDate(rset.getDate("TDATE"));
				*/
				
				// * 매개변수생성자로 생성과 동시에 값 대입
				t = new Test(rset.getInt("TNO"), rset.getString("TNAME"), rset.getDate(3));
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 7) 다쓴 JDBC용 객체 반납 (생성된 역순으로)
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 조회된 결과 출력
		if(t == null) { // 생성x => 조회결과가 없었음
			System.out.println("조회된 결과가 없습니다.");
		}else {
			System.out.println(t);
		}

	}

}
