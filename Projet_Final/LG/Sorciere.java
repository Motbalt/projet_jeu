import java.util.ArrayList;
public class Sorciere extends Personnage implements PeutTuer{
    private final static String NOMCLASSE = "Sorcière";
    public boolean potionVie = true;
    public boolean potionTue = true;

    public Sorciere(String nom){
        super(nom, "Sorcière");
    }

    public String getNom(){
        return super.nom;
    }

    public void res(){
        //String nomRes = "";
        if(this.potionVie){
            //nomRes = Clavier.lireString("Qui voulez vous réssuciter ? ");
            potionVie = false;
        }
        else{
            System.out.println("Vous avez déjà utilisé(e) cette potion");
        }

        //return nomRes;
    }

    public void Tuer(){
        //String nomTue = "";
        if(this.potionTue){
            //nomTue = Clavier.lireString("Qui voulez vous tuer ? ");
            potionTue = false;
        }
        else{
            System.out.println("Vous avez déjà utilisé(e) cette potion");
        }
        //return nomTue;
    }

}
