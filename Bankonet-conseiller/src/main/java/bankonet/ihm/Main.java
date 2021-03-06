package bankonet.ihm;
import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.bankonet.Client;
import com.bankonet.Compte;
import com.bankonet.CompteCourant;

import bankonet.dao.DaoFactory;
import bankonet.dao.DaoFactoryMySQL;
import bankonet.dao.client.ClientDao;
import bankonet.metier.ClientService;

import java.util.Scanner;

public class Main {
	

	static DaoFactory factory;
	static ClientService serviceClient;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		factory=new DaoFactoryMySQL();
		serviceClient=new ClientService(factory.getDaoClient());
		
		menu();

		
	}
	
	public static void menu() {
				
		System.out.println("****** APPLICATION CONSEILLER BANCAIRE ******");
		System.out.println("0. Arreter l'application");
		System.out.println("1. Ouvrir un compte courant");
		System.out.println("2. Lister tous les clients");
		System.out.println("");
		System.out.println("Veuillez choisir une option");
		
		Scanner sc = new Scanner(System.in);
        int choix=sc.nextInt();
//        sc.close();
        
        switch (choix) {
        
		case 0:
			sc.close();
			close();
			break;
			
		case 1:
			ouvrirCompteCourant();
			break;
		case 2:
			listeClient();
			break;

		default:
			break;
		}
	}

	private static void listeClient() {
		System.out.println("***** Liste des clients *****");
		
		ArrayList<Client> listClient=serviceClient.getClients();
		
		for (Client client : listClient) {
	    	System.out.println(client);
		}
		menu();
	}

	private static void ouvrirCompteCourant() {
		// TODO Auto-generated method stub
		
		System.out.println("***** Ouverture de compte courrant *****");
		
		System.out.println("Veuillez saisir le nom du client : ");
		Scanner sc = new Scanner(System.in);
        String nom=sc.nextLine();
        
        System.out.println("Son pr�nom : ");
        String prenom=sc.nextLine();

        System.out.println("Son identifiant : ");
        String identifiant=sc.nextLine();

        String password="secret";
        
        serviceClient.ajouterClient(identifiant,nom,prenom,password);
                
    	System.out.println("Le client a bien �t� cr��");
        
        sc.close();
		menu();
	}

	private static void close() {
		// TODO Auto-generated method stub
		return;
	}

}
