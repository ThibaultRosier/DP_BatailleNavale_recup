/*
 * Decompiled with CFR 0_132.
 */
package model.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import model.server.Joueur;
import model.server.batiment.Batiment;
import model.server.batiment.GrandBatiment;
import model.server.batiment.LesBatimentEpoque;
import model.server.batiment.MoyenBatiment;
import model.server.batiment.PetitBatiment;
import model.service.Case;

public class Camp
implements Serializable {
    public static final int LARGEUR_CAMP = 10;
    public static final int HAUTEUR_CAMP = 10;
    private static final int NOMBRE_GRAND = 1;
    private static final int NOMBRE_MOYEN = 2;
    private static final int NOMBRE_PETIT = 3;
    private Case[][] camp;
    private Random random;
    private Joueur joueur;

    public Camp(Joueur j, LesBatimentEpoque lbE) {
        int i;
        GrandBatiment gb = lbE.getGrandBatiment();
        MoyenBatiment mb = lbE.getMoyenBatiment();
        PetitBatiment pb = lbE.getPetitBatiment();
        this.joueur = j;
        this.random = new Random();
        ArrayList<Batiment> lesBatiments = new ArrayList<Batiment>();
        for (i = 0; i < 1; ++i) {
            lesBatiments.add(new GrandBatiment(gb));
        }
        for (i = 0; i < 2; ++i) {
            lesBatiments.add(new MoyenBatiment(mb));
        }
        for (i = 0; i < 3; ++i) {
            lesBatiments.add(new PetitBatiment(pb));
        }
        this.remplirCamp(lesBatiments);
    }

    private void remplirCamp(ArrayList<Batiment> lesBatiments) {
        boolean placer = false;
        this.camp = new Case[10][10];
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                this.camp[i][j] = new Case(j, i);
            }
        }
        while (!lesBatiments.isEmpty()) {
            placer = false;
            Batiment batiActu = lesBatiments.get(0);
            lesBatiments.remove(0);
            batiActu.viderCase();
            while (!placer) {
                int direction = this.random.nextInt(2);
                int x = this.random.nextInt(10);
                int y = this.random.nextInt(10);
                if (direction == 0) {
                    placer = this.mettreBatimentHorizontal(this.camp[y][x], batiActu);
                    continue;
                }
                placer = this.mettreBatimentVertical(this.camp[y][x], batiActu);
            }
        }
    }

    private boolean mettreBatimentHorizontal(Case c, Batiment b) {
        int x = c.getX();
        int y = c.getY();
        if (x + b.getTaille() < 10) {
            int i;
            for (i = x; i < x + b.getTaille(); ++i) {
                if (this.camp[y][i].getBatiment() == null) continue;
                return false;
            }
            b.mettreHorizontal();
            b.setDebutBatiment(c);
            for (i = x; i < x + b.getTaille(); ++i) {
                this.camp[y][i].setBatiment(b);
            }
            b.chargerTire(this.joueur);
            return true;
        }
        return false;
    }

    private boolean mettreBatimentVertical(Case c, Batiment b) {
        int x = c.getX();
        int y = c.getY();
        if (y + b.getTaille() < 10) {
            int i;
            for (i = y; i < y + b.getTaille(); ++i) {
                if (this.camp[i][x].getBatiment() == null) continue;
                return false;
            }
            b.mettreVertical();
            b.setDebutBatiment(c);
            for (i = y; i < y + b.getTaille(); ++i) {
                this.camp[i][x].setBatiment(b);
            }
            b.chargerTire(this.joueur);
            return true;
        }
        return false;
    }

    public Case[][] getCamp() {
        return this.camp;
    }
}

