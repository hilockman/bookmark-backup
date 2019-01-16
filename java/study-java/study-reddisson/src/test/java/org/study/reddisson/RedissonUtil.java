package org.study.reddisson;

import java.io.File;
import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonUtil {

	public static RedissonClient redisson(File file) {
		Config config;
		try {
			config = Config.fromJSON(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		
		
		return  Redisson.create(config);
	}
	
	public static RedissonClient redisson() {
		return redisson(new File("SingleServerConfig.cfg"));
	}
}
