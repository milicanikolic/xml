package firma;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.JAXBHandle;

import app.StartApp;
import banka.Banka;
import banka.Banke;
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

		JAXBHandle<?> jaxHandle = new JAXBHandle<>(StartApp.getContextFirma());

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
		List<Firma> listaFirmi = new ArrayList<Firma>();
		for (Firma firma : firmeBaza.getFirme()) {
			if (!((firma.getUsername()).equals(bezFirme.getUsername()))) {
				listaFirmi.add(firma);				
			}
		}

		return listaFirmi;
	}

	@POST
	@Path("/{kupacUlogovan/dobavljacKupujem}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void kreirajFakturu(@PathParam("kupacUlogovan") String kupacUlogovan,
			@PathParam("dobavljacKupujem") String dobavljacKupujem, List<StavkaFakture> stavke) {


		Firma dobavljac = null;
		for (Firma firma : firmeBaza.getFirme()) {
			if (firma.getUsername().equals(dobavljacKupujem)) {
				dobavljac = firma;
				break;
			}
		}

		Firma kupac = null;
		for (Firma firma : firmeBaza.getFirme()) {
			if (firma.getUsername().equals(kupacUlogovan)) {
				kupac = firma;
				break;
			}
		}

		// u kupcu
		for (StavkaFakture s : kupac.getStavke()) {
			for (StavkaFakture kupljena : stavke) {
				if (kupljena.getRedniBr().equals(s.getRedniBr())) {
					BigDecimal kolicinaKupca = s.getKolicina();
					BigDecimal kupujemStavki = kupljena.getKolicina();
					kolicinaKupca = kolicinaKupca.add(kupujemStavki);
				}
			}
		}

		// u prodavcu
		for (StavkaFakture s : dobavljac.getStavke()) {
			for (StavkaFakture kupljena : stavke) {
				if (kupljena.getRedniBr().equals(s.getRedniBr())) {
					BigDecimal kolicinaProdavca = s.getKolicina();
					BigDecimal kupujemStavki = kupljena.getKolicina();
					kolicinaProdavca = kolicinaProdavca.subtract(kupujemStavki);
				}
			}
		}

		// danasnji datum
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		DatatypeFactory datatypeFactory=null;
		try {
			datatypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
		System.out.println("sada datum " + now.toString() + now);

		// racunanje vrednosti i poreza

		BigDecimal robaUkupno = new BigDecimal("0");
		for (StavkaFakture sf : stavke) {
			robaUkupno.add(sf.getJedinicnaCena());
		}

		BigDecimal robaUslugeUkupno = new BigDecimal("0");
		for (StavkaFakture sf : stavke) {
			robaUslugeUkupno.add(sf.getVrednost());
		}
		
		BigDecimal uslugeUkupno = new BigDecimal("0");
		for (StavkaFakture sf : stavke) {
			BigDecimal usluga=sf.getVrednost().subtract(sf.getJedinicnaCena());
			uslugeUkupno.add(usluga);
		}
		
		BigDecimal rabatUkupno = new BigDecimal("0");
		for (StavkaFakture sf : stavke) {
			rabatUkupno.add(sf.getIznosRabata());
		}
		
		BigDecimal porezUkupno = new BigDecimal("0");
		for (StavkaFakture sf : stavke) {
			porezUkupno.add(sf.getUkupanPorez());
		}
		
		BigDecimal uplataBezPoreza = robaUslugeUkupno.subtract(rabatUkupno);
		BigDecimal uplataIznos=uplataBezPoreza.add(porezUkupno);
		
		
		//banka
		JAXBHandle<?> jaxHandle = new JAXBHandle<>(StartApp.getContextBanka());

		StartApp.otvoriKonekciju();

		XMLDocumentManager docMgr = StartApp.getClient().newXMLDocumentManager();

		docMgr.read("/content/banka.xml", jaxHandle);
		Banke bankeBaza = (Banke) jaxHandle.get();

		StartApp.zatvoriKonekciju();
		
		String racun="";
		for(Banka banka:bankeBaza.getBanke()) {
			for(String firmaUsername: banka.getRacuniFirmi().keySet()) {
				if(firmaUsername.equals(dobavljac)){
					racun=banka.getRacuniFirmi().get(firmaUsername);
					break;
				}
			}
				
			}
		//String racun
		

		ZaglavljeFakture zaglavljeFakture = new ZaglavljeFakture();
		zaglavljeFakture.setIdPoruke("bvfdBFDBfdbfdbaas"); //promeniti da bude jedinstveno
		zaglavljeFakture.setNazivDobavljac(dobavljac.getNaziv());
		zaglavljeFakture.setAdresaDobavljac(dobavljac.getAdresa());
		zaglavljeFakture.setPibDobavljac(dobavljac.getPIB());
		zaglavljeFakture.setNazivKupac(kupac.getNaziv());
		zaglavljeFakture.setAdresaKupac(kupac.getAdresa());
		zaglavljeFakture.setPibKupac(kupac.getPIB());
		zaglavljeFakture.setBrojRacuna(new BigDecimal("456")); //promeniti da bude jedinstveno
		zaglavljeFakture.setDatumRacuna(now);
		zaglavljeFakture.setVrednostRobe(robaUkupno);
		zaglavljeFakture.setVrednostUsluga(uslugeUkupno);
		zaglavljeFakture.setUkupnoRobaIUsluge(robaUslugeUkupno);
		zaglavljeFakture.setUkupanRabat(rabatUkupno);
		zaglavljeFakture.setUkupanPorez(porezUkupno);
		zaglavljeFakture.setValuta("RSD");
		zaglavljeFakture.setIznosZaUplatu(uplataIznos);
		zaglavljeFakture.setUplataNaRacun(racun);
		zaglavljeFakture.setDatumValute(now);

		Faktura faktura = new Faktura();
		faktura.setZaglavljeFakture(zaglavljeFakture);
		faktura.setStavkaFakture(stavke);

	}
}