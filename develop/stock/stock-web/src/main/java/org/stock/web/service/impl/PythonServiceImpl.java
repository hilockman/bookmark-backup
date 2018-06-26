package org.stock.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.stock.web.service.PythonService;
import org.stock.web.utils.Utils;

@Service
public class PythonServiceImpl implements PythonService {

	private String url(String path) {
		return "http://localhost:5000/"+path;
	}
	
	private String getStringResult(String path) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url(path), String.class);
	}
	
	private <T> T getObjectResult(String path, Class<T> clazz) {
		String rt = getStringResult(path);
		if (rt == null)
			return null;
		
		return Utils.toObject(rt, clazz);
	}
	
	@Override
	public String getStockBasics() {
		return getStringResult("stock/basics");
	}

}
