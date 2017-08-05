package banka;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Banke implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Banka> banka;

	public Banke() {
		banka=new ArrayList<Banka>();
	}

	public List<Banka> getBanke() {
		return banka;
	}

	public void setBanke(List<Banka> banke) {
		this.banka = banke;
	}
	
	public void dodajBanku(Banka banka) {
		this.banka.add(banka);
	}
	
	

}
