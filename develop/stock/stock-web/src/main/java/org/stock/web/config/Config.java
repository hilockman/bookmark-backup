package org.stock.web.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.stock.web.utils.AppUtil;

@Configuration
@EnableAutoConfiguration
public class Config {



	
	@PostConstruct
	public void init() {
		Process p = AppUtil.execute("python", "D:\\\\GitHub\\\\study-notes\\\\develop\\\\stock\\\\stock-web\\\\src\\\\main\\\\resources\\\\static\\\\python\\\\app.py");

		
	}
	
	@PreDestroy
	public void destory() {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForLocation("http://localhost:5000/shutdown", null);
		System.out.println("Python server shutting down...");
	}
}
