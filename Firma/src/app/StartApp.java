package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.InputStreamHandle;

import model.Banka;
import model.Firma;

@Startup
@Singleton
public class StartApp {

	private JAXBContext context;

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
        Marshaller marshaller=null;
       try {
		this.context=JAXBContext.newInstance(Firma.class);
		 marshaller=this.context.createMarshaller();
		 marshaller.marshal(new Firma("Firma1", "Novosadskog Sajma 10", "12345", new Banka("Agricole", "123", "500")), new File("C:/Users/NINA-HOME/Desktop/milica/github/Firma/WebContent/firma.xml"));
	} catch (JAXBException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
       
       
       DatabaseClient client =
 			  DatabaseClientFactory.newClient("localhost", 8003,
 			                                  "admin", "admin",
 			                                  Authentication.DIGEST);

 	XMLDocumentManager docMgr = client.newXMLDocumentManager();
 	
 	InputStream is=null;
 	try{
 		is=new FileInputStream("C:/Users/NINA-HOME/Desktop/milica/github/Firma/WebContent/firma.xml");
 				is.close();
 	}
 	catch(Exception e) {
 		e.printStackTrace();
 	}
 	
 	InputStreamHandle writeHandle=new InputStreamHandle(is);
 	docMgr.write("/content/firma.xml", writeHandle);
 	
 	
 	/*StringHandle handleRead = new StringHandle();
 	docMgr.read("/content/xml/zavrsni_rad.xml", handleRead);
 	
 	System.out.println("ispis " + handleRead.get());
 	
 	*/
 	client.release();
       
       
       
 
	}
}
