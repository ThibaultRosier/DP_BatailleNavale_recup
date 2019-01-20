/*
 * Decompiled with CFR 0_132.
 */
package model.server;

import java.io.Serializable;
import java.rmi.RemoteException;
import model.server.Camp;
import model.server.Partie;
import model.server.batiment.Batiment;
import model.server.batiment.LesBatimentEpoque;
import model.server.batiment.xviSiecle.LesBatimentsXVI;
import model.server.batiment.xxSiecle.LesBatimentsXX;
import model.service.Case;

public class Joueur
implements Serializable {
    private Camp campJoueur;
    private int nombreTireRestant = 0;
    private int nbBatiment = 0;

    public Joueur() throws RemoteException {
        switch (Partie.EPOQUE) {
            case "XVI Siecle": {
                this.campJoueur = new Camp(this, new LesBatimentsXVI());
                break;
            }
            case "XX Siecle": {
                this.campJoueur = new Camp(this, new LesBatimentsXX());
            }
        }
    }

    public void ajouterBatiment() {
        ++this.nbBatiment;
    }

    public void enleverBatiment() {
        --this.nbBatiment;
    }

    public int getNombreTireRestant() {
        return this.nombreTireRestant;
    }

    public void enleverTir(int tirEnMoins) {
        this.nombreTireRestant -= tirEnMoins;
    }

    public void ajouterTire(int nbTire) {
        this.nombreTireRestant += nbTire;
    }

    public Camp getCampJoueur() {
        return this.campJoueur;
    }

    public String toString() {
        String string = "";
        Case[][] camp = this.campJoueur.getCamp();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (camp[i][j].getBatiment() != null) {
                    if (camp[i][j].estUneCaseDebutBatiment()) {
                        string = string + "D";
                        continue;
                    }
                    string = string + "B";
                    continue;
                }
                string = string + "|";
            }
            string = string + "\n";
        }
        return string;
    }

    public int getNbBatiments() {
        return this.nbBatiment;
    }
}

