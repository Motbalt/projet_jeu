import java.util.*;

public class Jeu {
    private final int NBJOUEURMIN = 6;
    private final int NBJOUEURMAX = 11;
    private ArrayList<Personnage> listePersonnage = new ArrayList<>();
    private enum listeRole {Villageois, Sorciere, Loup, Voyante, Cupidon, Chasseur};
    private ArrayList<String> listeNom = new ArrayList<String>();
    private Sorciere sorciere;
    private Pair amoureux;

    public Jeu(ArrayList<String> nom){
        //Chaque nom doit-être différent
        if(nom.size()>NBJOUEURMIN && nom.size()<NBJOUEURMAX){
            this.listeNom = nom;
            Collections.shuffle(nom);

            Personnage voyante = new Voyante(nom.get(0));
            this.listePersonnage.add(voyante);

            this.sorciere = new Sorciere(nom.get(1));
            this.listePersonnage.add(sorciere);

            Personnage cupidon = new Cupidon(nom.get(2));
            this.listePersonnage.add(cupidon);

            Personnage chasseur = new Chasseur(nom.get(3));
            this.listePersonnage.add(chasseur);

            Personnage loup1 = new Loup(nom.get(4));
            this.listePersonnage.add(loup1);

            Personnage loup2 = new Loup(nom.get(5));
            this.listePersonnage.add(loup2);
            
            for(int i = 6; i < nom.size(); i++){
                this.listePersonnage.add(new Villageois(nom.get(i)));
            }
            
        }
        else{
            System.err.println("Vous n'êtes pas assez");
        }
        
    }

    private boolean victoireVillage(){
        boolean result = true;
        for(Personnage i : this.listePersonnage){
            if(i instanceof Loup){
                result = false;
            }
        }
        return result;
    }

    private boolean victoireLoup(){
        boolean result = true;
        for(Personnage i : listePersonnage){
            if(!(i instanceof Loup)){
                result = false;
            }
        }
        return result;
    }

    private void printNom(){
    	System.out.println("####################################################################################################################");
        for(Personnage p : this.listePersonnage){
            System.out.println("Joueur " + this.listePersonnage.indexOf(p) + " : " + p);
        }
        System.out.println("####################################################################################################################");
    }

    private String checkNom(String msg){
        boolean result = false;
        String entree = "";
        while(!result) {
            entree = Clavier.lireString(msg);
            if(this.listeNom.contains(entree)) {
                result = true;
            }
        }
        return entree;
    }

    private Personnage resultVote(){
        Personnage pVote = this.listePersonnage.get(0);

        for(Personnage p : this.listePersonnage){
            if(pVote.nbVote < p.nbVote){
                pVote = p;
            }
        }

        return pVote;
    }

    private void resetVote(){
        for(Personnage p : this.listePersonnage){
            p.nbVote = 0;
        }
    }

    private Personnage electionLoup(){
        for(Personnage p : this.listePersonnage) {
            if (p instanceof Loup) {
                p.vote(this.listePersonnage);
            }
        }
        Personnage result = this.resultVote();
        this.resetVote();

        return result;
    }

    private Personnage electionClassique(){
        for(Personnage p : this.listePersonnage){
            p.vote(this.listePersonnage);
        }
        Personnage result = this.resultVote();
        this.resetVote();

        return result;
    }

    private Personnage getPersonnageFromName(String nom){
        Personnage result = null;
        for(Personnage p : this.listePersonnage){
            if(p.nom.equals(nom)){
                result = p;
            }
        }
        return result;
    }
    
    private boolean checkVoyante() {
    	boolean result = false;
    	for (Personnage p : this.listePersonnage) {
    		if (p instanceof Voyante) {
    			result = true;
    		}
    	}
    	return result;
    }
    
    private boolean checkSorciere() {
    	boolean result = false;
    	for (Personnage p : this.listePersonnage) {
    		if (p instanceof Sorciere) {
    			result = true;
    		}
    	}
    	return result;
    }
    
    private void removePersonnage(Personnage p){

        //if(p.role.equals("Chasseur")){
    	if(p instanceof Chasseur) {
            String victime = checkNom("Chasseur, qui veux tu tuer ? ");
            Chasseur chasseur = (Chasseur) p;
            chasseur.Tuer();
            if(this.amoureux.getT() == p || this.amoureux.getU() == p) {
            	this.listePersonnage.remove(amoureux.getT());
            	this.listePersonnage.remove(amoureux.getU());
           	 }
            else {
            	this.listePersonnage.remove(p); //Remove du chasseur
            }
            this.removePersonnage(getPersonnageFromName(victime));
            
            this.listeNom.remove(victime);
            this.listeNom.remove(p.nom);
        }
        else if(this.amoureux.getT() == p || this.amoureux.getU() == p){
        	if(this.amoureux.getT() == p && this.amoureux.getU() instanceof Chasseur) {
        		this.listePersonnage.remove(amoureux.getT());
                this.removePersonnage((Personnage) amoureux.getU());
        	} else if (this.amoureux.getU() == p && this.amoureux.getT() instanceof Chasseur) {
        		this.listePersonnage.remove(amoureux.getU());
                this.removePersonnage((Personnage) amoureux.getT());
        	} else {
            this.listePersonnage.remove(amoureux.getT());
            this.listePersonnage.remove(amoureux.getU()); //Remove des amoureux
        	}
            this.listeNom.remove(amoureux.getT());
            this.listeNom.remove(amoureux.getU());
            
        } else {
            this.listePersonnage.remove(p);
            this.listeNom.remove(p.nom);
        }
    }

