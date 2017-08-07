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

import banka.Banka;
import banka.Banke;
import faktura.StavkaFakture;
import firma.Firma;
import firma.Firme;

@Startup
@Singleton
public class StartApp {

 private static DatabaseClient client;
 private static JAXBContext contextFirma;
 private static JAXBContext contextBanka;
 
 private static final String FILE_NAME_FIRMA = "firma.xml";
 private static final String FILE_NAME_BANKA = "banka.xml";
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
  StavkaFakture s1 = new StavkaFakture(new BigDecimal("1"), "Stavka1", new BigDecimal("10"), "jedinicaMere",
    new BigDecimal("150.44"), new BigDecimal("173.8"), new BigDecimal("5.2"), new BigDecimal("9.0376"),
    new BigDecimal("164.7624"), new BigDecimal("14.54"));
  StavkaFakture s2 = new StavkaFakture(new BigDecimal("2"), "Stavka2", new BigDecimal("56"), "jedinicaMere",
    new BigDecimal("542.55"), new BigDecimal("598.6"), new BigDecimal("2.3"), new BigDecimal("13.76"),
    new BigDecimal("584.84"), new BigDecimal("35"));
  StavkaFakture s3 = new StavkaFakture(new BigDecimal("3"), "Stavka3", new BigDecimal("14"), "jedinicaMere",
    new BigDecimal("107.24"), new BigDecimal("123"), new BigDecimal("8.2"), new BigDecimal("10.086"), new BigDecimal("112.914"),
    new BigDecimal("18.2"));
  StavkaFakture s4 = new StavkaFakture(new BigDecimal("4"), "Stavka4", new BigDecimal("45"), "jedinicaMere",
    new BigDecimal("678.9"), new BigDecimal("973.99"), new BigDecimal("15.2"), new BigDecimal("148.04648"),
    new BigDecimal("825.94352"), new BigDecimal("96.4"));
  StavkaFakture s5 = new StavkaFakture(new BigDecimal("5"), "Stavka5", new BigDecimal("10"), "jedinicaMere",
    new BigDecimal("1421"), new BigDecimal("1487.5"), new BigDecimal("15.2"), new BigDecimal("226.1"), new BigDecimal("1261.4"),
    new BigDecimal("109.54"));
  StavkaFakture s6 = new StavkaFakture(new BigDecimal("6"), "Stavka6", new BigDecimal("67"), "jedinicaMere",
    new BigDecimal("1356.78"), new BigDecimal("1643.58"), new BigDecimal("12"), new BigDecimal("197.2296"),
    new BigDecimal("1446.3504"), new BigDecimal("322.44"));
  firmaAstavke.add(s1);
  firmaAstavke.add(s2);
  firmaAstavke.add(s3);
  firmaBstavke.add(s4);
  firmaBstavke.add(s5);
  firmaBstavke.add(s6);
  
  Banke banke=new Banke();
  Banka bankaC = new Banka("Agricole", "123", "500");
  Banka bankaD = new Banka("Generali", "456", "345");

  Firme firme = new Firme();
  Firma firmaA = new Firma("f", "f", "8180", "Firma A", "Novosadskog Sajma 10", "12345", bankaC, firmaAstavke);
  Firma firmaB = new Firma("firmaB", "firmaB", "8280", "Firma B", "Bulevar Oslobodjenja 55", "56789", bankaD,
    firmaBstavke);
  
  bankaC.dodajRacun(firmaA.getUsername(), "racunfirmeA");
  bankaD.dodajRacun(firmaB.getUsername(), "racunfirmeB");

  firme.dodajFirmu(firmaA);
  firme.dodajFirmu(firmaB);
  
  banke.dodajBanku(bankaC);
  banke.dodajBanku(bankaD);

  Marshaller marshallerFirma;
  Marshaller marshallerBanka;
  
  try {
   StartApp.contextFirma = JAXBContext.newInstance(Firme.class);
   StartApp.contextBanka = JAXBContext.newInstance(Banke.class);
   marshallerFirma = StartApp.contextFirma.createMarshaller();
   marshallerFirma.marshal(firme, new File(System.getProperty(JBOSS_DIR), FILE_NAME_FIRMA));
   marshallerBanka=StartApp.contextBanka.createMarshaller();
   marshallerBanka.marshal(banke, new File(System.getProperty(JBOSS_DIR), FILE_NAME_BANKA));
  } catch (JAXBException e1) {
   // TODO Auto-generated catch block
   e1.printStackTrace();
  }

  otvoriKonekciju();

  XMLDocumentManager docMgr = client.newXMLDocumentManager();
  String fileReadFirma = System.getProperty(JBOSS_DIR) + "\\" + FILE_NAME_FIRMA;
  String fileReadBanka = System.getProperty(JBOSS_DIR) + "\\" + FILE_NAME_BANKA;
  InputStream isFirma = null;
  InputStream isBanka = null;
  try {
   isFirma = new FileInputStream(fileReadFirma);
   isBanka= new FileInputStream(fileReadBanka);
   InputStreamHandle writeHandleFirma = new InputStreamHandle(isFirma);
   InputStreamHandle writeHandleBanka = new InputStreamHandle(isBanka);
   docMgr.write("/content/" + FILE_NAME_FIRMA, writeHandleFirma);
   docMgr.write("/content/" + FILE_NAME_BANKA, writeHandleBanka);
   isFirma.close();
   isBanka.close();
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

 public static JAXBContext getContextFirma() {
  return contextFirma;
 }

 public static void setContextFirma(JAXBContext context) {
  StartApp.contextFirma = context;
 }

 public static JAXBContext getContextBanka() {
  return contextBanka;
 }

 public static void setContextBanka(JAXBContext contextBanka) {
  StartApp.contextBanka = contextBanka;
 }

 
 public static String getJbossDir() {
  return JBOSS_DIR;
 }

 public static String getFileNameFirma() {
  return FILE_NAME_FIRMA;
 }

 public static String getFileNameBanka() {
  return FILE_NAME_BANKA;
 }
 
 
 

}