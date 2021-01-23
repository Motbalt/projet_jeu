public class Villageois extends Personnage{
    private static final String NOMCLASSE = "Villageois";

    public Villageois(String nom){
        super(nom, "Villageois");
    }

    public String getNom(){return super.nom;}
}
