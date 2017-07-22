package app;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.StringHandle;

@Startup
@Singleton
public class StartApp {

	public StartApp() {
		
	}
	
	@PostConstruct
	public void init() {
	System.out.println("POST CONSTRUCTTT!!!!!!!!!!!");
	
	
	DatabaseClient client =
			  DatabaseClientFactory.newClient("localhost", 8003,
			                                  "admin", "admin",
			                                  Authentication.DIGEST);

	XMLDocumentManager docMgr = client.newXMLDocumentManager();
	
	
	
	StringHandle handleRead = new StringHandle();
	docMgr.read("/content/xml/instance1.xml", handleRead);
	
	System.out.println("ispis " + handleRead.get());
	
	
	client.release();

	
	
	
	
	
	
	
	}

}
