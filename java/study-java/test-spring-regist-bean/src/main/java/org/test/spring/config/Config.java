package org.test.spring.config;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.test.spring.component.B;

@Configuration
@EnableAutoConfiguration
//@Order(2)
@AutoConfigureOrder(2)
public class Config  {



	@Bean
	@Order(2)
	public B b(DataBusProperties p) {
		System.out.println("DataBusProperties:"+p.getName());
		try {
		   return new B();
		} finally {
			System.out.println("Config : B is registed as a bean, order :"+(2));
		}
	}

	
}
