package com.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.br.common.JDBCTemplate.*;
import com.br.model.vo.Member;

public class MemberDao {
	
	public List<Member> selectMemberList(Connection conn) {
		// select문 (여러행) => ResultSet객체 => 각행은 Member객체 => List<Member> 에 쌓기
		List<Member> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER";
		
		try {
			// 3) PreparedStatement 생성 
			pstmt = conn.prepareStatement(sql); // 애초에 완성된 sql문
			// 4, 5) sql문 실행 후 결과받기
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				// 한 행의 모든 컬럼값들 => Member객체의 각 필드에 대입 => 리스트에 추가
				list.add(new Member( rset.getInt("USER_NO")
								   , rset.getString("user_id")
								   , rset.getString("user_pwd")
								   , rset.getString("user_name")
								   , rset.getString("gender")
								   , rset.getInt("age")
								   , rset.getString("email")
								   , rset.getString("phone")
								   , rset.getString("hobby")
								   , rset.getDate("regist_date") )); 
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/*JDBCTemplate.*/close(rset);
			/*JDBCTemplate.*/close(pstmt);
			//JDBCTemplate.close(conn); => Service에서 반납
		}
		
		return list;
		
	}
	
	public int insertMember(Connection conn, Member m) {
		// insert문 => 처리된행수(int) => 트랜잭션처리(Service에서 진행)
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO MEMBER VALUES(SEQ_UNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(sql); // 3)  , 미완성된sql문
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getHobby());
			
			result = pstmt.executeUpdate(); // 4), 5)
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	
	
	

}
