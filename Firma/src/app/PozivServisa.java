package app;



import java.io.StringReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;

import com.marklogic.client.eval.ServerEvaluationCall;

import banka.Banka;
import generisani.BankaServis;
import generisani.Nalog;
import generisani.Presek;
import generisani.ZahtevZaIzvod;



public class PozivServisa {

	static URL wsdlMojaBanka;
	static URL WSDLTudjaBanka; 
	//static Banka bankaDuznika;
	static Banka bankaPoverioca;
	static QName serviceName=new QName("http://ftn.uns.ac.rs/banka","BankaServis");
	static QName portName=new QName("http://ftn.uns.ac.rs/banka","Banka");
	
	public static void posaljiNalog(Nalog nalog){
		
		String oznakaBankeDuznika=nalog.getRacunDuznik().substring(0, 3);
		String oznakaBankePoveriova=nalog.getRacunPoverioca().substring(0, 3);
		
		String upitDuznik = "for $x in doc('/content/banka.xml')/banke/banka where $x/oznakaBanke='"
				+oznakaBankeDuznika + "' return $x";
		String upitPoverioca = "for $x in doc('/content/banka.xml')/banke/banka where $x/oznakaBanke='"
				+oznakaBankePoveriova + "' return $x";
		
		String odgovorDuznik = posaljiUpit(upitDuznik);
		String odgovorPoverioca = posaljiUpit(upitPoverioca);
		
		Banka bankaDuznika=null;
		try {
			bankaDuznika = unmarshaluj(Banka.class, new StreamSource(
					new StringReader(odgovorDuznik)));
			System.out.println("odgovor: "+odgovorDuznik);
			System.out.println("port: "+bankaDuznika.getPort());
			System.out.println("naiv: "+bankaDuznika.getNaziv());
			System.out.println("wsdl: "+StartApp.getWsdl(bankaDuznika.getPort()));
		
			bankaPoverioca = unmarshaluj(Banka.class, new StreamSource(
					new StringReader(odgovorPoverioca)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			wsdlMojaBanka=new URL(StartApp.getWsdl(bankaDuznika.getPort()));
			System.out.println("port: "+bankaDuznika.getPort());
			//WSDLTudjaBanka=new URL(StartApp.getWsdl(bankaPoverioca.getPort()));
		} catch (MalformedURLException e) {
		
			e.printStackTrace();
		}
		
		
		//SALJE NALOG BANCI DUZNIKA
		System.out.println("Salje nalog banci: "+bankaDuznika.getNaziv());
		Service service = Service.create(wsdlMojaBanka, serviceName);
        BankaServis inter = service
                .getPort(portName, BankaServis.class);
        inter.obradiNalog(nalog);
		
	}
	
	public static Presek posaljiZahtev(ZahtevZaIzvod zahtev){
		String oznakaMojeBanke=zahtev.getBrojRacuna().substring(0, 3);
		String upitBanka = "for $x in doc('/content/banka.xml')/banke/banka where $x/oznakaBanke='"
				+oznakaMojeBanke + "' return $x";
		
		String odgBanka=posaljiUpit(upitBanka);
		Banka mojaBanka=null;
		
		try {
			mojaBanka = unmarshaluj(Banka.class, new StreamSource(
					new StringReader(odgBanka)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		URL wsdlMojaBank=null;
		
		try {
			
			wsdlMojaBank=new URL(StartApp.getWsdl(mojaBanka.getPort()));
		} catch (MalformedURLException e) {
		
			e.printStackTrace();
		}
		
		Service service = Service.create(wsdlMojaBank, serviceName);
        BankaServis inter = service
                .getPort(portName, BankaServis.class);
        Presek presek=inter.obradiZahtevZaIzvod(zahtev);
		return presek;
	}
	
	public static String posaljiUpit(String upit) {
		ServerEvaluationCall poziv = StartApp.getClient().newServerEval();
		String odgovor = poziv.xquery(upit).evalAs(String.class);
		return odgovor;
	}

	public static <T> T unmarshaluj(Class<T> cl, Source s) throws JAXBException {
		JAXBContext ctx = JAXBContext.newInstance(cl);
		Unmarshaller u = ctx.createUnmarshaller();
		return u.unmarshal(s, cl).getValue();
	}

	public static <T> void marshaluj(T obj, Writer wr) throws JAXBException {
		JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
		Marshaller m = ctx.createMarshaller();
		m.marshal(obj, wr);
	}
	
}
