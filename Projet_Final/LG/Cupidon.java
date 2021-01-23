import java.util.*;
public class Cupidon extends Personnage{

    private final static String NOMCLASSE = "Cupidon";

    public Cupidon(String nom){
        super(nom,"Cupidon");
    }

    public String getNom(){
        return super.nom;
    }


    public static Pair<Personnage,Personnage> lier(Personnage p1, Personnage p2){ //Pair
        return new Pair(p1, p2);
    }

}
