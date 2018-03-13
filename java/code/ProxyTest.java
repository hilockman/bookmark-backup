import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class ProxyTest {
		
	public static void main(String[] args) {
		System.out.println("hello");
		
		Map proxyInstance = (Map) Proxy.newProxyInstance(
				ProxyTest.class.getClassLoader(), 
		  new Class[] { Map.class }, 
		  new DynamicInvocationHandler());
		  
		 proxyInstance.put("hello", "world"); 
	}

}

class DynamicInvocationHandler implements InvocationHandler {
 

 
    public Object invoke(Object proxy, Method method, Object[] args) 
      throws Throwable {
        System.out.println(String.format("Invoked method: %s", method.getName()));
 
        return 42;
    }
}