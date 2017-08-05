package banka;

import java.io.Serializable;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Banka implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String naziv;
	private String obracunskiRacun;
	private String swiftCode;
	private HashMap<String, String> racunFirme;

	public Banka() {
		super();
		racunFirme=new HashMap<String, String>();
	}

	public Banka(String naziv, String obracunskiRacun, String swiftCode) {
		super();
		racunFirme=new HashMap<String, String>();
		this.naziv = naziv;
		this.obracunskiRacun=obracunskiRacun;
		this.swiftCode = swiftCode;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getObracunskiRacun() {
		return obracunskiRacun;
	}

	public void setObracunskiRacun(String obracunskiRacun) {
		this.obracunskiRacun = obracunskiRacun;
	}

	public HashMap<String, String> getRacuniFirmi() {
		return racunFirme;
	}

	public void setRacuniFirmi(HashMap<String, String> racuniFirmi) {
		this.racunFirme= racuniFirmi;
	}
	
	public void dodajRacun(String firma, String brRacuna) {
		racunFirme.put(firma, brRacuna);
	}

}