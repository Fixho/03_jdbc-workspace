package com.br.controller;

import java.util.List;

import com.br.model.dao.MemberDao;
import com.br.model.vo.Member;
import com.br.view.MemberView;

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
		
		if(list.isEmpty()) { // 비어있을 경우 => 조회결과 x
			new MemberView().displayNoData("전체 조회 결과가 없습니다.");
		}else {	// 그게 아닐 경우 => 조회결과 o
			new MemberView().displayMemberListData(list);
		}
		
	}

	public void insertMember(String userId, String userPwd, String userName
						   , String gender, String age, String email
						   , String phone, String hobby) {
		
		// 데이터 가공처리
		// view로부터 전달받은 데이터들을 vo객체(Member)에 주섬주섬담아서
		// dao로 통채로 전달하기 
		
		// 매개변수 생성자 이용
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, hobby);
		
		new MemberDao().insertMember(m);
		
	}
	
	
	
	
	
	
}
