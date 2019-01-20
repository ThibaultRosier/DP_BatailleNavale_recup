/*
 * Decompiled with CFR 0_132.
 */
package model.server.batiment;

import model.server.batiment.Batiment;

public class PetitBatiment
extends Batiment {
    public PetitBatiment(int nbVie, int nbTir) {
        super(nbTir, nbVie, 2);
    }

    public PetitBatiment(PetitBatiment pb) {
        super(pb);
    }
}

