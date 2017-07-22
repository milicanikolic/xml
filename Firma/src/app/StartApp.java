package app;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

@Startup
@Singleton
public class StartApp {
	
	public StartApp() {
		
	}
	
	@PostConstruct
	public void init() {
	
		System.out.println("POKRENUO");
		
		Endpoint.publish("http://localhost:9999/ws/hello", new MyImpl());
		
		
		URL url=null;
		try {
			url = new URL("http://localhost:9999/ws/hello?wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //1st argument service URI, refer to wsdl document above
	//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://app/", "MyImplService");

        Service service = Service.create(url, qname);

        MyInterface hello = service.getPort(MyInterface.class);

        System.out.println(hello.getHelloWorldAsString("mkyong"));
	}
}
