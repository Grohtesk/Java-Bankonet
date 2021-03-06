package command;

import java.util.Scanner;

import bankonet.metier.ClientService;

public class OuvrirCompteCourrantCommand extends IhmCommand {
	
	Scanner sc;
	private ClientService clientService;

	public OuvrirCompteCourrantCommand(Scanner sc,ClientService clientService) {
		this.sc=sc;
		this.clientService = clientService;
	}

	@Override
	public int getInt() {
		return 1;
	}

	@Override
	public String getLibelle() {
		return "Cr�ation d'un client avec un compte courrant";
	}

	@Override
	public void execute() {
		System.out.println(this.getLibelle());
		System.out.println("nom du client :");
		String nom=sc.nextLine();
		System.out.println("Pr�nom :");
		String prenom=sc.nextLine();
		System.out.println("Identifiant :");
		String identifiant=sc.nextLine();
		clientService.ajouterClient(identifiant, nom, prenom, "secret");
		
	}
	
	

}
