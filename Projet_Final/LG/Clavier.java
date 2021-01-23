import java.io.* ;
/** Classe fournissant des fonctions de lecture au clavier en mode Console
 * @author Evelyne Vittori
  * 
 */ 
public class Clavier
	{ /** M�thode de lecture d'une cha�ne de caract�res au clavier en mode console
	 * @param msg message � afficher pour demander � l'utilisateur de saisir une chaine au clavier 
	 * @return cha�ne de caract�re lue
	 */
	public static String lireString (String msg)   // lecture d'une chaine
	  { String ligne_lue = null ;
	    //Affichage du message de demande
	    System.out.print(msg);
	    try
	    { // tentative de lecture de l'entr�e standard r�f�renc�e par System.ini
	      InputStreamReader lecteur = new InputStreamReader (System.in) ;
	      BufferedReader entree = new BufferedReader (lecteur) ;
	      ligne_lue = entree.readLine() ;
	    }
	    catch (IOException err)
	    { System.exit(0) ;
	    }
	    return ligne_lue ;
	  }
	/** M�thode de lecture d'un r�el (float) au clavier en mode console
	 * @param msg message � afficher pour demander � l'utilisateur de saisir un r�el au clavier 
	 * @return r�el (float) lu au clavier
	 */
	public static float lireFloat (String msg)   // lecture d'un float
	  { float x=0 ;   // valeur a lire
	    try
	    { String ligne_lue = lireString(msg) ;
	      x = Float.parseFloat(ligne_lue) ;// Conversion de la cha�ne ligne_lue en float
	    }
	    catch (NumberFormatException err)
	    { System.out.println ("*** Erreur de saisie ***") ;
	      System.exit(0) ;
	    }
	    return x ;
	  }
	/** M�thode de lecture d'un r�el (double) au clavier en mode console
	 * @param msg message � afficher pour demander � l'utilisateur de saisir un r�el au clavier 
	 * @return r�el (double) lu au clavier
	 */
	  public static double lireDouble (String msg)   // lecture d'un double
	  { double x=0 ;   // valeur a lire
	    try
	    { String ligne_lue = lireString(msg) ;
	      x = Double.parseDouble(ligne_lue) ; // Conversion de la cha�ne ligne_lue en double
	    }
	    catch (NumberFormatException err)
	    { System.out.println ("*** Erreur de saisie ***") ;
	      System.exit(0) ;
	    }
	    return x ;
	  }
	  /** M�thode de lecture d'un entier (int) au clavier en mode console
		 * @param msg message � afficher pour demander � l'utilisateur de saisir un entier au clavier 
		 * @return entier(int) lu au clavier
		 */
	  public static int lireInt (String msg)         // lecture d'un int
	  { int n=0 ;   // valeur a lire
	    try
	    { String ligne_lue = lireString(msg) ;
	      n = Integer.parseInt(ligne_lue) ; // Conversion de la cha�ne ligne_lue en int
	      }
	    catch (NumberFormatException err)
	    { System.out.println ("*** Erreur de donnee ***") ;
	      System.exit(0) ;
	    }
	    return n ;
	  }
	  /** Programme de test de la classe Clavier
	   */
	  public static void main (String[] args)
	  { 
	    double x ;
	    x = Clavier.lireDouble("Donnez un r�el : ") ;
	    System.out.println ("Merci pour " + x) ;
	    
	    int n  ;
	    n = Clavier.lireInt("Donnez un entier : ") ;
	    System.out.println ("Merci pour " + n) ;
	    
	    String s=null  ;
	    s = Clavier.lireString("Donnez une cha�ne de carat�res : ") ;
	    System.out.println ("Merci pour " + s) ;
	 }
	}
	
	
