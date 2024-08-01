package com.br.run;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
		
		// Properties (Map 계열의 컬렉션)
		//  key:value 세트로 저장
		//  key도 String, value도 String으로 보관 
		//  setProperty(key, value)  : 데이터 담기
		//  getProperty(key)		 : value 꺼내기
		
		/*
		Properties prop = new Properties();
		prop.setProperty("academy", "goodee academy");
		prop.setProperty("classRoom", "A");
		
		System.out.println(prop);
		
		try {
			prop.store(new FileOutputStream("resources/test.properties"), "test.properties");
			prop.storeToXML(new FileOutputStream("resources/test.xml"), "test.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		Properties prop = new Properties();
		
		try {
			//prop.load(new FileInputStream("resources/test.properties"));
			prop.loadFromXML(new FileInputStream("resources/test.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(prop);
		System.out.println(prop.getProperty("classroom")); // null
		System.out.println(prop.getProperty("classRoom"));
		
		
		
		
		
		
		
		
		
		
		

	}

}
