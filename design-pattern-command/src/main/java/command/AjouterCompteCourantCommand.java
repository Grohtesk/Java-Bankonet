package command;

import java.util.List;
import java.util.Scanner;

import com.bankonet.Client;

import bankonet.metier.ClientService;
import bankonet.metier.CompteService;

public class AjouterCompteCourantCommand extends IhmCommand {
	

	private Scanner sc;
	private ClientService clientService;

	public AjouterCompteCourantCommand(Scanner sc, ClientService clientService) {
		this.sc = sc;
		this.clientService = clientService;
	}

	@Override
	public int getInt() {
		return 8;
	}

	@Override
	public String getLibelle() {
		return "Ajouter un compte courant � un client existant";
	}

	@Override
	public void execute() {
		System.out.println(this.getLibelle());
		System.out.println("Choisissez un client par son identifiant");
		List<Client> clients=clientService.getClients();
		for (Client client : clients) {
			System.out.println(client);
		}
		Client client = clientService.getClient(sc.nextLine());
		System.out.println("Numero du compte");
		String numero=sc.nextLine();
		System.out.println("Intitul� du compte");
		String intitule=sc.nextLine();
		System.out.println("Solde");
		Double solde=sc.nextDouble();
		System.out.println("Montant d�couvert autoris�");
		Double montantDecouvetAutorise=sc.nextDouble();
		
		clientService.ajouterCompteCourant(client,numero,intitule,solde,montantDecouvetAutorise);
	}

	
}
