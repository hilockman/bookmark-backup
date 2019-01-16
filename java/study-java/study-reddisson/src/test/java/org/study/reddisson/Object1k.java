package org.study.reddisson;

public class Object1k {

	public static String str;
	
	{
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < 1024; i++) {
			buffer.append("a");
			
		}
		
		str = buffer.toString();
	}
	
	private String content;
	
	public Object1k(int i) {
		content = str + i;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
