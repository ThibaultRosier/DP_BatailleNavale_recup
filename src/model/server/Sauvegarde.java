package model.server;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Sauvegarde implements Serializable{

    /*private String nom;
    private String epoque;
    private String typePartie;
    private Date date;

    public Sauvegarde(String nom, String epoque, String typePartie) {
        this.nom = nom;
        this.epoque = epoque;
        this.typePartie = typePartie;
        this.date = Calendar.getInstance().getTime();
    }

    public Sauvegarde(String nom) {
        this.nom = nom;
    }

    public Sauvegarde(Sauvegarde s) {
        this.nom = s.nom;
        this.epoque = s.epoque;
        this.typePartie = s.typePartie;
        this.date = Calendar.getInstance().getTime();
    }

    public String getNom() {
        return nom;
    }

    public String getEpoque() {
        return epoque;
    }

    public String getTypePartie() {
        return typePartie;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return nom+"        "+epoque+"      "+typePartie+"        "+ new SimpleDateFormat("dd-MM-yyyy        HH:mm:ss").format(date);

    }

    public void serialize(String name) throws IOException {

        File fichier =  new File("./src/fichier_sauvegarde/"+name+".save") ;

        // ouverture d'un flux sur un fichier
        ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(fichier)) ;

        // sérialization de l'objet
        oos.writeObject(this) ;
    }

    public static Sauvegarde deSerialize(String name) throws IOException, ClassNotFoundException {

        // on simplifie le code en retirant la gestion des exceptions
        File fichier =  new File("./src/fichier_sauvegarde/"+name+".save") ;

        // ouverture d'un flux sur un fichier
        ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier)) ;

        // désérialization de l'objet
        return (Sauvegarde) ois.readObject() ;
        //System.out.println(m) ;

    }*/


}
