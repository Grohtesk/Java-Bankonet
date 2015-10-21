package bankonet.metier;

import com.bankonet.Client;
import com.bankonet.Compte;
import com.bankonet.CompteCourant;

import bankonet.dao.client.ClientDao;
import bankonet.dao.compte.CompteDao;

public class CompteService {

	private CompteDao daoCompte;
	private ClientDao daoClient;

	public CompteService(CompteDao daoCompte,ClientDao daoClient) {
		this.daoCompte = daoCompte;
		this.daoClient = daoClient;
		
	}

	public void ajouterCompteCourant(Client client, String numero, String intitule, Double solde,Double montantDecouvetAutorise) {
		Compte compte=new CompteCourant(numero,intitule,solde,montantDecouvetAutorise);
		client.addCompte(compte);
		daoCompte.save(compte);
		daoClient.save(client);
	}

}
