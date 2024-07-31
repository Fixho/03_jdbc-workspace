package com.br.controller;

import java.util.List;

import com.br.model.service.MemberService;
import com.br.model.vo.Member;
import com.br.view.MemberView;

public class MemberController {
	
	public void selectMemberList() {
		List<Member> list = new MemberService().selectMemberList();
		
		if(list.isEmpty()) { // 조회결과 없을 경우
			new MemberView().displayNoData("전체 조회 결과가 없습니다.");
		}else { // 조회결과 있을 경우
			new MemberView().displayMemberListData(list);
		}
	}

	public void insertMember(String userId, String userPwd, String userName,
							 String gender, String age, String email, String phone, String hobby) {
		
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, hobby);
		
		int result = new MemberService().insertMember(m);
		
		if(result > 0) { // 성공
			new MemberView().displaySuccess("성공적으로 추가 되었습니다.");
		}else { // 실패
			new MemberView().displayFail("회원 추가에 실패했습니다.");
		}
		
	}
	
	public void selectMemberByUserId(String userId) {
		Member m = new MemberService().selectMemberByUserId(userId);
		
		if(m == null) { // 조회결과 x
			new MemberView().displayNoData(userId + "에 대한 검색 결과가 없습니다.");
		}else { // 조회결과 o
			new MemberView().displayMemberData(m);
		}
	}
	
	public void selectMemberByUserName(String userName) {
		List<Member> list = new MemberService().selectMemberByUserName(userName);
		
		if(list.isEmpty()) {
			new MemberView().displayNoData(userName + "에 대한 검색 결과가 없습니다.");
		}else {
			new MemberView().displayMemberListData(list);
		}
	}
	
	public void updateMember(String userId, String userPwd, String email, String phone, String hobby) {
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setHobby(hobby);
		
		int result = new MemberService().updateMember(m);
		
		if(result > 0) {
			new MemberView().displaySuccess("성공적으로 회원 정보 변경 되었습니다.");
		}else {
			new MemberView().displayFail("회원 정보 변경에 실패했습니다.");
		}
		
	}
	
	public void deleteMember(String userId) {
		int result = new MemberService().deleteMember(userId);
		
		if(result > 0) {
			new MemberView().displaySuccess("성공적으로 회원 탈퇴 되었습니다.");
		}else {
			new MemberView().displayFail("회원 탈퇴에 실패했습니다.");
		}
	}
	
	
	
	
	
	
	
	
}
