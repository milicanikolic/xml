
package generisani;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for mt910 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mt910">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idPoruke" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="swiftBanPoverioc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="obracunskiRacBanPoverioc" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "mt910", namespace = "http://ftn.uns.ac.rs/mt910", propOrder = {
    "idPoruke",
    "swiftBanPoverioc",
    "obracunskiRacBanPoverioc",
    "idPorukeNaloga",
    "datumValute",
    "iznos",
    "sifraValute"
})
public class Mt910 {

    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt910", required = true)
    protected String idPoruke;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt910", required = true)
    protected String swiftBanPoverioc;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt910", required = true)
    protected String obracunskiRacBanPoverioc;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt910", required = true)
    protected String idPorukeNaloga;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt910", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt910", required = true)
    protected BigDecimal iznos;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/mt910", required = true)
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
     * Gets the value of the swiftBanPoverioc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwiftBanPoverioc() {
        return swiftBanPoverioc;
    }

    /**
     * Sets the value of the swiftBanPoverioc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwiftBanPoverioc(String value) {
        this.swiftBanPoverioc = value;
    }

    /**
     * Gets the value of the obracunskiRacBanPoverioc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObracunskiRacBanPoverioc() {
        return obracunskiRacBanPoverioc;
    }

    /**
     * Sets the value of the obracunskiRacBanPoverioc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObracunskiRacBanPoverioc(String value) {
        this.obracunskiRacBanPoverioc = value;
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
