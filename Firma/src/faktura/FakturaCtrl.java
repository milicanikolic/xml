package faktura;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import wrapper.Fakture;
import app.StartApp;

import com.marklogic.client.eval.EvalResult;
import com.marklogic.client.eval.EvalResultIterator;
import com.marklogic.client.eval.ServerEvaluationCall;

@LocalBean
@Stateless
@Path("/faktura")
public class FakturaCtrl {

	@GET
	@Path("/{pib}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Faktura> uzmiFakture(@PathParam("pib") String pib) {

		String upitPre = "declare namespace fak='http://ftn.uns.ac.rs/faktura';"
				+ " for $x in doc('/content/faktura.xml')/fakture/fak:faktura[fak:zaglavljeFakture/fak:pibKupac='"
				+ pib + "'] return $x";

		ServerEvaluationCall poziv = StartApp.getClient().newServerEval();
		List<Faktura> listaFaktura = new ArrayList<Faktura>();
		try {
			JAXBContext jc = JAXBContext.newInstance(Fakture.class);
			Unmarshaller unmarsaller = jc.createUnmarshaller();
			EvalResultIterator it = poziv.xquery(upitPre).eval();
			while (it.hasNext()) {
				EvalResult eval = it.next();
				Faktura f = (Faktura) unmarsaller.unmarshal(new StringReader(
						eval.getString()));
				listaFaktura.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaFaktura;

	}
}