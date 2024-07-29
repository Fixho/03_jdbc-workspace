package com.br.model.vo;

import java.sql.Date;

public class Test {
	
	private int testNo;			// TEST 테이블의 TNO 컬럼(NUMBER)값 담기위한 필드
	private String testName;	// TEST 테이블의 TNAME 컬럼(VARCHAR2)값 담기위한 필드 
	private Date testDate;		// TEST 테이블의 TDATE 컬럼(DATE)값 담기위한 필드
	
	public Test() {}

	public Test(int testNo, String testName, Date testDate) {
		super();
		this.testNo = testNo;
		this.testName = testName;
		this.testDate = testDate;
	}

	public int getTestNo() {
		return testNo;
	}

	public void setTestNo(int testNo) {
		this.testNo = testNo;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	@Override
	public String toString() {
		return "Test [testNo=" + testNo + ", testName=" + testName + ", testDate=" + testDate + "]";
	}
	
}
