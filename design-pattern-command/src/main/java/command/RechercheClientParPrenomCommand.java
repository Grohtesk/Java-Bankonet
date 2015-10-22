package command;

import java.util.List;
import java.util.Scanner;

import com.bankonet.Client;

import bankonet.metier.ClientService;

public class RechercheClientParPrenomCommand extends IhmCommand {
	
	ClientService clientService;
	private Scanner sc;
	
	public RechercheClientParPrenomCommand(Scanner sc, ClientService clientService) {
		this.sc = sc;
		this.clientService=clientService;
	}

	@Override
	public int getInt() {
		return 5;
	}

	@Override
	public String getLibelle() {
		return "Rechercher une personne suivant son prénom";
	}

	@Override
	public void execute() {
		System.out.println("Entrez un prénom");
		List<Client> clients=clientService.getClientByName(sc.nextLine());
		for (Client client :clients) {
			System.out.println(client);
		}
//		sc1.close();
	}

}
