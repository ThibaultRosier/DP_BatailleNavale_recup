package model.server.batiment;

import model.service.Case;
import model.server.*;

import java.io.Serializable;
import java.util.ArrayList;

public  class Batiment implements Cloneable,Serializable {
	
	protected int tirRestant;
	protected int nbTire;
	
	protected int taille;

	protected boolean couler;
	
	protected int nbVie;//combien de fois il faut le toucher avant qu'il coule

	protected  Joueur joueur;

	protected Case debutBatiment = null;
	protected boolean vertical;
	protected ArrayList<Case> lesCaseDuBatiment;


	public Batiment(int nbTire,int nbVie,int taille){
		assert (nbVie <= taille):"Le nombre de vie d'un batiment ne peut etre superieur a sa taille probleme pour les batiment de type "+this.getClass().getName()+"ici le nombre de vie ne peut etre supperieur a "+taille;
		assert (nbVie <= nbTire):"Le nombre de vie d'un batiment ne peut etre superieur a son nombre de tire "+this.getClass().getName()+" ici le nombre de vie ne peut etre supperieur a "+nbTire;
		debutBatiment = null;
		this.joueur = null;
		couler = false;
		this.nbVie = nbVie;
		this.nbTire = nbTire;
		this.taille = taille;
        this.debutBatiment = null;
        this.vertical = false;
		tirRestant = nbTire;
		lesCaseDuBatiment = new ArrayList<Case>();
	}

	public Batiment(Batiment b){
		debutBatiment = null;
		this.joueur = null;
		couler = false;
		this.nbVie = b.nbVie;
		this.nbTire = b.nbTire;
		this.taille = b.taille;
		this.debutBatiment = null;
		this.vertical = false;
		tirRestant = b.nbTire;
		lesCaseDuBatiment = new ArrayList<Case>();
	}



    public void viderCase(){
		lesCaseDuBatiment.clear();
	}

    public void ajouterCase(Case c){
		lesCaseDuBatiment.add(c);
	}

	public void chargerTire(Joueur j){
		joueur = j;
		j.ajouterTire(nbTire);
		j.ajouterBatiment();
	}

	public void toucher(){
		nbVie--;
		if(nbVie <= 0){
			couler = true;
			joueur.enleverTir(tirRestant);
			joueur.enleverBatiment();
			couler();
		}
	}

	private void couler(){
		for(Case c : lesCaseDuBatiment){
			if(!c.getToucher()) {
				c.toucher();
			}
		}
	}

	public void tir(){
		joueur.enleverTir(1);
		tirRestant--;
	}

	public int getTaille() {
		return taille;
	}

	public boolean getCouler(){
		return couler;
	}

	public int getTirRestant() {
		return tirRestant;
	}

	public void setDebutBatiment(Case c){
		debutBatiment = c;
	}

	public void mettreVertical(){
		vertical = true;
	}

	public void mettreHorizontal(){
		vertical = false;
	}

	public Case getDebutBatiment(){
		return  debutBatiment;
	}

	public String toString(){
		String aRenvoyer ="";

		aRenvoyer += "  taille  : "+ taille +"  Case   -> ";
		for(Case c : lesCaseDuBatiment){
			aRenvoyer += c.toString()+" - ";
		}

		return aRenvoyer;
	}
}
