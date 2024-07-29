package com.br.controller;

import java.util.List;

import com.br.model.dao.MemberDao;
import com.br.model.vo.Member;

/*
 * Controller
 * 사용자가 보낸 요청처리 
 * 요청에 따른 sql문 실행을 위해 DAO 메소드 호출
 * DAO로 부터 반환받은 결과에 따라서 
 * 성공/실패, 조회결과있는지/없는지 판별해서 응답화면 결정
 */
public class MemberController {
	
	public void selectMemberList() {
		List<Member> list = new MemberDao().selectMemberList();
	}

	
	
	
	
	
	
	
}
