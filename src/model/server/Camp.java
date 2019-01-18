package model.server;

import model.server.batiment.*;
import model.service.Case;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

public class Camp extends UnicastRemoteObject {

    public final static int LARGEUR_CAMP = 10;
    public final static int HAUTEUR_CAMP = 10;

    private final static int NOMBRE_GRAND = 1;
    private final static int NOMBRE_MOYEN = 2;
    private final static int NOMBRE_PETIT = 3;

    private Case[][] camp;
    private Random random ;
    private Joueur joueur;


    public Camp(Joueur j, LesBatimentEpoque lbE) throws RemoteException {
        super();
        GrandBatiment gb; MoyenBatiment mb ; PetitBatiment pb;
        gb = lbE.getGrandBatiment();
        mb = lbE.getMoyenBatiment();
        pb = lbE.getPetitBatiment();
        joueur = j;
        random = new Random();
        ArrayList<Batiment> lesBatiments = new ArrayList<Batiment>();
        for(int i = 0; i < NOMBRE_GRAND ; i++){
            lesBatiments.add(new Batiment(gb));
        }

        for(int i = 0; i < NOMBRE_MOYEN ; i++){
            lesBatiments.add(new Batiment(mb));
        }

        for(int i = 0; i < NOMBRE_PETIT ; i++){
            lesBatiments.add(new Batiment(pb));
        }

        remplirCamp(lesBatiments);
    }


    private  void remplirCamp(ArrayList<Batiment> lesBatiments ){
        boolean placer = false;
        int direction,x,y;
        camp = new Case[HAUTEUR_CAMP][LARGEUR_CAMP];
        for(int i = 0; i < HAUTEUR_CAMP ; i++){
            for(int j = 0; j < LARGEUR_CAMP ; j++){
                camp[i][j] = new Case(j,i);
            }
        }
        Batiment batiActu ;
        while(!lesBatiments.isEmpty()){
            placer = false;
            batiActu = lesBatiments.get(0);
            lesBatiments.remove(0);
            batiActu.viderCase();
            while (!placer) {
                direction = random.nextInt(2);
                x = random.nextInt(LARGEUR_CAMP);
                y = random.nextInt(HAUTEUR_CAMP);
                if (direction == 0) {
                    placer = mettreBatimentHorizontal(camp[y][x],batiActu);
                } else {
                    placer = mettreBatimentVertical(camp[y][x],batiActu);
                }
            }
        }


    }



    private boolean mettreBatimentHorizontal(Case c,Batiment b){
        int x = c.getX();
        int y = c.getY();
        if(x + b.getTaille() < LARGEUR_CAMP){
            for(int i = x; i < x+b.getTaille();i++){
                if(camp[y][i].getBatiment() != null){
                    return false;
                }
            }
            b.mettreHorizontal();
            b.setDebutBatiment(c);
            for(int i = x; i < x+b.getTaille();i++){
                camp[y][i].setBatiment(b);
            }
            b.chargerTire(joueur);
            return true;
        }
        return false;
    }

    private boolean mettreBatimentVertical(Case c,Batiment b){
        int x = c.getX();
        int y = c.getY();
        if(y + b.getTaille() < HAUTEUR_CAMP){
            for(int i = y; i < y+b.getTaille();i++){
                if(camp[i][x].getBatiment() != null){
                    return false;
                }
            }
            b.mettreVertical();
            b.setDebutBatiment(c);
            for(int i = y; i < y+b.getTaille();i++){
                camp[i][x].setBatiment(b);
            }
            b.chargerTire(joueur);
            return true;
        }
        return false;
    }

    public Case[][] getCamp() {
        return camp;
    }


}
