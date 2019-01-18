package model.service;

import model.server.batiment.Batiment;

import java.io.Serializable;

public class Case implements Serializable {

	private Batiment batiment;
	private boolean toucher;
	private int x;
	private int y;

	public Case(int x, int y){
		this.x = x;
		this.y = y;
		toucher = false;
		batiment = null;
	}

	public void setBatiment(Batiment batiment){
		this.batiment = batiment;
		batiment.ajouterCase(this);
	}

	public Batiment getBatiment() {
		return batiment;
	}

	public void toucher(){
		toucher = true;
		if(batiment != null){
			batiment.toucher();
		}
	}

	public boolean getToucher(){
		return toucher;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	public boolean estUneCaseDebutBatiment(){
		if(batiment != null){
			Case c = batiment.getDebutBatiment();
			if(c == this){
				return  true;
			}
		}
		return false;
	}

	public String toString(){
		return "("+x+","+y+")";
	}

}
