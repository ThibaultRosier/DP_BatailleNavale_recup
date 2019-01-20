/*
 * Decompiled with CFR 0_132.
 */
package model.server.batiment.xxSiecle;

import model.server.batiment.GrandBatiment;
import model.server.batiment.LesBatimentEpoque;
import model.server.batiment.MoyenBatiment;
import model.server.batiment.PetitBatiment;
import model.server.batiment.xxSiecle.Croiseur;
import model.server.batiment.xxSiecle.Destroyer;
import model.server.batiment.xxSiecle.LandingShipDock;

public class LesBatimentsXX
extends LesBatimentEpoque {
    public LesBatimentsXX() {
        super(new Croiseur(), new Destroyer(), new LandingShipDock());
    }
}

