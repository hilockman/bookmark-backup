package org.test.spring.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.test.spring.component.C;
import org.test.spring.component.ResgistClassD;

@Configuration
@EnableAutoConfiguration
//@Order(1)
@AutoConfigureOrder(1)
public class TestConfig {


	@Bean
	@Order(1)
	public C C(ConfigurableListableBeanFactory beanFactory) {
		try {
		   


		   return new C();
		} finally {
			System.out.println("TestConfig : C is registed as a bean, order :"+(1));
			
			beanFactory.registerSingleton(ResgistClassD.class.getCanonicalName(), new ResgistClassD());
			
			System.out.println("TestConfig : ResgistClassD is registted as a bean, order :"+(1));
		}
	}
}
