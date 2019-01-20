/*
 * Decompiled with CFR 0_132.
 */
package model.server.batiment;

import java.io.Serializable;
import java.util.ArrayList;
import model.server.Joueur;
import model.service.Case;

public class Batiment
implements Cloneable,
Serializable {
    protected int tirRestant;
    protected int nbTire;
    protected int taille;
    protected boolean couler;
    protected int nbVie;
    protected Joueur joueur;
    protected Case debutBatiment = null;
    protected boolean vertical;
    protected ArrayList<Case> lesCaseDuBatiment;

    public Batiment(int nbTire, int nbVie, int taille) {
        assert (nbVie <= taille);
        assert (nbVie <= nbTire);
        this.debutBatiment = null;
        this.joueur = null;
        this.couler = false;
        this.nbVie = nbVie;
        this.nbTire = nbTire;
        this.taille = taille;
        this.debutBatiment = null;
        this.vertical = false;
        this.tirRestant = nbTire;
        this.lesCaseDuBatiment = new ArrayList();
    }

    public Batiment(Batiment b) {
        this.debutBatiment = null;
        this.joueur = null;
        this.couler = false;
        this.nbVie = b.nbVie;
        this.nbTire = b.nbTire;
        this.taille = b.taille;
        this.debutBatiment = null;
        this.vertical = false;
        this.tirRestant = b.nbTire;
        this.lesCaseDuBatiment = new ArrayList();
    }

    public void viderCase() {
        this.lesCaseDuBatiment.clear();
    }

    public void ajouterCase(Case c) {
        this.lesCaseDuBatiment.add(c);
    }

    public void chargerTire(Joueur j) {
        this.joueur = j;
        j.ajouterTire(this.nbTire);
        j.ajouterBatiment();
    }

    public void toucher() {
        --this.nbVie;
        if (this.nbVie <= 0) {
            if (!this.couler) {
                this.joueur.enleverBatiment();
            }
            this.couler = true;
            this.joueur.enleverTir(this.tirRestant);
            this.couler();
        }
    }

    private void couler() {
        for (Case c : this.lesCaseDuBatiment) {
            if (c.getToucher()) continue;
            c.toucher();
        }
    }

    public void tir() {
        this.joueur.enleverTir(1);
        --this.tirRestant;
    }

    public int getTaille() {
        return this.taille;
    }

    public boolean getCouler() {
        return this.couler;
    }

    public int getTirRestant() {
        return this.tirRestant;
    }

    public void setDebutBatiment(Case c) {
        this.debutBatiment = c;
    }

    public void mettreVertical() {
        this.vertical = true;
    }

    public void mettreHorizontal() {
        this.vertical = false;
    }

    public Case getDebutBatiment() {
        return this.debutBatiment;
    }

    public String toString() {
        String aRenvoyer = "";
        aRenvoyer = aRenvoyer + "  taille  : " + this.taille + "  Case   -> ";
        for (Case c : this.lesCaseDuBatiment) {
            aRenvoyer = aRenvoyer + c.toString() + " - ";
        }
        return aRenvoyer;
    }
}

