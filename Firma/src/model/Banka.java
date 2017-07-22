package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Banka implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String naziv;
	private String sifra;
	private String swiftCode;
	
	public Banka() {
		super();
	}

	public Banka(String naziv, String sifra, String swiftCode) {
		super();
		this.naziv = naziv;
		this.sifra = sifra;
		this.swiftCode = swiftCode;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	
	
	
}
