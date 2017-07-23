package firma;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;

import app.StartApp;

@LocalBean
@Stateless
@Path("/firma")
public class FirmaCtrl {
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{username}/{password}")
	public Firma login(@PathParam("username") String username, @PathParam("password") String password) {
		
		
		
		JAXBHandle<?> jaxHandle=new JAXBHandle<>(StartApp.getContext());
     	
		StartApp.otvoriKonekciju();
		
     	XMLDocumentManager docMgr=StartApp.getClient().newXMLDocumentManager();
     	
     	docMgr.read("/content/firma.xml", jaxHandle);
     	Firme firmeBaza=(Firme) jaxHandle.get();
     	
     	StartApp.zatvoriKonekciju();
     	
     	for(Firma f:firmeBaza.getFirme()) {
     		if(f.getUsername().equals(username)) {
     			System.out.println("usao u prvi if");
     			if(f.getPassword().equals(password)) {
     				System.out.println("sve prosao. usename: "+f.getUsername()+" ,sifra: "+f.getPassword());
     				return f;
     			}
     		}
     	}
		return null;
		
	}

}
