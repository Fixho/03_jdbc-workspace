package com.br.model.service;

import static com.br.common.JDBCTemplate.close;
import static com.br.common.JDBCTemplate.commit;
import static com.br.common.JDBCTemplate.getConnection;
import static com.br.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

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
	
	public Member selectMemberByUserId(String userId) {
		Connection conn = getConnection();
		Member m = new MemberDao().selectMemberByUserId(conn, userId);
		close(conn);
		return m;
	}
	
	public List<Member> selectMemberByUserName(String userName){
		Connection conn = getConnection();
		List<Member> list = new MemberDao().selectMemberByUserName(conn, userName);
		close(conn);
		return list;
	}
	
	public int updateMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDao().updateMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public int deleteMember(String userId) {
		Connection conn = getConnection();
		int result = new MemberDao().deleteMember(conn, userId);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public String loginMember(Map<String, String> map) {
		Connection conn = getConnection();
		String loginUserName = new MemberDao().loginMember(conn, map);
		close(conn);
		return loginUserName;
	}
	
	
	
	
	
	
	

}
