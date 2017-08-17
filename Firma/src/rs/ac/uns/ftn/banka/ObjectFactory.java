
package rs.ac.uns.ftn.banka;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.banka package. 
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

    private final static QName _ObradiNalogResponse_QNAME = new QName("http://ftn.uns.ac.rs/banka", "obradiNalogResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.banka
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObradiNalogResponse }
     * 
     */
    public ObradiNalogResponse createObradiNalogResponse() {
        return new ObradiNalogResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObradiNalogResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/banka", name = "obradiNalogResponse")
    public JAXBElement<ObradiNalogResponse> createObradiNalogResponse(ObradiNalogResponse value) {
        return new JAXBElement<ObradiNalogResponse>(_ObradiNalogResponse_QNAME, ObradiNalogResponse.class, null, value);
    }

}
