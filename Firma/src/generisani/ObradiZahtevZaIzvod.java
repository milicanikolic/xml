
package generisani;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obradiZahtevZaIzvod complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obradiZahtevZaIzvod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zahtev}zahtevZaIzvod" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obradiZahtevZaIzvod", propOrder = {
    "zahtevZaIzvod"
})
public class ObradiZahtevZaIzvod {

    @XmlElement(namespace = "http://ftn.uns.ac.rs/zahtev")
    protected ZahtevZaIzvod zahtevZaIzvod;

    /**
     * Gets the value of the zahtevZaIzvod property.
     * 
     * @return
     *     possible object is
     *     {@link ZahtevZaIzvod }
     *     
     */
    public ZahtevZaIzvod getZahtevZaIzvod() {
        return zahtevZaIzvod;
    }

    /**
     * Sets the value of the zahtevZaIzvod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZahtevZaIzvod }
     *     
     */
    public void setZahtevZaIzvod(ZahtevZaIzvod value) {
        this.zahtevZaIzvod = value;
    }

}
