/*
 * Decompiled with CFR 0_132.
 */
package model.service;

import java.io.Serializable;
import model.server.batiment.Batiment;

public class Case
implements Serializable {
    private Batiment batiment;
    private boolean toucher;
    private int x;
    private int y;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.toucher = false;
        this.batiment = null;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
        batiment.ajouterCase(this);
    }

    public Batiment getBatiment() {
        return this.batiment;
    }

    public void toucher() {
        this.toucher = true;
        if (this.batiment != null) {
            this.batiment.toucher();
        }
    }

    public boolean getToucher() {
        return this.toucher;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean estUneCaseDebutBatiment() {
        Case c;
        if (this.batiment != null && (c = this.batiment.getDebutBatiment()) == this) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}

