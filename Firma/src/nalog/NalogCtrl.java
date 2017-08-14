package nalog;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@LocalBean
@Stateless
@Path("/nalog")
public class NalogCtrl {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void kreirajNalog(Nalog nalog) {
		System.out.println("Usao u nalog "+nalog.getDuznik());
		
		XMLGregorianCalendar now = null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		try {
			now = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			now.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			//System.out.println("datum " + now);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		nalog.setDatumNaloga(now);
		
		XMLGregorianCalendar datumValute = nalog.getDatumValute();
		datumValute.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		
		nalog.setDatumValute(datumValute);
		
	}
	

}
