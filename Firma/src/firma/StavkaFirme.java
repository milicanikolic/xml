package firma;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StavkaFirme implements Serializable {

	private static final long serialVersionUID = 4440887577193866324L;

	private BigDecimal redniBr;
	private String nazivRobeIliUsluge;
	private BigDecimal kolicina;
	private String jedinicaMere;
	private BigDecimal jedinicnaCena;
	private BigDecimal vrednost;
	private BigDecimal procenatRabata;
	private BigDecimal iznosRabata;
	private BigDecimal umanjenoZaRabat;
	private BigDecimal ukupanPorez;

	public StavkaFirme() {
		super();
	}

	public StavkaFirme(BigDecimal redniBr, String nazivRobeIliUsluge,
			BigDecimal kolicina, String jedinicaMere, BigDecimal jedinicnaCena,
			BigDecimal vrednost, BigDecimal procenatRabata,
			BigDecimal iznosRabata, BigDecimal umanjenoZaRabat,
			BigDecimal ukupanPorez) {
		super();
		this.redniBr = redniBr;
		this.nazivRobeIliUsluge = nazivRobeIliUsluge;
		this.kolicina = kolicina;
		this.jedinicaMere = jedinicaMere;
		this.jedinicnaCena = jedinicnaCena;
		this.vrednost = vrednost;
		this.procenatRabata = procenatRabata;
		this.iznosRabata = iznosRabata;
		this.umanjenoZaRabat = umanjenoZaRabat;
		this.ukupanPorez = ukupanPorez;
	}

	public BigDecimal getRedniBr() {
		return redniBr;
	}

	public void setRedniBr(BigDecimal redniBr) {
		this.redniBr = redniBr;
	}

	public String getNazivRobeIliUsluge() {
		return nazivRobeIliUsluge;
	}

	public void setNazivRobeIliUsluge(String nazivRobeIliUsluge) {
		this.nazivRobeIliUsluge = nazivRobeIliUsluge;
	}

	public BigDecimal getKolicina() {
		return kolicina;
	}

	public void setKolicina(BigDecimal kolicina) {
		this.kolicina = kolicina;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public BigDecimal getJedinicnaCena() {
		return jedinicnaCena;
	}

	public void setJedinicnaCena(BigDecimal jedinicnaCena) {
		this.jedinicnaCena = jedinicnaCena;
	}

	public BigDecimal getVrednost() {
		return vrednost;
	}

	public void setVrednost(BigDecimal vrednost) {
		this.vrednost = vrednost;
	}

	public BigDecimal getProcenatRabata() {
		return procenatRabata;
	}

	public void setProcenatRabata(BigDecimal procenatRabata) {
		this.procenatRabata = procenatRabata;
	}

	public BigDecimal getIznosRabata() {
		return iznosRabata;
	}

	public void setIznosRabata(BigDecimal iznosRabata) {
		this.iznosRabata = iznosRabata;
	}

	public BigDecimal getUmanjenoZaRabat() {
		return umanjenoZaRabat;
	}

	public void setUmanjenoZaRabat(BigDecimal umanjenoZaRabat) {
		this.umanjenoZaRabat = umanjenoZaRabat;
	}

	public BigDecimal getUkupanPorez() {
		return ukupanPorez;
	}

	public void setUkupanPorez(BigDecimal ukupanPorez) {
		this.ukupanPorez = ukupanPorez;
	}

}
