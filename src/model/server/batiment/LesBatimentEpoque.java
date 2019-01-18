package model.server.batiment;

public abstract class LesBatimentEpoque {

    private GrandBatiment grandBatiment;
    private MoyenBatiment moyenBatiment;
    private PetitBatiment petitBatiment;

    public LesBatimentEpoque(GrandBatiment gb,MoyenBatiment mb,PetitBatiment pb){
        grandBatiment = gb;
        moyenBatiment = mb;
        petitBatiment = pb;
    }

    public GrandBatiment getGrandBatiment(){
        return grandBatiment;
    }

    public MoyenBatiment getMoyenBatiment(){
        return moyenBatiment;
    }

    public PetitBatiment getPetitBatiment(){
        return petitBatiment;
    }

}
