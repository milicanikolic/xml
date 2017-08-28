
package generisani;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mt103n" type="{http://ftn.uns.ac.rs/mt103}mt103" form="qualified"/>
 *         &lt;element name="mt910n" type="{http://ftn.uns.ac.rs/mt910}mt910" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mt103N",
    "mt910N"
})
@XmlRootElement(name = "MT103i910", namespace = "http://ftn.uns.ac.rs/mt103i910")
public class MT103I910 {

    @XmlElement(name = "mt103n", namespace = "http://ftn.uns.ac.rs/mt103i910", required = true)
    protected Mt103 mt103N;
    @XmlElement(name = "mt910n", namespace = "http://ftn.uns.ac.rs/mt103i910", required = true)
    protected Mt910 mt910N;

    /**
     * Gets the value of the mt103N property.
     * 
     * @return
     *     possible object is
     *     {@link Mt103 }
     *     
     */
    public Mt103 getMt103N() {
        return mt103N;
    }

    /**
     * Sets the value of the mt103N property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt103 }
     *     
     */
    public void setMt103N(Mt103 value) {
        this.mt103N = value;
    }

    /**
     * Gets the value of the mt910N property.
     * 
     * @return
     *     possible object is
     *     {@link Mt910 }
     *     
     */
    public Mt910 getMt910N() {
        return mt910N;
    }

    /**
     * Sets the value of the mt910N property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt910 }
     *     
     */
    public void setMt910N(Mt910 value) {
        this.mt910N = value;
    }

}
