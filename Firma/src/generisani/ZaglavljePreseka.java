
package generisani;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for zaglavljePreseka complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="zaglavljePreseka">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="brojRacuna" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datumNaloga" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="brojPreseka" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="prethodnoStanje" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="brPromenaUKorist" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ukupnoUKorist" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="brPromenaNaTeret" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ukupnoNaTeret" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="novoStanje" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zaglavljePreseka", namespace = "http://ftn.uns.ac.rs/presek", propOrder = {
    "brojRacuna",
    "datumNaloga",
    "brojPreseka",
    "prethodnoStanje",
    "brPromenaUKorist",
    "ukupnoUKorist",
    "brPromenaNaTeret",
    "ukupnoNaTeret",
    "novoStanje"
})
public class ZaglavljePreseka {

    @XmlElement(namespace = "http://ftn.uns.ac.rs/presek", required = true)
    protected String brojRacuna;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/presek", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumNaloga;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/presek", required = true)
    protected BigInteger brojPreseka;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/presek", required = true)
    protected BigDecimal prethodnoStanje;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/presek")
    protected int brPromenaUKorist;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/presek", required = true)
    protected BigDecimal ukupnoUKorist;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/presek")
    protected int brPromenaNaTeret;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/presek", required = true)
    protected BigDecimal ukupnoNaTeret;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/presek", required = true)
    protected BigDecimal novoStanje;

    /**
     * Gets the value of the brojRacuna property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojRacuna() {
        return brojRacuna;
    }

    /**
     * Sets the value of the brojRacuna property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojRacuna(String value) {
        this.brojRacuna = value;
    }

    /**
     * Gets the value of the datumNaloga property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumNaloga() {
        return datumNaloga;
    }

    /**
     * Sets the value of the datumNaloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumNaloga(XMLGregorianCalendar value) {
        this.datumNaloga = value;
    }

    /**
     * Gets the value of the brojPreseka property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBrojPreseka() {
        return brojPreseka;
    }

    /**
     * Sets the value of the brojPreseka property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojPreseka(BigInteger value) {
        this.brojPreseka = value;
    }

    /**
     * Gets the value of the prethodnoStanje property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrethodnoStanje() {
        return prethodnoStanje;
    }

    /**
     * Sets the value of the prethodnoStanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrethodnoStanje(BigDecimal value) {
        this.prethodnoStanje = value;
    }

    /**
     * Gets the value of the brPromenaUKorist property.
     * 
     */
    public int getBrPromenaUKorist() {
        return brPromenaUKorist;
    }

    /**
     * Sets the value of the brPromenaUKorist property.
     * 
     */
    public void setBrPromenaUKorist(int value) {
        this.brPromenaUKorist = value;
    }

    /**
     * Gets the value of the ukupnoUKorist property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUkupnoUKorist() {
        return ukupnoUKorist;
    }

    /**
     * Sets the value of the ukupnoUKorist property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUkupnoUKorist(BigDecimal value) {
        this.ukupnoUKorist = value;
    }

    /**
     * Gets the value of the brPromenaNaTeret property.
     * 
     */
    public int getBrPromenaNaTeret() {
        return brPromenaNaTeret;
    }

    /**
     * Sets the value of the brPromenaNaTeret property.
     * 
     */
    public void setBrPromenaNaTeret(int value) {
        this.brPromenaNaTeret = value;
    }

    /**
     * Gets the value of the ukupnoNaTeret property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUkupnoNaTeret() {
        return ukupnoNaTeret;
    }

    /**
     * Sets the value of the ukupnoNaTeret property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUkupnoNaTeret(BigDecimal value) {
        this.ukupnoNaTeret = value;
    }

    /**
     * Gets the value of the novoStanje property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNovoStanje() {
        return novoStanje;
    }

    /**
     * Sets the value of the novoStanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNovoStanje(BigDecimal value) {
        this.novoStanje = value;
    }

}
