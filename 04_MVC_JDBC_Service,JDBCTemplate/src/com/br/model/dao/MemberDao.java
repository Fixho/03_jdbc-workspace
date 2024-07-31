package com.br.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.br.common.JDBCTemplate;
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
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			//JDBCTemplate.close(conn); => Service에서 반납
		}
		
		return list;
		
	}

}
