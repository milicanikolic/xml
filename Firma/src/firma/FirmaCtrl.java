package firma;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;

import app.StartApp;
import faktura.Faktura;
import faktura.StavkaFakture;
import faktura.ZaglavljeFakture;

@LocalBean
@Stateless
@Path("/firma")
public class FirmaCtrl {

	private Firme firmeBaza;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{username}/{password}")
	public Firma login(@PathParam("username") String username, @PathParam("password") String password) {

		JAXBHandle<?> jaxHandle = new JAXBHandle<>(StartApp.getContext());

		StartApp.otvoriKonekciju();

		XMLDocumentManager docMgr = StartApp.getClient().newXMLDocumentManager();

		docMgr.read("/content/firma.xml", jaxHandle);
		firmeBaza = (Firme) jaxHandle.get();

		StartApp.zatvoriKonekciju();

		for (Firma f : firmeBaza.getFirme()) {
			if (f.getUsername().equals(username)) {
				System.out.println("usao u prvi if");
				if (f.getPassword().equals(password)) {
					System.out.println("sve prosao. usename: " + f.getUsername() + " ,sifra: " + f.getPassword());
					return f;
				}
			}
		}
		return null;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Firma> uzmiPreostaleFirme(Firma bezFirme) {

		JAXBHandle<?> jaxHandle = new JAXBHandle<>(StartApp.getContext());
		System.out.println("SADA IMAM " + bezFirme.getNaziv());
		/*
		 * StartApp.otvoriKonekciju();
		 * 
		 * XMLDocumentManager
		 * docMgr=StartApp.getClient().newXMLDocumentManager();
		 * 
		 * docMgr.read("/content/firma.xml", jaxHandle); Firme firmeBaza=(Firme)
		 * jaxHandle.get();
		 * 
		 * StartApp.zatvoriKonekciju();
		 * 
		 */

		List<Firma> listaFirmi = new ArrayList<Firma>();
		for (Firma firma : firmeBaza.getFirme()) {
			System.out.println("IZ BAZE "+ firma.getNaziv());
			if (!((firma.getUsername()).equals(bezFirme.getUsername()))) {
				listaFirmi.add(firma);
				System.out.println("DODAOOO " +firma.getNaziv());
			}
		}

		return listaFirmi;

	}
	@Path("/kreirajFakturu")
	@POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void kreirajFakturu(List<StavkaFakture> stavke) {
	  
		for(StavkaFakture sf:stavke){
			
		System.out.println("backend za fakruru");
		System.out.println(sf.getNazivRobeIliUsluge()+" kol: "+sf.getKolicina());
		}
	/*	
		
	  Firma dobavljac=null;
	  for(Firma firma:firmeBaza.getFirme()) {
	   if(firma.getUsername().equals(anObject)) {
	    dobavljac=firma;
	    break;
	   }
	  }
	  
	  Firma kupac=null;
	  for(Firma firma:firmeBaza.getFirme()) {
	   if(firma.getUsername().equals(anObject)) {
	    kupac=firma;
	    break;
	   }
	  }
	   
	   
	  //u kupcu
	  for(StavkaFakture s:kupac.getStavke()) {
	   for(StavkaFakture kupljena:stavke) {
	    if(kupljena.getRedniBr().equals(s.getRedniBr())) {
	     BigDecimal kolicinaKupca=s.getKolicina();
	     BigDecimal kupujemStavki=kupljena.getKolicina();
	     kolicinaKupca=kolicinaKupca.add(kupujemStavki);
	    }
	   }
	  }
	  
	  
	  //u prodavcu
	    for(StavkaFakture s:dobavljac.getStavke()) {
	     for(StavkaFakture kupljena:stavke) {
	      if(kupljena.getRedniBr().equals(s.getRedniBr())) {
	       BigDecimal kolicinaProdavca=s.getKolicina();
	       BigDecimal kupujemStavki=kupljena.getKolicina();
	       kolicinaProdavca=kolicinaProdavca.subtract(kupujemStavki);
	      }
	     }
	    }
	  
	  ZaglavljeFakture zaglavljeFakture=new ZaglavljeFakture();
	  zaglavljeFakture.setIdPoruke(value);
	  zaglavljeFakture.setNazivDobavljac(value);
	  zaglavljeFakture.setAdresaDobavljac(value);
	  zaglavljeFakture.setPibDobavljac(value);
	  zaglavljeFakture.setNazivKupac(value);
	  zaglavljeFakture.setAdresaKupac(value);
	  zaglavljeFakture.setPibKupac(value);
	  zaglavljeFakture.setBrojRacuna(value);
	  zaglavljeFakture.setDatumRacuna(value);
	  zaglavljeFakture.setVrednostRobe(value);
	  zaglavljeFakture.setVrednostUsluga(value);
	  zaglavljeFakture.setUkupnoRobaIUsluge(value);
	  zaglavljeFakture.setUkupanRabat(value);
	  zaglavljeFakture.setUkupanPorez(value);
	  zaglavljeFakture.setValuta(value);
	  zaglavljeFakture.setIznosZaUplatu(value);
	  zaglavljeFakture.setUplataNaRacun(value);
	  zaglavljeFakture.setDatumValute(value);
	  
	  
	  
	  
	  
	  Faktura faktura=new Faktura();
	  faktura.setZaglavljeFakture(zaglavljeFakture);
	  faktura.setStavkaFakture(stavke);
	 */

}
}