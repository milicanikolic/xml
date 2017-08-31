
package generisani;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for primiMt102i910 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="primiMt102i910">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ftn.uns.ac.rs/mt102i910}MT102i910" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "primiMt102i910", propOrder = {
    "mt102I910"
})
public class PrimiMt102I910 {

    @XmlElement(name = "MT102i910", namespace = "http://ftn.uns.ac.rs/mt102i910")
    protected MT102I910 mt102I910;

    /**
     * Gets the value of the mt102I910 property.
     * 
     * @return
     *     possible object is
     *     {@link MT102I910 }
     *     
     */
    public MT102I910 getMT102I910() {
        return mt102I910;
    }

    /**
     * Sets the value of the mt102I910 property.
     * 
     * @param value
     *     allowed object is
     *     {@link MT102I910 }
     *     
     */
    public void setMT102I910(MT102I910 value) {
        this.mt102I910 = value;
    }

}
