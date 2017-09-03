package izvod;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import app.PozivServisa;
import generisani.Presek;
import generisani.StavkaPreseka;
import generisani.ZahtevZaIzvod;

@LocalBean
@Stateless
@Path("/zahtev")
public class ZahtevIzvodCtrl {

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void posaljiZahtev(ZahtevZaIzvod zahtev){
		System.out.println("zahtev za izvod: "+zahtev.getBrojRacuna());
		
		Presek presek=PozivServisa.posaljiZahtev(zahtev);
		
		if(presek!=null) {
		for(StavkaPreseka s: presek.getStavkaPreseka()) {
		System.out.println("Presek iznos " + s.getIznos());
		}
		}
		else {
			System.out.println("PRESEK NULL");
		}
		
	}
}
