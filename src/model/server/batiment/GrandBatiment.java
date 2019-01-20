/*
 * Decompiled with CFR 0_132.
 */
package model.server.batiment;

import model.server.batiment.Batiment;

public class GrandBatiment
extends Batiment {
    public GrandBatiment(int nbVie, int nbTir) {
        super(nbTir, nbVie, 5);
    }

    public GrandBatiment(GrandBatiment gb) {
        super(gb);
    }
}

