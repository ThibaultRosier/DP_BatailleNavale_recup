package model.server;

import model.server.batiment.Batiment;
import model.service.Case;
import model.service.IPartie;
import vue.Vue;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Partie extends UnicastRemoteObject implements IPartie,Serializable{

	private String nom;
	private Date date;

	public final static String NORMAL = "Normal";
	public final static String MASTER = "Master";


	public final static String XX = "XX Siecle";
	public final static String XVI = "XVI Siecle";

	public static String[] tabEpoque = {"XX Siecle","XVI Siecle"};;
	public static String[] tabTypePartie = {"Normal","Master"};;
	public static String[] tabTireOrdi = {"super Con","tres con","con", "pas trop con","moyennement intelligent","thibault rosier"};
	public static String EPOQUE = "";
	public static String TYPEPARTIE = "";

	private Joueur joueur1;
	private IA joueur2;

	public static Case caseSelection;
	public static Batiment batimentSelection;

	private static Partie partieEnCour = null;

	private ArrayList<Vue> lesVue;

	private Partie(String epoque, String type_Partie) throws RemoteException {
		super();
        lesVue = new ArrayList<Vue>();
		if(type_Partie.equals(NORMAL) || type_Partie.equals(MASTER)) {
			joueur2 = new IA();
            joueur1 = new Joueur();
		}else{
			assert(false):"Le type de partie n'est pas definie la partie ne peut donc etre cree";
		}
	}

	public static Partie getPartieEnCour() throws RemoteException {
		if( partieEnCour == null ){
			partieEnCour = new Partie(EPOQUE, TYPEPARTIE);
		}
		return partieEnCour;
	}

	public static void setPartieEnCour(Partie p){
		partieEnCour = p;
	}


	public void ajouterVue(Vue v){
	    lesVue.add(v);
    }

    public void update(){
	    for(Vue v : lesVue){
	        v.update();
        }
    }

	public void tireMaster(){
		assert (batimentSelection != null):" Aucun batiment selectionner le tire ne peut ce faire";
		assert (caseSelection != null):"Aucunnne case cibler le tire ne peut ce faire";

		if(batimentSelection.getTirRestant() > 0) {
            batimentSelection.tir();
            caseSelection.toucher();
		}
        update();
	}

	public void tireNormal(){
		assert (caseSelection != null):"Aucunnne case cibler le tire ne peut ce faire";

        caseSelection.toucher();
		tireNormalOrdi();
        update();
	}

	private void tireNormalOrdi(){
		joueur2.strategieEnCroix(joueur1.getCampJoueur().getCamp());
		assert (caseSelection != null):"Aucunnne case cibler le tire ne peut ce faire";
		caseSelection.toucher();
	}

	public void deSelection(){
        caseSelection = null;
        batimentSelection = null;
    }
	public Joueur getGagnantNormal() {
		if(joueur1.getNbBatiments() <= 0){
			return joueur2;
		}else {
			if (joueur2.getNbBatiments() <= 0) {
				return  joueur1;
			}
		}

		return null;
	}


	public Joueur getGagnant() {
		if(joueur1.getNombreTireRestant() <= 0){
            update();
			return joueur2;
		}else {
			if (joueur2.getNombreTireRestant() <= 0) {
                update();
				return  joueur1;
			}
		}

		return null;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public IA getJoueur2() {
		return joueur2;
	}

	public Camp getCampJoueur1(){
	    return joueur1.getCampJoueur();
    }

    public Camp getCampJoueur2(){
        return joueur2.getCampJoueur();
    }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return nom+"        "+EPOQUE+"      "+TYPEPARTIE+"        "+ new SimpleDateFormat("dd-MM-yyyy        HH:mm:ss").format(date);

	}

	public void serialize(String name) throws IOException {

		File fichier =  new File("./src/fichier_sauvegarde/"+name+".save") ;

		// ouverture d'un flux sur un fichier
		ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(fichier)) ;

		// sérialization de l'objet
		oos.writeObject(this) ;
	}

	public static Partie deSerialize(String name) throws IOException, ClassNotFoundException {

		// on simplifie le code en retirant la gestion des exceptions
		File fichier =  new File("./src/fichier_sauvegarde/"+name+".save") ;

		// ouverture d'un flux sur un fichier
		ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier)) ;

		// désérialization de l'objet
		return (Partie) ois.readObject() ;
		//System.out.println(m) ;

	}

	/*public String toString(){
		String string = "";

		string += joueur1.toString();

		for(int j = 0; j < Camp.LARGEUR_CAMP ; j++){
			string+="-";
		}
		string+="\n";

		string += joueur2.toString();

		return string;
	}*/

	public static void main(String [] args) throws RemoteException {
		try {
			// create a RMI registry
			Registry r = LocateRegistry.createRegistry(1099);

			// create and publish car factory server object
			Partie partieFactory = new Partie("XX Siecle","NORMAL");
			r.rebind("partie", partieFactory);
			System.err.println("CarFactoryServer is running.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
