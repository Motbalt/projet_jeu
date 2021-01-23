import java.util.ArrayList;
public class Loup extends Personnage {
    private final static String NOMCLASSE = "Loup";

    public Loup(String nom){
        super(nom, "Loup");
    }

    public String getNom(){
        return super.nom;
    }

}
