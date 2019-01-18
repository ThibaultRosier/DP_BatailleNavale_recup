package model.server;

import model.server.Camp;


import model.server.batiment.xviSiecle.Flute;
import model.server.batiment.xviSiecle.Gabare;
import model.server.batiment.xviSiecle.Galions;
import model.server.batiment.xviSiecle.LesBatimentsXVI;
import model.server.batiment.xxSiecle.Croiseur;
import model.server.batiment.xxSiecle.Destroyer;
import model.server.batiment.xxSiecle.LandingShipDock;
import model.server.batiment.xxSiecle.LesBatimentsXX;
import model.service.Case;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Joueur extends UnicastRemoteObject implements Serializable{



    private Camp campJoueur;


    private int nombreTireRestant = 0;
    private int nbBatiment = 0;

    public Joueur() throws RemoteException {
        switch (Partie.EPOQUE){
            case Partie.XVI :
                campJoueur = new Camp(this,new LesBatimentsXVI());
                break;
            case Partie.XX:
                campJoueur = new Camp(this,new LesBatimentsXX());
                break;
        }

    }

    public void ajouterBatiment(){
        nbBatiment++;
    }

    public void enleverBatiment(){
        nbBatiment--;
    }

    public int getNombreTireRestant(){
        return nombreTireRestant;
    }

    public void enleverTir(int tirEnMoins){
        nombreTireRestant-=tirEnMoins;
    }

    public void ajouterTire(int nbTire){
        nombreTireRestant += nbTire;
    }


    public Camp getCampJoueur(){
        return campJoueur;
    }

    public int getNbBatiments(){
        return nbBatiment;
    }


    public String toString(){
        String string = "";
        Case[][]camp = campJoueur.getCamp();
        for(int i = 0; i < Camp.HAUTEUR_CAMP ; i++){
            for(int j = 0; j < Camp.LARGEUR_CAMP ; j++){
                if(camp[i][j].getBatiment() != null){
                    if(camp[i][j].estUneCaseDebutBatiment() ){
                        string += "D";
                    }else {
                        string += "B";
                    }
                }else{
                    string+="|";
                }
            }
            string+="\n";
        }
        return string;
    }
}
