package faktura;

import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.swing.JFileChooser;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.marklogic.client.eval.EvalResult;
import com.marklogic.client.eval.EvalResultIterator;
import com.marklogic.client.eval.ServerEvaluationCall;

import app.StartApp;
import wrapper.Fakture;

@LocalBean
@Stateless
@Path("/faktura")
public class FakturaCtrl {

	@GET
	@Path("/{pib}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Faktura> uzmiFakture(@PathParam("pib") String pib) {

		String upitPre = "declare namespace fak='http://ftn.uns.ac.rs/faktura';"
				+ " for $x in doc('/content/faktura.xml')/fakture/fak:faktura[fak:zaglavljeFakture/fak:pibKupac='"
				+ pib + "'] return $x";

		ServerEvaluationCall poziv = StartApp.getClient().newServerEval();
		List<Faktura> listaFaktura = new ArrayList<Faktura>();
		try {
			JAXBContext jc = JAXBContext.newInstance(Fakture.class);
			Unmarshaller unmarsaller = jc.createUnmarshaller();
			EvalResultIterator it = poziv.xquery(upitPre).eval();
			while (it.hasNext()) {
				EvalResult eval = it.next();
				Faktura f = (Faktura) unmarsaller.unmarshal(new StringReader(
						eval.getString()));
				listaFaktura.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaFaktura;

	}
	
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void pdf(String brFakture) {
		
		System.out.println("USAO U PDF" + brFakture);
		
		String upit = "declare namespace fak='http://ftn.uns.ac.rs/faktura';"+" for $x in doc('/content/faktura.xml')/fakture/fak:faktura[fak:zaglavljeFakture/fak:idPoruke='" + brFakture + "']"
				 + " return $x";
		
		String odgovor = posaljiUpit(upit);
		
		System.out.println("odgovor je " +odgovor);
		
		String kon="<?xml version='1.0' encoding='UTF-8' standalone='yes'?>"+odgovor;
		String kon1 = kon.replace(" xmlns=\"http://ftn.uns.ac.rs/faktura\"","");
		
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter( new File(System.getProperty("jboss.server.data.dir") , "faktura.xml")));
		    writer.write( kon1);

		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
		
		
		
	
		TransformerFactory tff = TransformerFactory.newInstance();
		try {
			Transformer tf = tff.newTransformer(new StreamSource(new File(System.getProperty("jboss.server.data.dir") , "faktura.xslt")));
			StreamSource ss = new StreamSource(new File(System.getProperty("jboss.server.data.dir"), "faktura.xml"));
			StreamResult sr = new StreamResult(new File(System.getProperty("jboss.server.data.dir"), "faktura.html"));
			tf.transform(ss, sr);
			
			
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		ITextRenderer itr = new ITextRenderer();
		try {
			itr.setDocument(new File(System.getProperty("jboss.server.data.dir"),"faktura.html").toURI().toURL().toString());
			itr.layout();
			OutputStream os = new FileOutputStream(new File(System.getProperty("jboss.server.data.dir"),"faktura.pdf"));
			itr.createPDF(os);
			itr.finishPDF();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		

		
		
	}
	
	
	public String posaljiUpit(String upit) {
		ServerEvaluationCall poziv = StartApp.getClient().newServerEval();
		String odgovor = poziv.xquery(upit).evalAs(String.class);
		return odgovor;
	}
	
	
	
	
	
	
	
}
