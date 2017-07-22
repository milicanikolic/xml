//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.22 at 03:21:51 PM CEST 
//


package faktura;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for stavkaFakture complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="stavkaFakture">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="redniBr">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="nazivRobeIliUsluge">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="120"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="kolicina" type="{http://ftn.uns.ac.rs/tipovi}tipDec10_2"/>
 *         &lt;element name="jedinicaMere">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="6"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="jedinicnaCena" type="{http://ftn.uns.ac.rs/tipovi}tipDec10_2"/>
 *         &lt;element name="vrednost" type="{http://ftn.uns.ac.rs/tipovi}tipDec12_2"/>
 *         &lt;element name="procenatRabata">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="5"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="iznosRabata" type="{http://ftn.uns.ac.rs/tipovi}tipDec12_2"/>
 *         &lt;element name="umanjenoZaRabat" type="{http://ftn.uns.ac.rs/tipovi}tipDec12_2"/>
 *         &lt;element name="ukupanPorez" type="{http://ftn.uns.ac.rs/tipovi}tipDec12_2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "stavkaFakture", propOrder = {
    "redniBr",
    "nazivRobeIliUsluge",
    "kolicina",
    "jedinicaMere",
    "jedinicnaCena",
    "vrednost",
    "procenatRabata",
    "iznosRabata",
    "umanjenoZaRabat",
    "ukupanPorez"
})
public class StavkaFakture {

    @XmlElement(required = true)
    protected BigDecimal redniBr;
    @XmlElement(required = true)
    protected String nazivRobeIliUsluge;
    @XmlElement(required = true)
    protected BigDecimal kolicina;
    @XmlElement(required = true)
    protected String jedinicaMere;
    @XmlElement(required = true)
    protected BigDecimal jedinicnaCena;
    @XmlElement(required = true)
    protected BigDecimal vrednost;
    @XmlElement(required = true)
    protected BigDecimal procenatRabata;
    @XmlElement(required = true)
    protected BigDecimal iznosRabata;
    @XmlElement(required = true)
    protected BigDecimal umanjenoZaRabat;
    @XmlElement(required = true)
    protected BigDecimal ukupanPorez;

    /**
     * Gets the value of the redniBr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRedniBr() {
        return redniBr;
    }

    /**
     * Sets the value of the redniBr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRedniBr(BigDecimal value) {
        this.redniBr = value;
    }

    /**
     * Gets the value of the nazivRobeIliUsluge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivRobeIliUsluge() {
        return nazivRobeIliUsluge;
    }

    /**
     * Sets the value of the nazivRobeIliUsluge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivRobeIliUsluge(String value) {
        this.nazivRobeIliUsluge = value;
    }

    /**
     * Gets the value of the kolicina property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKolicina() {
        return kolicina;
    }

    /**
     * Sets the value of the kolicina property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKolicina(BigDecimal value) {
        this.kolicina = value;
    }

    /**
     * Gets the value of the jedinicaMere property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJedinicaMere() {
        return jedinicaMere;
    }

    /**
     * Sets the value of the jedinicaMere property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJedinicaMere(String value) {
        this.jedinicaMere = value;
    }

    /**
     * Gets the value of the jedinicnaCena property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getJedinicnaCena() {
        return jedinicnaCena;
    }

    /**
     * Sets the value of the jedinicnaCena property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setJedinicnaCena(BigDecimal value) {
        this.jedinicnaCena = value;
    }

    /**
     * Gets the value of the vrednost property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVrednost() {
        return vrednost;
    }

    /**
     * Sets the value of the vrednost property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVrednost(BigDecimal value) {
        this.vrednost = value;
    }

    /**
     * Gets the value of the procenatRabata property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getProcenatRabata() {
        return procenatRabata;
    }

    /**
     * Sets the value of the procenatRabata property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setProcenatRabata(BigDecimal value) {
        this.procenatRabata = value;
    }

    /**
     * Gets the value of the iznosRabata property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIznosRabata() {
        return iznosRabata;
    }

    /**
     * Sets the value of the iznosRabata property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIznosRabata(BigDecimal value) {
        this.iznosRabata = value;
    }

    /**
     * Gets the value of the umanjenoZaRabat property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUmanjenoZaRabat() {
        return umanjenoZaRabat;
    }

    /**
     * Sets the value of the umanjenoZaRabat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUmanjenoZaRabat(BigDecimal value) {
        this.umanjenoZaRabat = value;
    }

    /**
     * Gets the value of the ukupanPorez property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUkupanPorez() {
        return ukupanPorez;
    }

    /**
     * Sets the value of the ukupanPorez property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUkupanPorez(BigDecimal value) {
        this.ukupanPorez = value;
    }

}