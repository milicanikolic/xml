package firma;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Firma implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String port;
	private String naziv;
	private String adresa;
	private String PIB;
	private String brojRacuna;
	private List<StavkaFirme> stavke;

	public Firma() {
		super();
		stavke = new ArrayList<StavkaFirme>();
	}

	public Firma(String username, String password, String port, String naziv, String adresa, String pIB,
			String brojRacuna, List<StavkaFirme> stavke) {
		super();
		this.username = username;
		this.password = password;
		this.port = port;
		this.naziv = naziv;
		this.adresa = adresa;
		this.PIB = pIB;
		this.brojRacuna = brojRacuna;
		this.stavke = stavke;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPIB() {
		return PIB;
	}

	public void setPIB(String pIB) {
		PIB = pIB;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public List<StavkaFirme> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaFirme> stavke) {
		this.stavke = stavke;
	}

	public void dodajStavku(StavkaFirme stavkaFirme) {
		stavke.add(stavkaFirme);
	}

}