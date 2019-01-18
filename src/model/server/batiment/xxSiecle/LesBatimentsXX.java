package model.server.batiment.xxSiecle;

import model.server.batiment.LesBatimentEpoque;
import model.server.batiment.xviSiecle.Flute;
import model.server.batiment.xviSiecle.Gabare;
import model.server.batiment.xviSiecle.Galions;

public class LesBatimentsXX extends LesBatimentEpoque {

    public LesBatimentsXX(){
        super(new Croiseur(),new Destroyer(),new LandingShipDock());
    }
}
