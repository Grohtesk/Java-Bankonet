package bankonet.metier;

import com.bankonet.Client;
import com.bankonet.Compte;
import com.bankonet.CompteCourant;

import bankonet.dao.client.ClientDao;

public class CompteService {

	private ClientDao daoClient;

	public CompteService(ClientDao daoClient) {
		this.daoClient = daoClient;
		
	}

	public void ajouterCompteCourant(Client client, String numero, String intitule, Double solde,Double montantDecouvetAutorise) {
		Compte compte=new CompteCourant(numero,intitule,solde,montantDecouvetAutorise);
		client.addCompte(compte);
		daoClient.save(client);
	}

}
