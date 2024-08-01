package com.br.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	// Connection 생성해서 반환
	public static Connection getConnection() {
		
		/*
		 * * 기존의방식 : driver구문, 접속할 db 정보가 자바소스코드내에 명시적으로 작성 => 정적코딩방식
		 * ㄴ 문제점 : dbms가 변경되거나 , 접속할 db의 정보가 변경될 경우 => 자바소스코드를 수정해야됨
		 * 																  => 프로그램 재 구동 (비정상적으로 종료됐다가 다시 구동)
		 * 			   프로그램 관리자(일반인)가 자바코드를 볼줄 몰라서 수정하기가 어려움
		 * 
		 * ㄴ 해결방식 : db관련 정보들을 외부 파일로 따로 저장시키고 
		 * 				 파일 정보를 읽어들여서 프로그램에 반영 => 동적코딩방식 
		 * 				 => 정보 변경할 경우 파일만 수정만하면됨 => 프로그램 재구동시킬 필요도 없음
		 */
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// ResultSet 객체를 반납(close) 처리
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Statement 관련 객체를 반납 처리 
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Connection 객체를 반납 처리
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// commit 처리 
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// rollback 처리
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
