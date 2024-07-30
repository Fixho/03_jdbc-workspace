package com.br.view;

import java.util.List;
import java.util.Scanner;

import com.br.controller.MemberController;
import com.br.model.vo.Member;

/*
 * View
 * 사용자가 보게될 화면(시각적인 요소)
 * 입력 받기(Scanner) 및 결과 출력(print)
 */
public class MemberView {
	
	Scanner sc = new Scanner(System.in);
	MemberController mc = new MemberController();
	
	/**
	 * 사용자의 메인 화면
	 */
	public void mainMenu() {
		
		while(true) {
			System.out.println("\n=== 회원 관리 프로그램 ===");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 신규 회원 추가");
			System.out.println("3. 회원 아이디 검색");
			System.out.println("4. 회원 이름 키워드 검색");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.print(">> 메뉴선택: ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1: mc.selectMemberList(); break;
			case 2: inputMember(); break;
			case 3:  break;
			case 4:  break;
			case 5:  break;
			case 6:  break;
			case 0: System.out.println("이용해주셔서 감사합니다. 프로그램을 종료합니다."); return;
			default: System.out.println("메뉴를 잘못 선택하셨습니다. 다시 선택해주세요.");
			}
			
		}
		
	}
	
	public void inputMember() {
		
		System.out.println("\n=== 신규 회원 추가 ===");
		
		// 아이디 ~ 취미
		System.out.print("아이디(필수): ");
		String userId = sc.nextLine();
		
		System.out.print("비밀번호(필수): ");
		String userPwd = sc.nextLine();
		
		System.out.print("이름(필수): ");
		String userName = sc.nextLine();
		
		System.out.print("성별(M/F): ");
		String gender = sc.nextLine().toUpperCase();
		
		System.out.print("나이(정수): ");
		String age = sc.nextLine();    // "20"
		
		System.out.print("이메일: ");
		String email = sc.nextLine();
		
		System.out.print("전화번호(-빼고 입력): ");
		String phone = sc.nextLine();
		
		System.out.print("취미(,로 연이어서 작성): ");
		String hobby = sc.nextLine();
		
		// 회원 추가 요청 == Controller 메소드 호출
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, hobby);
		
	}
	
	
	
	
	
	
	
	// ------------------ 응답화면 -----------------
	/**
	 * 서비스 요청처리 후 성공했을 경우 사용자가 보게될 응답화면
	 * @param message  출력시킬 성공메세지
	 */
	public void displaySuccess(String message) {
		System.out.println("\n서비스 요청 성공: " + message);
	}
	
	/**
	 * 서비스 요청처리 후 실패했을 경우 사용자가 보게될 응답화면
	 * @param message  출력시킬 실패메세지
	 */
	public void displayFail(String message) {
		System.out.println("\n서비스 요청 실패: " + message);
	}
	
	/**
	 * 조회서비스 요청처리 후 조회결과가 없을 경우 사용자가 보게될 응답화면
	 * @param message  출력시킬 메세지
	 */
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	
	/**
	 * 조회서비스 요청처리 후 조회결과가 여러행일 경우 사용자가 보게될 응답화면
	 * @param list  출력시킬 회원들 데이터
	 */
	public void displayMemberListData(List<Member> list) {
		System.out.println("\n조회된 데이터는 다음과 같습니다.");
		
		//for(int i=0; i<list.size(); i++) {
		for(Member m : list) {
			System.out.println(m);
		}
		
	}

}




