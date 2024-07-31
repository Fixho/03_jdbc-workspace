package com.br.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.br.common.JDBCTemplate.*;

import com.br.common.JDBCTemplate;
import com.br.model.dao.MemberDao;
import com.br.model.vo.Member;

public class MemberService {
	
	public List<Member> selectMemberList() {
		// 1) jdbc driver 등록
		// 2) Connection 객체 생성
		Connection conn = /*JDBCTemplate.*/getConnection();
		List<Member> list = new MemberDao().selectMemberList(conn);
		/*JDBCTemplate.*/close(conn);
		return list;
	}
	
	public int insertMember(Member m) {
		
		Connection conn = getConnection(); // 1), 2)
		int result = new MemberDao().insertMember(conn, m);
		
		// 6) 트랜잭션 처리
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}

}
