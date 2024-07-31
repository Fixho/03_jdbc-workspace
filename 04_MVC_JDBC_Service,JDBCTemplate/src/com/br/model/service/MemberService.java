package com.br.model.service;

import java.sql.Connection;
import java.util.List;

import com.br.common.JDBCTemplate;
import com.br.model.dao.MemberDao;
import com.br.model.vo.Member;

public class MemberService {
	
	public List<Member> selectMemberList() {
		// 1) jdbc driver 등록
		// 2) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		List<Member> list = new MemberDao().selectMemberList(conn);
		JDBCTemplate.close(conn);
		return list;
	}

}
