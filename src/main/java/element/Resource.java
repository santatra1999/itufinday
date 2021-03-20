package element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import utils.Header;

@CrossOrigin(origins = "http://localhost:5555")
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
			//throw e;
		}
		return header;
	}	
	
	@PostMapping("/client")
	public Header inscription(@RequestParam HashMap<String, Object> formData) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			Client savedUser = new Client(formData);
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
			ArrayList<Client> clientList = clientService.getChiffreAffaire();
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
			//throw e;
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
			// throw e;
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
			// throw e;
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
			// throw e;
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
			// throw e;
		}
		return header;		
	}	
	
//	@PostMapping("/typeoffre")
/*	public Header saveTypeOffre(@RequestParam HashMap<String, Object> formData) throws Exception {
		Header header = new Header();
		Object data = null;
		try {
			new OffreDaoService().saveTypeOffre(formData);
			header = new Header(200,"Ok",data);
		} catch (Exception e) {
			header = new Header(400,e.getMessage(),data);
			throw e;
		}
		return header;
	}	*/
	
}


