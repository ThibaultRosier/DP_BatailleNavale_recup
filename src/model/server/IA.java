package model.server;

import model.service.Case;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

public class IA extends Joueur {

	private Random r;
	private Case derniereCase = null;
	private Case premiereCase = null;

	protected IA() throws RemoteException {
		r = new Random();
	}

	public void strategieAleatoire(Case[][] campAdverse){
		ArrayList<Case> lesCaseDispo = new ArrayList<Case>();
		int taille1 = campAdverse.length;
		int taille2 = campAdverse[0].length;
		for(int i = 0 ;  i < taille1 ; i++){
			for(int j = 0 ;  j < taille2 ; j++){
				if(!campAdverse[i][j].getToucher()) {
					lesCaseDispo.add(campAdverse[i][j]);
				}
			}
		}

		int choix = r.nextInt(lesCaseDispo.size());
		Partie.caseSelection = lesCaseDispo.get(choix);
		
	}

	public void strategieEnCroix(Case[][] campAdverse){
		int choix;
		ArrayList<Case> lesCaseDispo = new ArrayList<Case>();
		if(premiereCase != null && premiereCase.getBatiment().getCouler()){
			premiereCase = null;
			derniereCase = null;
		}
		if(premiereCase == null) {

			int taille1 = campAdverse.length;
			int taille2 = campAdverse[0].length;
			for (int i = 0; i < taille1; i++) {
				for (int j = 0; j < taille2; j++) {
					if (!campAdverse[i][j].getToucher()) {
						lesCaseDispo.add(campAdverse[i][j]);
					}
				}
			}

		}else{
			while(lesCaseDispo.size() == 0) {
				int x = derniereCase.getX();
				int y = derniereCase.getY();
				if (y > 0) {
					if (!campAdverse[y - 1][x].getToucher()) {
						lesCaseDispo.add(campAdverse[y - 1][x]);
					}
				}
				if (y < Camp.HAUTEUR_CAMP - 1) {
					if (!campAdverse[y + 1][x].getToucher()) {
						lesCaseDispo.add(campAdverse[y + 1][x]);
					}
				}
				if (x > 0) {
					if (!campAdverse[y][x - 1].getToucher()) {
						lesCaseDispo.add(campAdverse[y][x - 1]);
					}
				}
				if (x < Camp.LARGEUR_CAMP - 1) {
					if (!campAdverse[y][x + 1].getToucher()) {
						lesCaseDispo.add(campAdverse[y][x + 1]);
					}
				}

				if (lesCaseDispo.size() == 0) {
					derniereCase = premiereCase;
				}
			}
		}

		choix = r.nextInt(lesCaseDispo.size());
		Partie.caseSelection = lesCaseDispo.get(choix);
		if(Partie.caseSelection.getBatiment() != null){
			if(premiereCase == null){
				premiereCase = Partie.caseSelection;;
			}
			derniereCase = Partie.caseSelection;
		}
	}

}
