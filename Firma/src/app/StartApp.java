package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.InputStreamHandle;
import com.marklogic.client.io.JAXBHandle;

import faktura.StavkaFakture;
import firma.Firma;
import firma.Firme;
import model.Banka;

@Startup
@Singleton
public class StartApp {

	private static DatabaseClient client;
	private static JAXBContext context;
	private Marshaller marshaller;
	private static final String FILE_NAME = "firma.xml";
	private static final String JBOSS_DIR = "jboss.server.data.dir";

	public StartApp() {

	}

	@PostConstruct
	public void init() {

		System.out.println("POKRENUO");

		/*
		 * Endpoint.publish("http://localhost:9999/ws/hello", new MyImpl());
		 * 
		 * 
		 * URL url=null; try { url = new
		 * URL("http://localhost:9999/ws/hello?wsdl"); } catch
		 * (MalformedURLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * //1st argument service URI, refer to wsdl document above //2nd
		 * argument is service name, refer to wsdl document above QName qname =
		 * new QName("http://app/", "MyImplService");
		 * 
		 * Service service = Service.create(url, qname);
		 * 
		 * MyInterface hello = service.getPort(MyInterface.class);
		 * 
		 * System.out.println(hello.getHelloWorldAsString("mkyong"));
		 * 
		 */

		List<StavkaFakture> firmaAstavke = new ArrayList<StavkaFakture>();
		List<StavkaFakture> firmaBstavke = new ArrayList<StavkaFakture>();
		StavkaFakture s1 = new StavkaFakture(new BigDecimal(1), "Stavka1", new BigDecimal(10), "jedinicaMere",
				new BigDecimal(15.44), new BigDecimal(10.5), new BigDecimal(15.2), new BigDecimal(19),
				new BigDecimal(81), new BigDecimal(14.54));
		StavkaFakture s2 = new StavkaFakture(new BigDecimal(2), "Stavka2", new BigDecimal(56.5), "jedinicaMere",
				new BigDecimal(54.55), new BigDecimal(84.6), new BigDecimal(20.3), new BigDecimal(43),
				new BigDecimal(53), new BigDecimal(35));
		StavkaFakture s3 = new StavkaFakture(new BigDecimal(3), "Stavka3", new BigDecimal(14), "jedinicaMere",
				new BigDecimal(10.24), new BigDecimal(10), new BigDecimal(8.2), new BigDecimal(11), new BigDecimal(634),
				new BigDecimal(18.2));
		StavkaFakture s4 = new StavkaFakture(new BigDecimal(4), "Stavka4", new BigDecimal(45.23), "jedinicaMere",
				new BigDecimal(67.9), new BigDecimal(97.99), new BigDecimal(15.2), new BigDecimal(19),
				new BigDecimal(81), new BigDecimal(19.4));
		StavkaFakture s5 = new StavkaFakture(new BigDecimal(5), "Stavka5", new BigDecimal(10.69), "jedinicaMere",
				new BigDecimal(14), new BigDecimal(12.5), new BigDecimal(15.2), new BigDecimal(15), new BigDecimal(80),
				new BigDecimal(10.54));
		StavkaFakture s6 = new StavkaFakture(new BigDecimal(6), "Stavka6", new BigDecimal(67), "jedinicaMere",
				new BigDecimal(13.78), new BigDecimal(18.58), new BigDecimal(12), new BigDecimal(14.7),
				new BigDecimal(83), new BigDecimal(32.44));
		firmaAstavke.add(s1);
		firmaAstavke.add(s2);
		firmaAstavke.add(s3);
		firmaBstavke.add(s4);
		firmaBstavke.add(s5);
		firmaBstavke.add(s6);

		Banka bankaC = new Banka("Agricole", "123", "500");
		Banka bankaD = new Banka("Generali", "456", "345");

		Firme firme = new Firme();
		Firma firmaA = new Firma("firmaA", "firmaA", "Firma A", "Novosadskog Sajma 10", "12345", bankaC, firmaAstavke);
		Firma firmaB = new Firma("firmaB", "firmaB", "Firma B", "Bulevar Oslobodjenja 55", "56789", bankaD,
				firmaBstavke);

		firme.dodajFirmu(firmaA);
		firme.dodajFirmu(firmaB);

		try {
			StartApp.context = JAXBContext.newInstance(Firme.class);
			marshaller = StartApp.context.createMarshaller();
			marshaller.marshal(firme, new File(System.getProperty(JBOSS_DIR), FILE_NAME));
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		otvoriKonekciju();

		XMLDocumentManager docMgr = client.newXMLDocumentManager();
		String fileRead = System.getProperty(JBOSS_DIR) + "\\" + FILE_NAME;
		InputStream is = null;
		try {
			is = new FileInputStream(fileRead);
			InputStreamHandle writeHandle = new InputStreamHandle(is);
			docMgr.write("/content/" + FILE_NAME, writeHandle);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		zatvoriKonekciju();

	}

	public static void otvoriKonekciju() {
		client = DatabaseClientFactory.newClient("localhost", 8003, "admin", "admin", Authentication.DIGEST);

	}

	public static void zatvoriKonekciju() {
		client.release();
	}

	public static DatabaseClient getClient() {
		return client;
	}

	public static void setClient(DatabaseClient client) {
		StartApp.client = client;
	}

	public static JAXBContext getContext() {
		return context;
	}

	public static void setContext(JAXBContext context) {
		StartApp.context = context;
	}

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

}