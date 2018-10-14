package image ;

import java.awt.Color ;

/** L'énumération NiveauGris représente de façon abstraite une couleur 
 * intermédiaire entre blanc et noir. Cette représentation abstraite
 * peut ensuite faire l'objet d'un affichage en mode texte ou en
 * mode graphique. */
public enum NiveauGris {
    /** Les niveaux de gris disponibles */
    BLANC(Color.WHITE, ' '),
    GRIS_CLAIR(Color.LIGHT_GRAY, '.'),
    GRIS_MOYEN(Color.GRAY, '+'),
    GRIS_FONCE(Color.DARK_GRAY, 'x'),
    NOIR(Color.BLACK, '*') ;

    // les attributs d'un niveau de gris
    private final Color couleur ;
    private final char code ;

    // le constructeur d'un niveau de gris (usage interne à l'énumération bien sûr)
    NiveauGris(Color couleur, char code) {
        this.couleur = couleur ;
        this.code = code ;
    }
    

    
    /** Donne l'instance de niveau de gris à partir d'un numéro
     * d'ordre. Si la valeur spécifiée est trop faible ou trop forte,
     * le niveau de gris retourné "sature" à BLANC ou à NOIR. */
    public static NiveauGris deNiveau(int niv) {
        NiveauGris[] valeurs = values() ;
        return valeurs[Math.min(Math.max(niv, 0), valeurs.length-1)] ;
    }

    /** Donne l'instance de niveau de gris à partir d'un degré de luminosité
     * compris entre 0 (noir) et 1 (blanc). Si la valeur spécifiée est
     * trop faible ou trop forte, le niveau de gris retourné "sature"
     * à BLANC ou à NOIR. */
    public static NiveauGris deLuminosite(double luminosite) {
        double noirceur = Math.min(Math.max(1-luminosite, 0), 1) ;
        return deNiveau((int) Math.round(noirceur * (values().length - 1))) ;
    }

    /** Donne l'instance de niveau de gris dont le caractère spécifié
     * le représente en mode texte. Si le caractère ne correspond à
     * rien, renvoie null */
    public static NiveauGris deCaractere(char code) {
        for(NiveauGris g: values())
            if (g.code == code)
                return g ;
        return null ;
    }

    /** Donne l'instance de niveau de gris à partir de la couleur qui
     * le représente en mode graphique. Si la couleur spécifiée ne
     * correspond à rien, renvoie null */
    public static NiveauGris deCouleur(Color couleur) {
        for(NiveauGris g: values())
            if (g.couleur.equals(couleur))
                return g ;
        return null ;
    }

    public static NiveauGris randomize() {
        NiveauGris[] valeurs = values() ;
        return valeurs[((int)(Math.random()*valeurs.length))] ;
    }

    public static NiveauGris randomizeNB() {
        if (Math.random() < 0.5)
            return BLANC ;
        return NOIR ;
    }

    /** Retourne le caractère représentant le niveau de gris courant */
    public char code() { return this.code ; }

    /** Retourne la couleur représentant le niveau de gris courant */
    public Color couleur() { return this.couleur ; }

    /** Teste si le niveau de gris courant est noir */
    public boolean estNoir() { return this == NOIR ; }

    /** Teste si le niveau de gris courant est blanc */
    public boolean estBlanc() { return this == BLANC ; }

    /** Renvoie le niveau de gris un ton plus clair que le niveau courant */
    public NiveauGris eclaircir() {
        return deNiveau(this.ordinal()-1) ;
    }

    // Renvoie le niveau de gris n tons plus clair que le niveau courant
    NiveauGris eclaircir(int n) {
        return deNiveau(this.ordinal()-n) ;
    }

    /** Renvoie le niveau de gris un ton plus foncé que le niveau courant */
    public NiveauGris assombrir() {
        return deNiveau(this.ordinal()+1) ;
    }

    // Renvoie le niveau de gris n tons plus foncé que le niveau courant
    public NiveauGris assombrir(int n) {
        return deNiveau(this.ordinal()+n) ;
    }

    /** Renvoie le "négatif" du niveau courant (blanc &lt;-&gt; noir) */
    public NiveauGris inverser() {
        return deNiveau(values().length-1 - this.ordinal()) ; 
    }

    /** Renvoie BLANC si les deux niveaux de gris sont égaux, NOIR sinon */
    public NiveauGris XOR(NiveauGris gris) {
        return (this==gris?BLANC:NOIR) ;
    }

    /** Renvoie un niveau de gris correspondant à la somme du niveau courant et
     * du paramètre (maximum : noir) */
    public NiveauGris ajouter(NiveauGris gris) {
        return assombrir(gris.ordinal()) ;       
    }

    /** Renvoie un niveau de gris correspondant à la différence entre le niveau 
     * courant et le paramètre (minimum : blanc) */
    public NiveauGris soustraire(NiveauGris gris) {
        return eclaircir(gris.ordinal()) ;       
    }

    /** Renvoie un niveau de gris égal au plus sombre entre le niveau courant et
     * le paramètre */
    public NiveauGris maximum(NiveauGris gris) {
        if (this.compareTo(gris) < 0)
            return gris ;
        return this ;
    }

    /** Renvoie un niveau de gris égal au plus clair entre le niveau courant et
     * le paramètre */
    public NiveauGris minimum(NiveauGris gris) {
        if (this.compareTo(gris) < 0)
            return this ;
        return gris ;
    }

    /** Représentation sous forme de chaîne (en fait le caractère codant le niveau de gris) */
    public String toString() { return "" + this.code ; }
}
