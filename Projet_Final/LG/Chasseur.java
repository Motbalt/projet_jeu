import java.util.ArrayList;
public class Chasseur extends Personnage implements PeutTuer{
    private final static String NOMCLASSE = "Chasseur";

    public Chasseur(String nom){
        super(nom, "Chasseur");
    }

    public String getNom(){
        return super.nom;
    }

    public void Tuer(){
        System.out.println("En mourrant le chasseur a emporté quelqu'un avec lui dans la tombe.");
    }
}
