package command;

import java.util.List;
import java.util.Scanner;

import com.bankonet.Client;

import bankonet.metier.ClientService;

public class RechercheClientParNomCommand extends IhmCommand {

	ClientService clientService;
	private Scanner sc;
	
	public RechercheClientParNomCommand(Scanner sc, ClientService clientService) {
		this.sc = sc;
		this.clientService=clientService;
	}

	@Override
	public int getInt() {
		return 4;
	}

	@Override
	public String getLibelle() {
		return "Rechercher une personne suivant son nom";
	}

	@Override
	public void execute() {
		System.out.println("Entrez un nom");
		List<Client> clients=clientService.getClientByName(sc.nextLine());
		for (Client client :clients) {
			System.out.println(client);
		}
//		sc1.close();
	}


}
