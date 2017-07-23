package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Firme implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5696192030843771105L;
	private List<Firma> firma;
	
	public Firme() {
		firma=new ArrayList<Firma>();
	}

	public List<Firma> getFirme() {
		return firma;
	}

	public void setFirme(List<Firma> firmaNova) {
		this.firma = firmaNova;
	}
	
	public void dodajFirmu(Firma f) {
		firma.add(f);
	}

}
