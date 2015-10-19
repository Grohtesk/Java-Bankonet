package bankonet.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.bankonet.Client;
import com.bankonet.ClientNonTrouveException;
import com.bankonet.Compte;
import com.bankonet.CompteCourant;

import bankonet.dao.client.ClientDao;

public class ClientService {
	
	private ClientDao daoClient;
	
	public ClientService(ClientDao daoClient) {
		this.daoClient=daoClient;
	}

	public Client ajouterClient(String identifiant,String nom,String prenom,String password) {
		
		Client client=new Client(identifiant,nom,prenom,password);
		String compteIdentifiant="["+nom+"]_["+prenom+"]_COURANT_1";
		Compte compte=new CompteCourant(compteIdentifiant, compteIdentifiant, 0, 0);
		client.addCompte(compte);
		
		this.daoClient.save(client);
		
		return client;
	}

	public ArrayList<Client> getClients() {
		// TODO Auto-generated method stub
		
		ArrayList<Client> list=(ArrayList<Client>) daoClient.getAll();
		
		return list;
	}
	

	public Client connect(String login, String password) throws ClientNonTrouveException {
		
		ArrayList<Client> list=(ArrayList<Client>) this.daoClient.getAll();
		HashMap<String, Client> map=new HashMap<>();
		
		for (Client client : list) {
			map.put(client.getIdentifiant(), client);
		}
		
		if(map.containsKey(login)){
			Client client=map.get(login);
			if(client.getPassword().equals(password)){
				return client;
			}else{
				throw new ClientNonTrouveException("Connexion impossible");
			}
		}else throw new ClientNonTrouveException();
	}


}