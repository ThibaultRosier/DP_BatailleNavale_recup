/*
 * Decompiled with CFR 0_132.
 */
package model.server.batiment;

import model.server.batiment.Batiment;

public class MoyenBatiment
extends Batiment {
    public MoyenBatiment(int nbVie, int nbTir) {
        super(nbTir, nbVie, 3);
    }

    public MoyenBatiment(MoyenBatiment mb) {
        super(mb);
    }
}

