//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.22 at 03:21:51 PM CEST 
//


package faktura;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for faktura complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="faktura">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ftn.uns.ac.rs/faktura}zaglavljeFakture"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/faktura}zaglavljeFakture"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "faktura", propOrder = {
    "content"
})
public class Faktura {

    @XmlElementRef(name = "zaglavljeFakture", namespace = "http://ftn.uns.ac.rs/faktura", type = JAXBElement.class, required = false)
    protected List<JAXBElement<ZaglavljeFakture>> content;

    /**
     * Gets the rest of the content model. 
     * 
     * <p>
     * You are getting this "catch-all" property because of the following reason: 
     * The field name "ZaglavljeFakture" is used by two different parts of a schema. See: 
     * line 9 of file:/C:/Users/NINAM/Desktop/Faklutet/IV_godina/Drugi%20semestar/XML/NasProjekatBaza/Firma/WebContent/seme/faktura.xsd
     * line 8 of file:/C:/Users/NINAM/Desktop/Faklutet/IV_godina/Drugi%20semestar/XML/NasProjekatBaza/Firma/WebContent/seme/faktura.xsd
     * <p>
     * To get rid of this property, apply a property customization to one 
     * of both of the following declarations to change their names: 
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link ZaglavljeFakture }{@code >}
     * 
     * 
     */
    public List<JAXBElement<ZaglavljeFakture>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<ZaglavljeFakture>>();
        }
        return this.content;
    }

}
