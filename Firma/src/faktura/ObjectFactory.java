//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.22 at 03:21:51 PM CEST 
//


package faktura;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the faktura package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StavkaFakture_QNAME = new QName("http://ftn.uns.ac.rs/faktura", "stavkaFakture");
    private final static QName _ZaglavljeFakture_QNAME = new QName("http://ftn.uns.ac.rs/faktura", "zaglavljeFakture");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: faktura
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ZaglavljeFakture }
     * 
     */
    public ZaglavljeFakture createZaglavljeFakture() {
        return new ZaglavljeFakture();
    }

    /**
     * Create an instance of {@link Faktura }
     * 
     */
    public Faktura createFaktura() {
        return new Faktura();
    }

    /**
     * Create an instance of {@link StavkaFakture }
     * 
     */
    public StavkaFakture createStavkaFakture() {
        return new StavkaFakture();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StavkaFakture }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/faktura", name = "stavkaFakture")
    public JAXBElement<StavkaFakture> createStavkaFakture(StavkaFakture value) {
        return new JAXBElement<StavkaFakture>(_StavkaFakture_QNAME, StavkaFakture.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ZaglavljeFakture }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/faktura", name = "zaglavljeFakture")
    public JAXBElement<ZaglavljeFakture> createZaglavljeFakture(ZaglavljeFakture value) {
        return new JAXBElement<ZaglavljeFakture>(_ZaglavljeFakture_QNAME, ZaglavljeFakture.class, null, value);
    }

}
