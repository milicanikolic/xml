
package generisani;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for odobriSredstva complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="odobriSredstva">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ftn.uns.ac.rs/mt103i910}MT103i910" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "odobriSredstva", propOrder = {
    "mt103I910"
})
public class OdobriSredstva {

    @XmlElement(name = "MT103i910", namespace = "http://ftn.uns.ac.rs/mt103i910")
    protected MT103I910 mt103I910;

    /**
     * Gets the value of the mt103I910 property.
     * 
     * @return
     *     possible object is
     *     {@link MT103I910 }
     *     
     */
    public MT103I910 getMT103I910() {
        return mt103I910;
    }

    /**
     * Sets the value of the mt103I910 property.
     * 
     * @param value
     *     allowed object is
     *     {@link MT103I910 }
     *     
     */
    public void setMT103I910(MT103I910 value) {
        this.mt103I910 = value;
    }

}
