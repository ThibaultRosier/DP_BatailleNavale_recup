/*
 * Decompiled with CFR 0_132.
 */
package model.server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;
import model.server.Joueur;
import model.server.Partie;
import model.server.batiment.Batiment;
import model.service.Case;

public class IA
extends Joueur {
    private Random r = new Random();
    private Case derniereCase = null;
    private Case premiereCase = null;

    protected IA() throws RemoteException {
    }

    public void jouerIA(Case[][] campAdverse) {
        switch (Partie.TYPETIR) {
            case "Aleatoire": {
                this.strategieAleatoire(campAdverse);
                break;
            }
            case "Tir en Croix": {
                this.strategieEnCroix(campAdverse);
            }
        }
    }

    public void strategieAleatoire(Case[][] campAdverse) {
        ArrayList<Case> lesCaseDispo = new ArrayList<Case>();
        int taille1 = campAdverse.length;
        int taille2 = campAdverse[0].length;
        for (int i = 0; i < taille1; ++i) {
            for (int j = 0; j < taille2; ++j) {
                if (campAdverse[i][j].getToucher()) continue;
                lesCaseDispo.add(campAdverse[i][j]);
            }
        }
        int choix = this.r.nextInt(lesCaseDispo.size());
        Partie.caseSelection = (Case)lesCaseDispo.get(choix);
    }

    public void strategieEnCroix(Case[][] campAdverse) {
        int choix;
        ArrayList<Case> lesCaseDispo = new ArrayList<Case>();
        if (this.premiereCase != null && this.premiereCase.getBatiment().getCouler()) {
            this.premiereCase = null;
            this.derniereCase = null;
        }
        if (this.premiereCase == null) {
            int taille1 = campAdverse.length;
            int taille2 = campAdverse[0].length;
            for (int i = 0; i < taille1; ++i) {
                for (int j = 0; j < taille2; ++j) {
                    if (campAdverse[i][j].getToucher()) continue;
                    lesCaseDispo.add(campAdverse[i][j]);
                }
            }
        } else {
            while (lesCaseDispo.size() == 0) {
                int x = this.derniereCase.getX();
                int y = this.derniereCase.getY();
                if (y > 0 && !campAdverse[y - 1][x].getToucher()) {
                    lesCaseDispo.add(campAdverse[y - 1][x]);
                }
                if (y < 9 && !campAdverse[y + 1][x].getToucher()) {
                    lesCaseDispo.add(campAdverse[y + 1][x]);
                }
                if (x > 0 && !campAdverse[y][x - 1].getToucher()) {
                    lesCaseDispo.add(campAdverse[y][x - 1]);
                }
                if (x < 9 && !campAdverse[y][x + 1].getToucher()) {
                    lesCaseDispo.add(campAdverse[y][x + 1]);
                }
                if (lesCaseDispo.size() != 0) continue;
                this.derniereCase = this.premiereCase;
            }
        }
        if ((Partie.caseSelection = (Case)lesCaseDispo.get(choix = this.r.nextInt(lesCaseDispo.size()))).getBatiment() != null) {
            if (this.premiereCase == null) {
                this.premiereCase = Partie.caseSelection;
            }
            this.derniereCase = Partie.caseSelection;
        }
    }
}

