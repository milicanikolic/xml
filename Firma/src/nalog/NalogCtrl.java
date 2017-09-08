package nalog;

import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.marklogic.client.eval.ServerEvaluationCall;

import app.PozivServisa;
import app.StartApp;
import generisani.Nalog;

@LocalBean
@Stateless
@Path("/nalog")
public class NalogCtrl {

	URL wsdlMojaBanka;
	URL wsldTudjaBanka;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void kreirajNalog(Nalog nalog) {
		System.out.println("Usao u nalog " + nalog.getDuznik() + nalog.getPrimalac());

		XMLGregorianCalendar now = null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		try {
			now = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			now.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			// System.out.println("datum " + now);
		} catch (Exception e) {
			e.printStackTrace();
		}

		nalog.setDatumNaloga(now);

		XMLGregorianCalendar datumValute = nalog.getDatumValute();
		datumValute.setTimezone(DatatypeConstants.FIELD_UNDEFINED);

		nalog.setDatumValute(datumValute);

		PozivServisa.posaljiNalog(nalog);
	

	}
	
	@GET
	@Path("/{pib}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String vratiUsername(@PathParam("pib") String pib) {
		String upitPre = "for $x in doc('/content/firma.xml')/firme/firma[PIB='"
				+ pib + "']/username return $x";
		String odgovor = posaljiUpit(upitPre);
		
		String[] ceo = odgovor.split(">");
		String[] drugi = ceo[1].split("<");
		System.out.println("USERNAMEEE " + drugi[0]);
		String kon=drugi[0];
		return kon;

	}
	
	
	public String posaljiUpit(String upit) {
		ServerEvaluationCall poziv = StartApp.getClient().newServerEval();
		String odgovor = poziv.xquery(upit).evalAs(String.class);
		return odgovor;
	}

}
