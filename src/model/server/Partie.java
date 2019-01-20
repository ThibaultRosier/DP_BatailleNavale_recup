/*
 * Decompiled with CFR 0_132.
 */
package model.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.server.Camp;
import model.server.IA;
import model.server.Joueur;
import model.server.batiment.Batiment;
import model.service.Case;
import vue.Vue;

public class Partie
implements Serializable {
    private String nom;
    private Date date;
    public static final String NORMAL = "Normal";
    public static final String MASTER = "Master";
    public static final String XX = "XX Siecle";
    public static final String XVI = "XVI Siecle";
    public static String[] tabEpoque = new String[]{"XX Siecle", "XVI Siecle"};
    public static String[] tabTypePartie = new String[]{"Normal", "Master"};
    public static String[] tabTireOrdi = new String[]{"Aleatoire", "Tir en Croix"};
    public static String EPOQUE = "";
    public static String TYPEPARTIE = "";
    public static String TYPETIR = "";
    public static final String ALEA = "Aleatoire";
    public static final String CROIX = "Tir en Croix";
    private Joueur joueur1;
    private IA joueur2;
    public static Case caseSelection;
    public static Batiment batimentSelection;
    private static Partie partieEnCour;
    private ArrayList<Vue> lesVue = new ArrayList();

    private Partie(String epoque, String type_Partie) throws RemoteException {
        if (type_Partie.equals(NORMAL) || type_Partie.equals(MASTER)) {
            this.joueur2 = new IA();
            this.joueur1 = new Joueur();
        } else assert (false);
    }

    public static Partie getPartieEnCour() throws RemoteException {
        if (partieEnCour == null) {
            partieEnCour = new Partie(EPOQUE, TYPEPARTIE);
        }
        return partieEnCour;
    }

    public static void setPartieEnCour(Partie p) {
        partieEnCour = p;
    }

    public void ajouterVue(Vue v) {
        this.lesVue.add(v);
    }

    public void update() {
        for (Vue v : this.lesVue) {
            v.update();
        }
    }

    public void tireMaster() {
        assert (batimentSelection != null);
        assert (caseSelection != null);
        if (batimentSelection.getTirRestant() > 0) {
            batimentSelection.tir();
            caseSelection.toucher();
        }
        this.update();
    }

    public void tireNormal() {
        assert (caseSelection != null);
        caseSelection.toucher();
        this.tireNormalOrdi();
        this.update();
    }

    private void tireNormalOrdi() {
        this.joueur2.jouerIA(this.joueur1.getCampJoueur().getCamp());
        assert (caseSelection != null);
        caseSelection.toucher();
    }

    public void deSelection() {
        caseSelection = null;
        batimentSelection = null;
    }

    public Joueur getGagnantNormal() {
        if (this.joueur1.getNbBatiments() <= 0) {
            return this.joueur2;
        }
        if (this.joueur2.getNbBatiments() <= 0) {
            return this.joueur1;
        }
        return null;
    }

    public Joueur getGagnant() {
        if (this.joueur1.getNombreTireRestant() <= 0) {
            this.update();
            return this.joueur2;
        }
        if (this.joueur2.getNombreTireRestant() <= 0) {
            this.update();
            return this.joueur1;
        }
        return null;
    }

    public Joueur getJoueur1() {
        return this.joueur1;
    }

    public IA getJoueur2() {
        return this.joueur2;
    }

    public Camp getCampJoueur1() {
        return this.joueur1.getCampJoueur();
    }

    public Camp getCampJoueur2() {
        return this.joueur2.getCampJoueur();
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return this.nom + "        " + EPOQUE + "      " + TYPEPARTIE + "        " + new SimpleDateFormat("dd-MM-yyyy        HH:mm:ss").format(this.date);
    }

    public void serialize(String name) throws IOException {
        File fichier = new File("./src/fichier_sauvegarde/" + name + ".save");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
        oos.writeObject(this);
    }

    public static Partie deSerialize(String name) throws IOException, ClassNotFoundException {
        File fichier = new File("./src/fichier_sauvegarde/" + name + ".save");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
        return (Partie)ois.readObject();
    }

    static {
        partieEnCour = null;
    }
}

