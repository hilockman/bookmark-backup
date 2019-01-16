package org.test.spring.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.test.spring.component.A;

@Controller
//@Order(8)
public class NullController {

	@Autowired
	private A a;
	
	public NullController() {
		System.out.println("NullConroller is created~~~~");
	}
}
 