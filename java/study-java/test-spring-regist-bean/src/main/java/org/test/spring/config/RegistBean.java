package org.test.spring.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarInputStream;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.test.spring.component.A;

@Component
@Configuration
@EnableAutoConfiguration
@AutoConfigureOrder(AutoConfigureOrder.DEFAULT_ORDER+4)
public class RegistBean implements BeanDefinitionRegistryPostProcessor  {

	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//		DataBusProperties properties = beanFactory.getBean(DataBusProperties.class);
//		System.out.println("Properties : "+properties.getName());
		A a = new A();
//		beanFactory.autowireBean(a);
//		beanFactory.initializeBean(a, "a");
		beanFactory.registerSingleton(a.getClass().getCanonicalName(), a);
		



	    
		System.out.println("RegistBean : a is registted as a bean, order :"+(AutoConfigureOrder.DEFAULT_ORDER+4));
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		// TODO Auto-generated method stub
		
	}
}
