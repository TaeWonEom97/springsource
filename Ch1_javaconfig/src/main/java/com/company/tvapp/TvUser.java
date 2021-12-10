package com.company.tvapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TvUser {

	public static void main(String[] args) {
		
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationConfig.xml");
		ApplicationContext ctx = new AnnotationConfigApplicationContext("");
		TV tv = (TV) ctx.getBean("samsung");
		
		
	//	tv.setSpeaker(new AppleSpeaker());
		tv.turnOn();
		tv.soundDown();
		tv.soundUp();
		tv.turnOff();
		
		TV tv1 = new LgTv();
		//싱글턴 패턴 사용
		
		System.out.println(tv == tv1 ? "객체 동등":"객체 다름");
	}

}
