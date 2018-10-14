package dictionnaire.correction;
/** L'interface Dictionnaire décrit les objets capables de stocker un ensemble d'associations
 *  (clef, valeur), chaque clef n'étant présente qu'une seule fois. À noter : cette interface
 *  utilise deux types génériques K et V pour le type des clefs et les valeurs respectivement.
 */
public interface Dictionnaire<K,V>
{
    /** Teste si le dictionnaire ne contient aucune association */
    boolean estVide() ;
    /** Teste si le dictionnaire contient l'association assoc */
    boolean contient(Couple<K, V> assoc) ;
    /** Teste si le dictionnaire possède une association de clef c */
    boolean contientClef(K c) ;
    /** Teste si le dictionnaire possède une association de valeur v */
    boolean contientValeur(V v) ;
    /** Retourne le nombre d'associations du dictionnaire */
    int nbElements() ;
    /** Retourne l'association correspondant à la clef c spécifiée, null si absente */
    Couple<K,V> assocPour(K c) ;
    /** Retourne la valeur associée à le clef c, null si absente */
    V valeurPour(K c) ;
    /** Ajoute l'association assoc au dictionnaire (remplacement si clef présente) */
    void ajouter(Couple<K, V> assoc) ;
    /** Définit ou modifie la valeur v associée à la clef c dans le dictionnaire */
    void ajouter(K c, V v) ;
    /** Enlève l'association assoc du dictionnaire (si présente) */
    void enlever(Couple<K, V> assoc) ;
    /** Enlève l'entrée de clef c (si présente) */
    void enleverPour(K c) ;
}
