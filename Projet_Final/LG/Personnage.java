import java.util.ArrayList;
public class Personnage{
    public String nom;
    public int nbVote = 0;
    public String role;
    public int poidVote = 1;

    public Personnage(String nom, String role){
        this.nom = nom;
        this.role = role;
    }

    public void vote(ArrayList<Personnage> listePersonnage){
        String nomVote = this.checkNom(listePersonnage, this.nom + ", qui voulez vous voter ? ");

        for(Personnage p : listePersonnage){
            if(p.nom.equals(nomVote)){
                p.nbVote = p.nbVote + this.poidVote;
                break;
            }
        }
    }

    @Override
    public String toString(){
        return this.nom;
    }

    private String checkNom(ArrayList<Personnage> listePersonnage, String msg){
        boolean result = false;
        String entree;
        entree = "";
        ArrayList<String> listeNom = new ArrayList<String>();
        //Liste nom
        for(Personnage p : listePersonnage){
            listeNom.add(p.nom);
        }

        while(!result) {
            entree = Clavier.lireString(msg);
            if(listeNom.contains(entree)) {
                result = true;
            }
        }
        return entree;
    }
}
