
package generisani;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for mt900 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mt900">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idPoruke" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="swiftBanDuznik" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="obracunskiRacBanDuznik" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idPorukeNaloga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datumValute" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="iznos" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="sifraValute" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mt900", namespace = "http://ftn.uns.ac.rs/mt900", propOrder = {
    "idPoruke",
    "swiftBanDuznik",
    "obracunskiRacBanDuznik",
    "idPorukeNaloga",
    "datumValute",
    "iznos",
    "sifraValute"
})
public class Mt900 {

    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt900", required = true)
    protected String idPoruke;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt900", required = true)
    protected String swiftBanDuznik;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt900", required = true)
    protected String obracunskiRacBanDuznik;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt900", required = true)
    protected String idPorukeNaloga;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt900", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt900", required = true)
    protected BigDecimal iznos;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt900", required = true)
    protected String sifraValute;

    /**
     * Gets the value of the idPoruke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPoruke() {
        return idPoruke;
    }

    /**
     * Sets the value of the idPoruke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPoruke(String value) {
        this.idPoruke = value;
    }

    /**
     * Gets the value of the swiftBanDuznik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwiftBanDuznik() {
        return swiftBanDuznik;
    }

    /**
     * Sets the value of the swiftBanDuznik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwiftBanDuznik(String value) {
        this.swiftBanDuznik = value;
    }

    /**
     * Gets the value of the obracunskiRacBanDuznik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObracunskiRacBanDuznik() {
        return obracunskiRacBanDuznik;
    }

    /**
     * Sets the value of the obracunskiRacBanDuznik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObracunskiRacBanDuznik(String value) {
        this.obracunskiRacBanDuznik = value;
    }

    /**
     * Gets the value of the idPorukeNaloga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPorukeNaloga() {
        return idPorukeNaloga;
    }

    /**
     * Sets the value of the idPorukeNaloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPorukeNaloga(String value) {
        this.idPorukeNaloga = value;
    }

    /**
     * Gets the value of the datumValute property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumValute() {
        return datumValute;
    }

    /**
     * Sets the value of the datumValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumValute(XMLGregorianCalendar value) {
        this.datumValute = value;
    }

    /**
     * Gets the value of the iznos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIznos() {
        return iznos;
    }

    /**
     * Sets the value of the iznos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIznos(BigDecimal value) {
        this.iznos = value;
    }

    /**
     * Gets the value of the sifraValute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraValute() {
        return sifraValute;
    }

    /**
     * Sets the value of the sifraValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraValute(String value) {
        this.sifraValute = value;
    }

}
