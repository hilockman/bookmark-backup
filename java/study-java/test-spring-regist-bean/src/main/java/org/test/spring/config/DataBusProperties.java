package org.test.spring.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "buffer")
//@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableAutoConfiguration
//@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class DataBusProperties {
	
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		
		System.out.println("DataBusProperties.setName : "+name);
	}
	
	public DataBusProperties() {
		
		System.out.println("DataBusProperties created !!!!!!!!!!!!!!!!!");
	}
	
	@Autowired
	private ConfigurableListableBeanFactory beanFactory;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	
	  protected List<String> listResources(JarInputStream jar, String path) throws IOException {
		    // Include the leading and trailing slash when matching names
		    if (!path.startsWith("/")) {
		      path = "/" + path;
		    }
		    if (!path.endsWith("/")) {
		      path = path + "/";
		    }

		    // Iterate over the entries and collect those that begin with the requested path
		    List<String> resources = new ArrayList<String>();
		    for (JarEntry entry; (entry = jar.getNextJarEntry()) != null;) {
		      if (!entry.isDirectory()) {
		        // Add leading slash if it's missing
		        String name = entry.getName();
		        if (!name.startsWith("/")) {
		          name = "/" + name;
		        }

		        // Check file name
		        if (name.startsWith(path)) {
//		          if (log.isDebugEnabled()) {
//		            log.debug("Found resource: " + name);
//		          }
		          System.out.println("Found resource: " + name);
		          // Trim leading slash
		          resources.add(name.substring(1));
		        }
		      }
		    }
		    return resources;
		  }
	  
	@PostConstruct
	public void init() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~DataBusProperties.init~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		//ResourceLoader resourceLoader = beanFactory.getBean(ResourceLoader.class);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			String path = "com/znd/bus/common/buffer";
			Enumeration<URL> e = classLoader.getResources(path);
	        ArrayList<URL> l = new ArrayList<>();
	        while (e.hasMoreElements())
	            l.add(e.nextElement());
	        
	        
	        for (URL url : l) {
	        	System.out.println("Url : "+url);
	        	
	        	StringBuilder jarUrl = new StringBuilder(url.toExternalForm());
	    	    int index = jarUrl.lastIndexOf(".jar");
	    	    if (index >= 0) {
	    	      jarUrl.setLength(index + 4);
	    	    }
	    	    
	        	System.out.println("Jar url : "+ jarUrl);
	        	Resource resource = resourceLoader.getResource(jarUrl.toString());
	        	System.out.println("resourceLoader:"+resourceLoader);
	        	System.out.println("resource:"+resource);
//	        	String type = URLConnection.guessContentTypeFromName(resource.getFilename());
//	        	System.out.println("Resource type : "+type+", filename: "+resource.getFilename());
	        	//InputStream is = resource.getInputStream();
		        try (JarInputStream jis = new JarInputStream(resource.getInputStream())) {
		        	int rt = jis.available();
		        	System.out.println("Available : "+rt);
		        	listResources(jis, path);
	        	}
	        }
		}  catch (Exception e) {
	        	e.printStackTrace();
	        }
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~DataBusProperties.init end~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}
