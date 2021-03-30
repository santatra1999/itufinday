package element;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import utils.Header;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RequestMapping(value = "/finday")
@RestController
public class Resource {
	
	@Autowired
	private ClientDaoService clientService;
	@Autowired
	private OffreDaoService offreService;
	@Autowired
	private MvtmobilemoneyDaoService mvtService;	
	@Autowired
	private AchatoffreDaoService achatOffreService;	
	@Autowired
	private StatistiqueDaoService statService;
	@Autowired
	private TypeOffreDaoService typeService;
	@Autowired
	private AppelDaoService appelService;
	@Autowired
	private Offre_and_typeDaoService offreTypeService;
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello world";
	}
	
	@GetMapping("/client/{identif}/{mdp}")
	public Header connexion(@PathVariable String identif,@PathVariable String mdp) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			Client client = new Client(identif,mdp);
			client = clientService.connexion(client);
			data = client;
			if(client == null) {
				header = new Header(400,"Bad Request",data);
			} else {
				header = new Header(200,"Ok",data);
			}
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;
	}	
	
	@PostMapping("/client")
	public Header inscription(@RequestBody Client formData) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			Client savedUser = new Client(formData.getNom(), formData.getDatenaiss(), formData.getMdp(), formData.getIdentif(), formData.getNum());
			clientService.saveClient(savedUser);
			data = savedUser;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;
	}
	
	@GetMapping("/chiffreaffaire")
	public Header getChiffreAffaire() throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			ArrayList<Client> clientList = clientService.getChiffreAffaireClient();
			data = clientList;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}

	@GetMapping("/offrebempividy")
	public Header getOffreBeMpividy() throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			ArrayList<Offre> offreList = offreService.getOffreBeMpividy();
			data = offreList ;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}	
	
	@GetMapping("/offre")
	public Header getOffre() throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			ArrayList<Offre> offreList = offreService.getOffre();
			data = offreList ;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}	
	
	@GetMapping("/depotnonvalide")
	public Header getDepotnonvalide() throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			ArrayList<Mvtmobilemoney> mvtList = mvtService.getDepotNonValide();
			data = mvtList;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}		
	
	@GetMapping("/creditclient")
	public Header getCreditclient(@RequestHeader("Authorization") String token) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			String validToken = token.split(" ")[1];
			int idclient = clientService.getIdclient(validToken);
			double credit = clientService.getCreditClient(idclient);
			System.out.println("credit "+credit);
			data = credit;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}	

	@GetMapping("/deconnexion")
	public Header deconnexion(@RequestHeader("Authorization") String token) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			String validToken = token.split(" ")[1];
			clientService.deconnexion(validToken);			
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}
	
	@GetMapping("/soldemvola")
	public Header getSoldeMvola(@RequestHeader("Authorization") String token) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			String validToken = token.split(" ")[1];
			int idclient = clientService.getIdclient(validToken);
			double solde = clientService.getSoldeMvola(idclient);			
			data = solde;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}	
	
	@GetMapping("/offreclient")
	public Header getOffreClient(@RequestHeader("Authorization") String token) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			String validToken = token.split(" ")[1];
			int idclient = clientService.getIdclient(validToken);
			ArrayList<Achatoffre> offreListe = achatOffreService.getOffre(idclient);			
			data = offreListe;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}	
	
	@GetMapping("/depotvalide/{idmvt}")
	public Header validationDepot(@PathVariable int idmvt) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			mvtService.updateDepot(idmvt);			
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;
	}
	
	@PostMapping("/saveoffre")
	public Header saveOffreC(@RequestBody Offre offre) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			offreService.saveOffre(offre.getNom_offre(), offre.getValue(), offre.getDuree_valide(), offre.getPriorite());
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;
	}	
	
	/*@PostMapping("/saveoffre")
	public Header saveOffreC(@RequestBody Offre_and_type offre) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			offreTypeService.saveOffre_and_type(offre);
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;
	}*/
	
	@PostMapping("/saveoffreandtype")
	public Header saveOffreAndTypeC(@RequestBody Offre_and_type formData) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			offreTypeService.saveOffre_and_type(formData);
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;
	}	
		
	@PostMapping("/savetypeoffre")
	public Header saveTypeOffreC(@RequestBody Offre offre) throws Exception {
		Header header = new Header();
		Object data = null;
		try {	
			offreService.saveTypeOffre(offre.getNom_type_offre());		
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;
	}				
	
	@GetMapping("/stat/chaffjour")
	public Header getChaffjour() throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			ArrayList<Statistique> chaffJour = statService.getChaffJour();
			data = chaffJour;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}	

	@GetMapping("/stat/frequsage")
	public Header getFrequenceusage() throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			ArrayList<Statistique> chaffJour = statService.getFrequenceUsage();
			data = chaffJour;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}
	
	@GetMapping("/stat/totalchaff")
	public Header getTotalChaff() throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			String chaff = statService.getChiffreAffaire();
			data = chaff;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}	
	
	@GetMapping("/stat/client")
	public Header getCountClient() throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			double chaff = statService.getCountClient();
			data = chaff;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}		

	
	@GetMapping("/typeoffre")
	public Header getTypeOffre() throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			ArrayList<TypeOffre> to = typeService.getTypeOffre();
			data = to;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}
	
	@GetMapping("/historique")
	public Header getHistorique(@RequestHeader("Authorization") String token) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			String validToken = token.split(" ")[1];
			int idclient = clientService.getIdclient(validToken);
			ArrayList<Appel> log = appelService.getHistoriqueAppel(idclient);		
			data = log;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}	
	
	@GetMapping("/detailoffre")
	public Header getDetailOffre() throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			ArrayList<Offre> log = offreService.getDetailsOffre();	
			data = log;
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}	

	@PutMapping("/offreupdate/{nom_offre}")
	public Header updateOffre(@PathVariable String nom_offre,@RequestBody Offre offre) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			offreService.update(nom_offre,offre);			
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;
	}	
	
	@PutMapping("/updateoffreandtype/{id_offre_and_type}")
	public Header update(@PathVariable int id_offre_and_type, @RequestBody Offre_and_type offre) throws Exception{
		Header header = new Header();
		Object data = null;
		try {
			System.out.println(offre.getValeur());
			offreTypeService.update(id_offre_and_type, offre);			
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}
	
	@GetMapping("/deleteoffreandtype/{id_offre_and_type}")
	public Header deleteOffraAndType(@PathVariable int id_offre_and_type) throws Exception{
		Header header = new Header();
		Object data = null;
		try {
			offreTypeService.delete(id_offre_and_type);			
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}	
	
	@GetMapping("/countinvalide")
	public Header getCountInvalide() throws Exception{
		Header header = new Header();
		Object data = null;
		try {
			data = statService.getMvtNonValidate();
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;		
	}	
}


