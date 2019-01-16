package org.study.reddisson;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

import org.junit.Test;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;

public class ManySaveToQueueTest {
	@Test 
	public void test() throws IOException, InterruptedException {

		final RedissonClient redisson = RedissonUtil.redisson();
		
		RBlockingQueue<Object1k> queue = redisson.getBlockingQueue("anyQueue");
		// 如果初始容量（边界）设定成功则返回`真（true）`，
		// 如果初始容量（边界）已近存在则返回`假（false）`。
		new Thread(new Runnable() {
			
			public void run() {
				RBlockingQueue<Object1k> queue1 = redisson.getBlockingQueue("anyQueue");	
				Date time = new Date();
				while (true) {
					int size = queue1.size();
					
					System.out.println("Queue size : "+size+", elapsed : "+(new Date().getTime() - time.getTime())/1000.0+" ms.");

						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


					
				}
			}
		}).start();
		
		queue.clear();
		
		int i = 0;
		for (; i < 20000; i++) {
			queue.offer(new Object1k(i));

		}
		
		System.out.println("finish save :"+i);

		Thread.sleep(1000);
	}
}
