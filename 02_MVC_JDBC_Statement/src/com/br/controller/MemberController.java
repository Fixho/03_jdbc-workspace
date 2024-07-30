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
	
	//MemberView mv = new MemberView();
	
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
		
		int result = new MemberDao().insertMember(m);
		
		// 결과를 가지고 성공/실패 판단 후 사용자가 보게될 응답화면지정
		if(result > 0) { // 성공
			new MemberView().displaySuccess("성공적으로 회원 추가되었습니다.");
		}else { // 실패
			new MemberView().displayFail("회원 추가를 실패했습니다..");
		}
		
	}
	
	public void selectMemberByUserId(String userId) {
		Member m = new MemberDao().selectMemberByUserId(userId);
		
		if(m == null) { // 조회결과없음
			new MemberView().displayNoData(userId + "에 대한 검색 결과가 없습니다.");
		}else { // 조회결과있음
			new MemberView().displayMemberData(m);
		}
	}
	
	public void selectMemberByUserName(String userName) {
		List<Member> list = new MemberDao().selectMemberByUserName(userName);
	
		if(list.isEmpty()) { // 조회결과x
			new MemberView().displayNoData(userName + "에 대한 검색 결과가 없습니다.");
		}else { // 조회결과o
			new MemberView().displayMemberListData(list);
		}
	}
	
	public void updateMember(String userId, String userPwd
						   , String email, String phone, String hobby) {
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setHobby(hobby);
		
		int result = new MemberDao().updateMember(m);
		
		if(result > 0) {
			new MemberView().displaySuccess("성공적으로 정보 변경 되었습니다.");
		}else {
			new MemberView().displayFail("정보 변경에 실패했습니다.");
		}
		
		
	}
	
	public void deleteMember(String userId) {
		int result = new MemberDao().deleteMember(userId);
		
		if(result > 0) {
			new MemberView().displaySuccess("성공적으로 탈퇴 처리 되었습니다.");
		}else {
			new MemberView().displayFail("탈퇴 처리에 실패했습니다.");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
