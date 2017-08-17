
package rs.ac.uns.ftn.nalog;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.nalog package. 
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

    private final static QName _Nalog_QNAME = new QName("http://ftn.uns.ac.rs/nalog", "nalog");
    private final static QName _ObradiNalog_QNAME = new QName("http://ftn.uns.ac.rs/nalog", "obradiNalog");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.nalog
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObradiNalog }
     * 
     */
    public ObradiNalog createObradiNalog() {
        return new ObradiNalog();
    }

    /**
     * Create an instance of {@link Nalog }
     * 
     */
    public Nalog createNalog() {
        return new Nalog();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Nalog }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/nalog", name = "nalog")
    public JAXBElement<Nalog> createNalog(Nalog value) {
        return new JAXBElement<Nalog>(_Nalog_QNAME, Nalog.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObradiNalog }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/nalog", name = "obradiNalog")
    public JAXBElement<ObradiNalog> createObradiNalog(ObradiNalog value) {
        return new JAXBElement<ObradiNalog>(_ObradiNalog_QNAME, ObradiNalog.class, null, value);
    }

}
