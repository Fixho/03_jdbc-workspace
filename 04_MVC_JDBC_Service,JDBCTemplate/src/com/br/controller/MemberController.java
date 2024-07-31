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

}