    public void lancerPartie(){
        //Jour Nuit Tour (différentes classes)
        System.out.println("Attribution de vos rôles, veuillez laisser le narrateur seul devant le terminal.");
        for(Personnage p : listePersonnage){
            System.out.println(p.nom + " est " + p.role); //getClasse().getName()
        }
        /*
            PREMIERE NUIT : INITIALISATION DES PARAMETRES DU JEU
        */
        
        printNom();
        String nomLier = checkNom("Cupidon à ton tour, lies deux personnages qui resteront ensemble jusqu'à leurs morts : ");
        String nomLier2 = checkNom("Cupidon à ton tour, lies deux personnages qui resteront ensemble jusqu'à leurs morts 2: ");
        this.amoureux = Cupidon.lier(this.getPersonnageFromName(nomLier),this.getPersonnageFromName(nomLier2));

        printNom();
        String nomVue = checkNom("Voyante à ton tour, regarde le rôle d'un personnage : ");
        String role = Voyante.observer(this.getPersonnageFromName(nomVue));
        System.out.println("Le role de " + nomVue + " est " + role);

        System.out.println("Loup à votre tour, désigner le personnage que vous voulez dévorer");
        printNom(); //Verifier qu'il ne tue pas un loups
        Personnage personneTuerParLesLoups = electionLoup();

        //CheckPotionUtiliser (si elle n'as sauver personne alors on print son nom sinon rien)
        System.out.println("Sorciere à ton tour, veux-tu sauver" + personneTuerParLesLoups + " ou utiliser ta potion mortelle" +
                "ou patienter");
        int choixSorciere;
        choixSorciere= Clavier.lireInt("Appuyer sur 1 pour utiliser la potion de vie, 2 pour celle de mort et 3 pour ne rien faire : ");
        while(0 > choixSorciere && choixSorciere > 4) {
            choixSorciere = Clavier.lireInt("Appuyer sur 1 pour utiliser la potion de vie, 2 pour celle de mort et 3 pour ne rien faire : ");
        }
        if(choixSorciere==2 && sorciere.potionTue){ //CHOIX MORT
            printNom();
            String nomTueSorciere = checkNom("Qui veux tu tuer : ");
            this.sorciere.Tuer();
            removePersonnage(this.getPersonnageFromName(nomTueSorciere)); //On tue le choix de la sorciere
            removePersonnage(personneTuerParLesLoups);

        }
        else if(choixSorciere==1 && sorciere.potionVie){ //CHOIX VIVRE
            this.sorciere.res();
        } else {
            removePersonnage(personneTuerParLesLoups);
        }

        //VOTE DU MAIRE
        printNom();
        System.out.println("Voter pour le maire.");
        Personnage pVoter = this.electionClassique();
        pVoter.poidVote = 2;
        System.out.println("Le maire est " + pVoter);
        
        while(!victoireVillage() && !victoireLoup()){
            //Nuit
        	if(this.checkVoyante()) {
	            printNom();
	            nomVue = checkNom("Voyante à ton tour, regarde le rôle d'un personnage : ");
	            role = Voyante.observer(this.getPersonnageFromName(nomVue));
	            System.out.println("Le role de " + nomVue + " est " + role);
        	}
            //Election loup
            System.out.println("Loup à votre tour, désigner le personnage que vous voulez dévorer");
            printNom(); //Verifier qu'il ne tue pas un loups
            personneTuerParLesLoups = electionLoup();
            
            if(this.checkSorciere()) {
	            //Sorciere tour
	            System.out.println("Sorciere à ton tour, veux-tu sauver" + personneTuerParLesLoups + " ou utiliser ta potion mortelle" +
	                    "ou patienter");
	            choixSorciere= Clavier.lireInt("Appuyer sur 1 pour utiliser la potion de vie, 2 pour celle de mort et 3 pour ne rien faire : ");
	            while(0 > choixSorciere && choixSorciere > 4) {
	                choixSorciere = Clavier.lireInt("Appuyer sur 1 pour utiliser la potion de vie, 2 pour celle de mort et 3 pour ne rien faire : ");
	            }
	            if(choixSorciere==2 && sorciere.potionTue){ //CHOIX MORT
	                printNom();
	                String nomTueSorciere = checkNom("Qui veux tu tuer : ");
	                this.sorciere.Tuer();
	                removePersonnage(this.getPersonnageFromName(nomTueSorciere)); //On tue le choix de la sorciere
	                removePersonnage(personneTuerParLesLoups);
	
	            }
	            else if(choixSorciere==1 && sorciere.potionVie){ //CHOIX VIVRE
	                this.sorciere.res();
	            } else {
	                removePersonnage(personneTuerParLesLoups);
	            }
            }
            else {
            	 removePersonnage(personneTuerParLesLoups);
            }
            
            //Comme �a le jour ne se l�ve pas si il ya une victoire pour l'une des deux �quipes
            
            if(victoireVillage() || victoireLoup()) {
            	break;
            }
            
            //Passage maire / Premiere nuit deux loups morts / m�me pr�noms / checkPotion / Couple qui gagne / Couple qui se tue / �galit� des votes / votes nuls
            
            //Jour
            System.out.println("############################# VOTE #############################");
            printNom();
            pVoter = this.electionClassique();
            removePersonnage(pVoter);
        }
        
        if(victoireVillage()) {
        	System.out.println("VICTOIRE DU VILLAGE");
        }
        else {
        	System.out.println("VICTOIRE DU VILLAGE");
        }
    }
}
