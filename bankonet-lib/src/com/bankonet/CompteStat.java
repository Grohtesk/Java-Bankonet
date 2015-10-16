package com.bankonet;

public interface CompteStat {
	
	double getSolde();
	double debiter(double montant) throws CompteException;
	void effectuerVirement(Compte compte,double montant) throws CompteException;
	double crediter(double montant) throws CreditException;
}
