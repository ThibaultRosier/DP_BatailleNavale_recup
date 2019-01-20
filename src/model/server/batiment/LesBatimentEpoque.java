/*
 * Decompiled with CFR 0_132.
 */
package model.server.batiment;

import model.server.batiment.GrandBatiment;
import model.server.batiment.MoyenBatiment;
import model.server.batiment.PetitBatiment;

public abstract class LesBatimentEpoque {
    private GrandBatiment grandBatiment;
    private MoyenBatiment moyenBatiment;
    private PetitBatiment petitBatiment;

    public LesBatimentEpoque(GrandBatiment gb, MoyenBatiment mb, PetitBatiment pb) {
        this.grandBatiment = gb;
        this.moyenBatiment = mb;
        this.petitBatiment = pb;
    }

    public GrandBatiment getGrandBatiment() {
        return this.grandBatiment;
    }

    public MoyenBatiment getMoyenBatiment() {
        return this.moyenBatiment;
    }

    public PetitBatiment getPetitBatiment() {
        return this.petitBatiment;
    }
}

