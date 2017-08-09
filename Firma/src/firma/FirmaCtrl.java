package firma;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.InputStreamHandle;
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

	/*
	 * @POST
	 * 
	 * @Path("/{kupacUlogovan}/{dobavljacKupujem}")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)//MediaType.APPLICATION_XML) public
	 * Faktura kreirajFakturu(@PathParam("kupacUlogovan") String kupacUlogovan,
	 * 
	 * @PathParam("dobavljacKupujem") String dobavljacKupujem,
	 * List<StavkaFakture> stavke) {
	 * 
	 * 
	 * Firma dobavljac = null; for (Firma firma : firmeBaza.getFirme()) { if
	 * (firma.getUsername().equals(dobavljacKupujem)) { dobavljac = firma;
	 * break; } }
	 * 
	 * Firma kupac = null; for (Firma firma : firmeBaza.getFirme()) { if
	 * (firma.getUsername().equals(kupacUlogovan)) { kupac = firma; break; } }
	 * 
	 * // u kupcu for (StavkaFakture s : kupac.getStavke()) { for (StavkaFakture
	 * kupljena : stavke) { if (kupljena.getRedniBr().equals(s.getRedniBr())) {
	 * BigDecimal kolicinaKupca = s.getKolicina(); BigDecimal kupujemStavki =
	 * kupljena.getKolicina(); kolicinaKupca = kolicinaKupca.add(kupujemStavki);
	 * s.setKolicina(kolicinaKupca); } } }
	 * 
	 * // u prodavcu for (StavkaFakture s : dobavljac.getStavke()) { for
	 * (StavkaFakture kupljena : stavke) { if
	 * (kupljena.getRedniBr().equals(s.getRedniBr())) { BigDecimal
	 * kolicinaProdavca = s.getKolicina(); BigDecimal kupujemStavki =
	 * kupljena.getKolicina(); kolicinaProdavca =
	 * kolicinaProdavca.subtract(kupujemStavki);
	 * s.setKolicina(kolicinaProdavca); } } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // danasnji datum GregorianCalendar gregorianCalendar = new
	 * GregorianCalendar(); DatatypeFactory datatypeFactory=null; try {
	 * datatypeFactory = DatatypeFactory.newInstance(); } catch
	 * (DatatypeConfigurationException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } XMLGregorianCalendar now =
	 * datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
	 * System.out.println("sada datum " + now.getYear() + "/" +
	 * now.getMonth()+"/"+now.getDay()+" "+now.getHour()+":"+now.getMinute()+":"
	 * +now.getSecond() );
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // racunanje vrednosti i poreza
	 * 
	 * BigDecimal robaUkupno = new BigDecimal("0"); for (StavkaFakture sf :
	 * stavke) { robaUkupno.add(sf.getJedinicnaCena()); }
	 * 
	 * BigDecimal robaUslugeUkupno = new BigDecimal("0"); for (StavkaFakture sf
	 * : stavke) { robaUslugeUkupno.add(sf.getVrednost()); }
	 * 
	 * BigDecimal uslugeUkupno = new BigDecimal("0"); for (StavkaFakture sf :
	 * stavke) { BigDecimal
	 * usluga=sf.getVrednost().subtract(sf.getJedinicnaCena());
	 * uslugeUkupno.add(usluga); }
	 * 
	 * BigDecimal rabatUkupno = new BigDecimal("0"); for (StavkaFakture sf :
	 * stavke) { rabatUkupno.add(sf.getIznosRabata()); }
	 * 
	 * BigDecimal porezUkupno = new BigDecimal("0"); for (StavkaFakture sf :
	 * stavke) { porezUkupno.add(sf.getUkupanPorez()); }
	 * 
	 * BigDecimal uplataBezPoreza = robaUslugeUkupno.subtract(rabatUkupno);
	 * BigDecimal uplataIznos=uplataBezPoreza.add(porezUkupno);
	 * 
	 * 
	 * //banka JAXBHandle<?> jaxHandle = new
	 * JAXBHandle<>(StartApp.getContextBanka());
	 * 
	 * 
	 * Marshaller marshallerFirma;
	 * 
	 * try { marshallerFirma = StartApp.getContextFirma().createMarshaller();
	 * marshallerFirma.marshal(firmeBaza, new
	 * File(System.getProperty(StartApp.getJbossDir()),
	 * StartApp.getFileNameFirma())); } catch (JAXBException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); }
	 * 
	 * 
	 * 
	 * 
	 * StartApp.otvoriKonekciju();
	 * 
	 * 
	 * 
	 * XMLDocumentManager docMgr = StartApp.getClient().newXMLDocumentManager();
	 * 
	 * String fileReadFirma = System.getProperty(StartApp.getJbossDir()) + "\\"
	 * + StartApp.getFileNameFirma(); InputStream isFirma = null; try { isFirma
	 * = new FileInputStream(fileReadFirma); InputStreamHandle writeHandleFirma
	 * = new InputStreamHandle(isFirma); docMgr.write("/content/" +
	 * StartApp.getFileNameFirma(), writeHandleFirma); isFirma.close(); } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 * docMgr.read("/content/banka.xml", jaxHandle); Banke bankeBaza = (Banke)
	 * jaxHandle.get();
	 * 
	 * StartApp.zatvoriKonekciju();
	 * 
	 * String racun=""; for(Banka banka:bankeBaza.getBanke()) { for(String
	 * firmaUsername: banka.getRacuniFirmi().keySet()) {
	 * if(firmaUsername.equals(dobavljac)){
	 * racun=banka.getRacuniFirmi().get(firmaUsername); break; } }
	 * 
	 * } //String racun
	 * 
	 * 
	 * ZaglavljeFakture zaglavljeFakture = new ZaglavljeFakture();
	 * zaglavljeFakture.setIdPoruke("bvfdBFDBfdbfdbaas"); //promeniti da bude
	 * jedinstveno zaglavljeFakture.setNazivDobavljac(dobavljac.getNaziv());
	 * zaglavljeFakture.setAdresaDobavljac(dobavljac.getAdresa());
	 * zaglavljeFakture.setPibDobavljac(dobavljac.getPIB());
	 * zaglavljeFakture.setNazivKupac(kupac.getNaziv());
	 * zaglavljeFakture.setAdresaKupac(kupac.getAdresa());
	 * zaglavljeFakture.setPibKupac(kupac.getPIB());
	 * zaglavljeFakture.setBrojRacuna(new BigDecimal("456")); //promeniti da
	 * bude jedinstveno zaglavljeFakture.setDatumRacuna(now);
	 * zaglavljeFakture.setVrednostRobe(robaUkupno);
	 * zaglavljeFakture.setVrednostUsluga(uslugeUkupno);
	 * zaglavljeFakture.setUkupnoRobaIUsluge(robaUslugeUkupno);
	 * zaglavljeFakture.setUkupanRabat(rabatUkupno);
	 * zaglavljeFakture.setUkupanPorez(porezUkupno);
	 * zaglavljeFakture.setValuta("RSD");
	 * zaglavljeFakture.setIznosZaUplatu(uplataIznos);
	 * zaglavljeFakture.setUplataNaRacun(racun);
	 * zaglavljeFakture.setDatumValute(now);
	 * 
	 * Faktura faktura = new Faktura();
	 * faktura.setZaglavljeFakture(zaglavljeFakture);
	 * faktura.setStavkaFakture(stavke);
	 * 
	 * return faktura; }
	 */

	@POST
	@Path("/{kupacUlogovan}/{dobavljacKupujem}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_XML) // MediaType.APPLICATION_XML)
	public Faktura kreirajFakturu(@PathParam("kupacUlogovan") String kupacUlogovan,
			@PathParam("dobavljacKupujem") String dobavljacKupujem, List<StavkaFakture> stavke) {

		String fileName = System.getProperty("jboss.server.config.dir") + "\\standalone-full.xml";
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		String portPart = "";
		String offset = "";
		String trenutniPort = "";

		try {

			String sCurrentLine;

			br1 = new BufferedReader(new FileReader(fileName));

			while ((sCurrentLine = br1.readLine()) != null) {

				if (sCurrentLine.contains("jboss.http.port")) {

					String[] foundPort = sCurrentLine.split(":");
					portPart = (foundPort[1].split("}"))[0];

					break;

				}

			}

			br2 = new BufferedReader(new FileReader(fileName));

			while ((sCurrentLine = br2.readLine()) != null) {

				if (sCurrentLine.contains("jboss.socket.binding.port-offset")) {

					String[] foundOffset = sCurrentLine.split(":");
					offset = (foundOffset[1].split("}"))[0];

					break;

				}

			}

			int portPartInt = Integer.parseInt(portPart);
			int offsetInt = Integer.parseInt(offset);
			int portInt = portPartInt + offsetInt;
			trenutniPort = String.valueOf(portInt);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br1 != null)
					br1.close();

				if (br2 != null)
					br2.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

		Firma dobavljac = null;
		for (Firma firma : firmeBaza.getFirme()) {
			if (firma.getUsername().equals(dobavljacKupujem)) {
				dobavljac = firma;
				break;
			}
		}

		if (trenutniPort.equals(dobavljac.getPort())) {
			System.out.println("u dobavljacu sam, pravim fakturu");

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
						s.setKolicina(kolicinaKupca);
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
						s.setKolicina(kolicinaProdavca);
					}
				}
			}

			// danasnji datum

			XMLGregorianCalendar now = null;
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(new Date());
			try {
				now = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
				now.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
				System.out.println("datum " + now);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			// racunanje vrednosti i poreza

			// vrednost za stavke

			for (StavkaFakture s : stavke) {
				BigDecimal vrednost = s.getJedinicnaCena();
				vrednost = vrednost.multiply(s.getKolicina());
				s.setVrednost(vrednost);

			}

			BigDecimal robaUkupno = new BigDecimal(0);
			for (StavkaFakture sf : stavke) {
				robaUkupno = robaUkupno.add(sf.getVrednost());
			}

			BigDecimal uslugeUkupno = new BigDecimal(0);
			for (StavkaFakture sf : stavke) {
				for (StavkaFakture s : dobavljac.getStavke()) {
					if (sf.getRedniBr().equals(s.getRedniBr())) {
						System.out.println("ta je");
						BigDecimal usluga = s.getVrednost().subtract(sf.getJedinicnaCena());
						uslugeUkupno = uslugeUkupno.add(usluga);
					}
				}
			}

			BigDecimal robaUslugeUkupno = robaUkupno.add(uslugeUkupno);

			BigDecimal rabatUkupno = new BigDecimal(0);
			for (StavkaFakture sf : stavke) {
				rabatUkupno = rabatUkupno.add(sf.getIznosRabata());
			}

			BigDecimal porezUkupno = new BigDecimal(0);
			for (StavkaFakture sf : stavke) {
				porezUkupno = porezUkupno.add(sf.getUkupanPorez());
			}

			BigDecimal uplataBezPoreza = robaUslugeUkupno.subtract(rabatUkupno);

			BigDecimal uplataIznos = uplataBezPoreza.add(porezUkupno);

			// banka
			JAXBHandle<?> jaxHandle = new JAXBHandle<>(StartApp.getContextBanka());

			Marshaller marshallerFirma;

			try {
				marshallerFirma = StartApp.getContextFirma().createMarshaller();
				marshallerFirma.marshal(firmeBaza,
						new File(System.getProperty(StartApp.getJbossDir()), StartApp.getFileNameFirma()));
			} catch (JAXBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			StartApp.otvoriKonekciju();

			XMLDocumentManager docMgr = StartApp.getClient().newXMLDocumentManager();

			String fileReadFirma = System.getProperty(StartApp.getJbossDir()) + "\\" + StartApp.getFileNameFirma();
			InputStream isFirma = null;
			try {
				isFirma = new FileInputStream(fileReadFirma);
				InputStreamHandle writeHandleFirma = new InputStreamHandle(isFirma);
				docMgr.write("/content/" + StartApp.getFileNameFirma(), writeHandleFirma);
				isFirma.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			docMgr.read("/content/banka.xml", jaxHandle);
			Banke bankeBaza = (Banke) jaxHandle.get();

			StartApp.zatvoriKonekciju();

			String racun = "";
			for (Banka banka : bankeBaza.getBanke()) {
				for (String firmaUsername : banka.getRacuniFirmi().keySet()) {
					if (firmaUsername.equals(dobavljac)) {
						racun = banka.getRacuniFirmi().get(firmaUsername);
						break;
					}
				}

			}
			// String racun
			
			
			//racunanje broja racuna - jedinstvenog
			
			Random ran=new Random();
			BigInteger bigInt=new BigInteger("999999");
			//BigInteger maxNum=bigInt.bitLength();
			BigInteger rand;
			do{
				rand=new BigInteger(bigInt.bitLength(),ran);
				
			}while(rand.compareTo(bigInt)>=0);

			ZaglavljeFakture zaglavljeFakture = new ZaglavljeFakture();
			zaglavljeFakture.setIdPoruke(UUID.randomUUID().toString()); 
			System.out.println("UID::::::: " + UUID.randomUUID().toString());													
			zaglavljeFakture.setNazivDobavljac(dobavljac.getNaziv());
			zaglavljeFakture.setAdresaDobavljac(dobavljac.getAdresa());
			zaglavljeFakture.setPibDobavljac(dobavljac.getPIB());
			zaglavljeFakture.setNazivKupac(kupac.getNaziv());
			zaglavljeFakture.setAdresaKupac(kupac.getAdresa());
			zaglavljeFakture.setPibKupac(kupac.getPIB());
			
			zaglavljeFakture.setBrojRacuna(rand); // promeniti
																	// da
																	// bude
																	// jedinstveno
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

			return faktura;

		}

		else {
			System.out.println("u svom sam, sad gadjam dobavljaca");
			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target("http://localhost:" + dobavljac.getPort() + "/Firma/rest/firma/"
					+ kupacUlogovan + "/" + dobavljacKupujem);

			Response response = target.request(MediaType.APPLICATION_XML)
					.post(Entity.entity(stavke, "application/json"));
			Faktura faktura = response.readEntity(Faktura.class);

			return faktura;

		}
	}

}