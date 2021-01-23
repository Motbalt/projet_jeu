import java.util.*;
public class Main {
    public static void main(String[] args){
        ArrayList<String> listeNom = new ArrayList<String>();     
        listeNom.add("Diego");
        listeNom.add("Enzo");
        listeNom.add("Benjamin");
        listeNom.add("Thomas");
        listeNom.add("Sarah");
        listeNom.add("Eloise");
        listeNom.add("Alexandre");

        Jeu jeu = new Jeu(listeNom);
        jeu.lancerPartie();
    }
}
