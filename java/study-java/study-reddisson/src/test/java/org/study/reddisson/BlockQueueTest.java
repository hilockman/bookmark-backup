package org.study.reddisson;

import java.io.IOException;

import org.junit.Test;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;

public class BlockQueueTest {


	@Test 
	public void test() throws IOException, InterruptedException {

		final RedissonClient redisson = RedissonUtil.redisson();
		
		RBlockingQueue<SomeObject> queue = redisson.getBlockingQueue("anyQueue");
		// 如果初始容量（边界）设定成功则返回`真（true）`，
		// 如果初始容量（边界）已近存在则返回`假（false）`。
		new Thread(new Runnable() {
			
			public void run() {
				RBlockingQueue<SomeObject> queue1 = redisson.getBlockingQueue("anyQueue");	
				while (true) {
					SomeObject obj = queue1.poll();
								  
					
//					 obj = queue1.poll();
//								System.out.println(obj); 
					if (obj == null) {
						try {
							Thread.sleep(0);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						continue;
					}
					System.out.println(obj);
					
				}
			}
		}).start();
		
		queue.clear();
		
		queue.offer(new SomeObject(1));
		queue.offer(new SomeObject(2));
		// 此时容量已满，下面代码将会被阻塞，直到有空闲为止。
			queue.add(new SomeObject());


		//			SomeObject ob = queue.poll(10, TimeUnit.MINUTES);
//					SomeObject obj = queue.peek();

			
	}
}
