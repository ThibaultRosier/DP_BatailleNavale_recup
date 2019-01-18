/*
 * Decompiled with CFR 0_132.
 */
package model.client;

import model.server.Partie;
import model.service.IPartie;

public class TestClient
implements IPartie {
    public static void main(String[] args) throws Exception {
        String url = "rmi://annuaire-RMI/partieFactory";
        Partie c = Partie.getPartieEnCour();
    }
}

