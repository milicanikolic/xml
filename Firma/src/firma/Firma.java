package firma;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import banka.Banka;
import faktura.StavkaFakture;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Firma implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String naziv;
	private String adresa;
	private String PIB;
	private Banka banka;
	private List<StavkaFakture> stavke;

	public Firma() {
		super();
		stavke=new ArrayList<StavkaFakture>();
	}

	public Firma(String username, String password, String naziv, String adresa, String pIB, Banka banka, List<StavkaFakture> stavke) {
		super();
		this.username=username;
		this.password=password;
		this.naziv = naziv;
		this.adresa = adresa;
		this.PIB = pIB;
		this.banka = banka;
		this.stavke=stavke;
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

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}

	public List<StavkaFakture> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaFakture> stavke) {
		this.stavke = stavke;
	}
	
	public void dodajStavku(StavkaFakture stavkaFakture) {
		stavke.add(stavkaFakture);
	}
}