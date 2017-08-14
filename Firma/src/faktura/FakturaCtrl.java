package faktura;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.marklogic.client.eval.EvalResult;
import com.marklogic.client.eval.EvalResultIterator;
import com.marklogic.client.eval.ServerEvaluationCall;

import app.StartApp;

@LocalBean
@Stateless
@Path("/faktura")
public class FakturaCtrl {
	

	private static final String prefix = "data/xquery/";
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Faktura> uzmiFakture() {
		
		
		

		//DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		//factory.setNamespaceAware(true);
		//DocumentBuilder builder=factory.
		
		String upitPre = "declare namespace fak='http://ftn.uns.ac.rs/faktura';" + " for $x in doc('/content/faktura.xml')/fakture/fak:faktura return $x";
   
		
		
		/*String upit = "for $x in doc('/content/faktura.xml')/fakture" +
				" return $x";
*/
		ServerEvaluationCall poziv = StartApp.getClient().newServerEval();
		List<Faktura> listaFaktura = new ArrayList<Faktura>();
		try {
			JAXBContext jc = JAXBContext.newInstance(Fakture.class);
			Unmarshaller unmarsaller = jc.createUnmarshaller();
			EvalResultIterator it = poziv.xquery(upitPre).eval();
			while (it.hasNext()) {
				EvalResult eval = it.next();
				Faktura f = (Faktura) unmarsaller.unmarshal(new StringReader(eval.getString()));
				listaFaktura.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("ukupno faktura " + listaFaktura.size());
		
		for(Faktura fak:listaFaktura) {
			System.out.println("id poruke fakture " + fak.getZaglavljeFakture().getIdPoruke());
		}
		
		return listaFaktura;
		
		 
	/*	JAXBHandle<?> handle;
		try {
			handle = new JAXBHandle<>(JAXBContext.newInstance(Fakture.class));
			XMLDocumentManager mgr=StartApp.getClient().newXMLDocumentManager();
			mgr.read("/content/faktura.xml", handle);
			Fakture listaFaktura = (Fakture) handle.get();

			return listaFaktura.getFakture();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
*/
		
	}

	/*public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	} */
}
