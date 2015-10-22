package com.bankonet;

import java.util.Map;
import java.util.Scanner;

public class Main {
	
	private static Client client;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Bonjour,");
		System.out.println("veuillez saisir vos identifiants");
		System.out.println("login : ");
		Scanner sc = new Scanner(System.in);
        String login=sc.nextLine();
		System.out.println("mot de passe : ");
		String password=sc.nextLine();
		
		
		
		try {
			client=Client.connect(login,password);
			menu();} 
		catch (ClientNonTrouveException e) {
			e.getMessage();
		}
		
	}

	private static void menu() {
		// TODO Auto-generated method stub
		
		
		System.out.println("****** APPLICATION CLIENT ******");
		System.out.println("0. Quitter l'application");
		System.out.println("1. Consulter les soldes des comptes");
		System.out.println("2. Effectuer un dépot");
		Scanner sc = new Scanner(System.in);
		int choix=sc.nextInt();
		
		switch (choix) {
			case 1:
				client.consulterSoldes();
				menu();
			break;
			
			case 2:
				depot();
			break;
	
			default:
				close();
			break;
		}
		
		sc.close();
	}

	private static void depot() {

		System.out.println("Choisissez le compte à créditer : ");
		client.consulterSoldes();
		Scanner sc = new Scanner(System.in);
		int choix=sc.nextInt();
		System.out.println("Choisissez un montant");
		double montant=sc.nextDouble();
		
		/*
		 * 
		 * Je me suis arrêté là, j'essayai de convertir mon hashmap en Compte[] mais j'ai pas compris comment ça marchait.
		 * Elles sont vraiment pas top les classes Java
		Map<String,Compte> mapClient=client.getListeComptes();
		
		Compte[] a=(Compte[]) mapClient.values().toArray();
		Compte compte=a[choix-1];
		
		try {
			compte.crediter(montant);
		} catch (CreditException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		sc.close();
		menu();
	}

	private static void close() {
		// TODO Auto-generated method stub
		System.out.println("Arret de l'application");
	}

}
