public class Voyante extends Personnage{
    private final static String NOMCLASSE = "Voyante";

    public Voyante(String nom) {
        super(nom, "Voyante");
    }

    public static String observer(Personnage p){
        return p.role;
    }
    public String getNom() {
        return super.nom;
    }

}
