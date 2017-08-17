package nalog;

import java.net.URL;
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
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rs.ac.uns.ftn.banka.NalogZaUplatu;


@LocalBean
@Stateless
@Path("/nalog")
public class NalogCtrl {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void kreirajNalog(rs.ac.uns.ftn.nalog.Nalog nalog) {
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
		
		
		
		try{
			
			URL wsdlLocation=new URL(" http://NINA:8085/Banka/NalogZaUplatuService?wsdl");
			QName serviceName= new QName("http://ftn.uns.ac.rs/banka", "NalogZaUplatuService");
			QName portName=new QName("http://ftn.uns.ac.rs/banka","NalogZaUplatu");
			
			Service service=Service.create(wsdlLocation,serviceName);
			NalogZaUplatu inter=service.getPort(portName,NalogZaUplatu.class);
			
		
			System.out.println("Racun poverioca: "+nalog.getRacunPoverioca());
			
			String hh= inter.obradiNalog(nalog);
		//	String dd=inter.metoda();
			System.out.println("vratio service: "+hh);
					
			

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	

}
