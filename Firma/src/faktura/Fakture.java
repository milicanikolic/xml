package faktura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="fakture")
@XmlAccessorType(XmlAccessType.FIELD)
public class Fakture implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7485456602304920884L;

	@XmlElement(namespace="http://ftn.uns.ac.rs/faktura")
	private List<Faktura> fakture;

	public Fakture() {
		fakture = new ArrayList<Faktura>();
	}

	public List<Faktura> getFakture() {
		return fakture;
	}

	public void setFakture(List<Faktura> fakture) {
		this.fakture = fakture;
	}

	public void dodajFakturu(Faktura f) {
		fakture.add(f);
	}

}
