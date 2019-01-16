package org.test.spring.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UseA {

	@Autowired
	private A a;
	
	public UseA() {
		System.out.println("UseA is created ~~~~~~~~~.");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("~~~~~~~~~~~~~~~UseA init start ~~~~~~~~~.");
		a.print();
		System.out.println("~~~~~~~~~~~~~~~UseA init end ~~~~~~~~~.");
	}
}
