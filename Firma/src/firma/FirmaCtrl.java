package firma;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.marklogic.client.eval.EvalResult;
import com.marklogic.client.eval.EvalResultIterator;
import com.marklogic.client.eval.ServerEvaluationCall;

import app.StartApp;
import faktura.Faktura;
import faktura.StavkaFakture;
import faktura.ZaglavljeFakture;

@LocalBean
@Stateless
@Path("/firma")
public class FirmaCtrl {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{username}/{password}")
	public Firma login(@PathParam("username") String username, @PathParam("password") String password) {

		String upit = "for $x in doc('/content/firma.xml')/firme/firma" + " where $x/username='" + username
				+ "' and $x/password='" + password + "'" + " return $x";
		String odgovor = posaljiUpit(upit);
		if (odgovor == null) {
			return null;
		}

		Firma firma = null;
		try {
			firma = unmarshaluj(Firma.class, new StreamSource(new StringReader(odgovor)));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return firma;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Firma> uzmiPreostaleFirme(Firma bezFirme) {

		String upit = "for $x in doc('/content/firma.xml')/firme/firma" +
		// " where $x/username!='" + bezFirme.getUsername() + "' and
		// $x/password!='" + bezFirme.getPassword() + "'" +
				" return $x";

		ServerEvaluationCall poziv = StartApp.getClient().newServerEval();
		List<Firma> listaFirmi = new ArrayList<Firma>();
		try {
			JAXBContext jc = JAXBContext.newInstance(Firme.class);
			Unmarshaller unmarsaller = jc.createUnmarshaller();
			EvalResultIterator it = poziv.xquery(upit).eval();
			while (it.hasNext()) {
				EvalResult eval = it.next();
				Firma firma = (Firma) unmarsaller.unmarshal(new StringReader(eval.getString()));
				listaFirmi.add(firma);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaFirmi;

	}
	
	
	

	@POST
	@Path("/{kupacUlogovan}/{dobavljacKupujem}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_XML) // MediaType.APPLICATION_XML) public
	public Faktura kreirajFakturu(@PathParam("kupacUlogovan") String kupacUlogovan,
			@PathParam("dobavljacKupujem") String dobavljacKupujem, List<StavkaFirme> stavke) {

		Firma dobavljac = null;
		String upit1 = "for $x in doc('/content/firma.xml')/firme/firma" + " where $x/username='" + dobavljacKupujem
				+ "'" + " return $x";
		String odgovor1 = posaljiUpit(upit1);
		if (odgovor1 != null) {
			try {
				dobavljac = unmarshaluj(Firma.class, new StreamSource(new StringReader(odgovor1)));
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}

		Firma kupac = null;
		String upit2 = "for $x in doc('/content/firma.xml')/firme/firma" + " where $x/username='" + kupacUlogovan + "'"
				+ " return $x";
		String odgovor2 = posaljiUpit(upit2);
		if (odgovor2 != null) {
			try {
				kupac = unmarshaluj(Firma.class, new StreamSource(new StringReader(odgovor1)));
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}

		if (StartApp.getTrenutniPort().equals(dobavljac.getPort())) {

			// u prodavcu
			for (StavkaFirme s : dobavljac.getStavke()) {
				for (StavkaFirme kupljena : stavke) {
					if (kupljena.getRedniBr().equals(s.getRedniBr())) {
						BigDecimal kolicinaProdavca = s.getKolicina();
						BigDecimal kupujemStavki = kupljena.getKolicina();
						kolicinaProdavca = kolicinaProdavca.subtract(kupujemStavki);
						s.setKolicina(kolicinaProdavca);

						String upit = "xdmp:node-replace(doc('/content/firma.xml')/firme/firma[username='"
								+ dobavljac.getUsername() + "']/stavke[redniBr=" + s.getRedniBr()
								+ "]/kolicina, <kolicina>" + s.getKolicina() + "</kolicina>)";
						posaljiUpit(upit);
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
				e.printStackTrace();
			}

			// racunanje vrednosti i poreza
			for (StavkaFirme s : stavke) {
				BigDecimal vrednost = s.getJedinicnaCena();
				vrednost = vrednost.multiply(s.getKolicina());
				s.setVrednost(vrednost);
			}

			BigDecimal robaUkupno = new BigDecimal(0);
			for (StavkaFirme sf : stavke) {
				robaUkupno = robaUkupno.add(sf.getVrednost());
			}

			BigDecimal uslugeUkupno = new BigDecimal(0);
			for (StavkaFirme sf : stavke) {
				for (StavkaFirme s : dobavljac.getStavke()) {
					if (sf.getRedniBr().equals(s.getRedniBr())) {
						System.out.println("ta je");
						BigDecimal usluga = s.getVrednost().subtract(sf.getJedinicnaCena());
						uslugeUkupno = uslugeUkupno.add(usluga);
					}
				}
			}

			BigDecimal robaUslugeUkupno = robaUkupno.add(uslugeUkupno);

			BigDecimal rabatUkupno = new BigDecimal(0);
			for (StavkaFirme sf : stavke) {
				rabatUkupno = rabatUkupno.add(sf.getIznosRabata());
			}

			BigDecimal porezUkupno = new BigDecimal(0);
			for (StavkaFirme sf : stavke) {
				porezUkupno = porezUkupno.add(sf.getUkupanPorez());
			}

			BigDecimal uplataBezPoreza = robaUslugeUkupno.subtract(rabatUkupno);

			BigDecimal uplataIznos = uplataBezPoreza.add(porezUkupno);

			String racun = dobavljac.getBrojRacuna();

			// racunanje broja racuna - jedinstvenog

			Random ran = new Random();
			BigInteger bigInt = new BigInteger("999999");
			BigInteger rand;

			do {
				rand = new BigInteger(bigInt.bitLength(), ran);

			} while (rand.compareTo(bigInt) >= 0);

			ZaglavljeFakture zaglavljeFakture = new ZaglavljeFakture();

			String uid = UUID.randomUUID().toString();
			zaglavljeFakture.setIdPoruke(uid);
			zaglavljeFakture.setNazivDobavljac(dobavljac.getNaziv());
			zaglavljeFakture.setAdresaDobavljac(dobavljac.getAdresa());
			zaglavljeFakture.setPibDobavljac(dobavljac.getPIB());
			zaglavljeFakture.setNazivKupac(kupac.getNaziv());
			zaglavljeFakture.setAdresaKupac(kupac.getAdresa());
			zaglavljeFakture.setPibKupac(kupac.getPIB());
			zaglavljeFakture.setBrojRacuna(rand);
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

			List<StavkaFakture> stavkeFakture = new ArrayList<StavkaFakture>();
			for (StavkaFirme sFirme : stavke) {
				StavkaFakture sFakture = new StavkaFakture();
				sFakture.setRedniBr(sFirme.getRedniBr());
				sFakture.setNazivRobeIliUsluge(sFirme.getNazivRobeIliUsluge());
				sFakture.setKolicina(sFirme.getKolicina());
				sFakture.setJedinicaMere(sFirme.getJedinicaMere());
				sFakture.setJedinicnaCena(sFirme.getJedinicnaCena());
				sFakture.setVrednost(sFirme.getVrednost());
				sFakture.setProcenatRabata(sFirme.getProcenatRabata());
				sFakture.setIznosRabata(sFirme.getIznosRabata());
				sFakture.setUmanjenoZaRabat(sFirme.getUmanjenoZaRabat());
				sFakture.setUkupanPorez(sFirme.getUkupanPorez());
				stavkeFakture.add(sFakture);

			}

			Faktura faktura = new Faktura();
			faktura.setZaglavljeFakture(zaglavljeFakture);
			faktura.setStavkaFakture(stavkeFakture);

			try {
				StringWriter sw = new StringWriter();
				marshaluj(faktura, sw);
				String kon = sw.toString().substring(sw.toString().indexOf("faktura") - 1, sw.toString().length());
				String upit = "xdmp:node-insert-child(doc('/content/faktura.xml')/fakture," + kon + ");";
				posaljiUpit(upit);
				System.out.println(kon.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}

			return faktura;

		}

		else {
			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target("http://localhost:" + dobavljac.getPort() + "/Firma/rest/firma/"
					+ kupacUlogovan + "/" + dobavljacKupujem);
			Response response = target.request(MediaType.APPLICATION_XML)
					.post(Entity.entity(stavke, "application/json"));
			Faktura faktura = response.readEntity(Faktura.class);
			return faktura;
		}
	}
	
	public String posaljiUpit(String upit) {
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