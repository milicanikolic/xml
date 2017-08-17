package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;


import faktura.Fakture;
import firma.Firme;

@Startup
@Singleton
public class StartApp {

	private static DatabaseClient client;
	private static final String fileName = System.getProperty("jboss.server.config.dir") + "\\standalone-full.xml";
	private static String trenutniPort;

	public StartApp() {

	}

	@PostConstruct
	public void init() {

		System.out.println("POKRENUO");
		uzmiPort();
		otvoriKonekciju();

		
		Marshaller marshallerBanka;

		Fakture f=new Fakture();
	/*
		try {
			
			JAXBContext contextBanka = JAXBContext.newInstance(Fakture.class);
			marshallerBanka = contextBanka.createMarshaller();
			marshallerBanka.marshal(f, new File(System.getProperty("jboss.server.data.dir"), "C:\Users\Nebojsa\Desktop\content\banka.xml"));
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
	}

	public void uzmiPort() {
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		String portPart = "";
		String offset = "";

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

	}

	@PreDestroy
	public void exit() {
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

	public static String getTrenutniPort() {
		return trenutniPort;
	}

	public static void setTrenutniPort(String trenutniPort) {
		StartApp.trenutniPort = trenutniPort;
	}
}