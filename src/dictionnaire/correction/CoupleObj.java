package dictionnaire.correction;
/** Cette classe représente un couple comportant deux objets
 * quelconques.*/
public class CoupleObj<K,V> implements Couple<K,V> {
    private K clef ; 
    private V valeur ;
    
    public CoupleObj(K k, V v) {
        clef = k ; valeur = v ;
    }
    
    public K premier() { return clef ; }
    public V second() { return valeur ; }
    public void defPremier(K k) { clef = k ; }
    public void defSecond(V v) { valeur = v ; }
    
    public boolean equals(Object o) {
        return ((clef.equals(((Couple) o).premier()))
                && (valeur.equals(((Couple) o).second()))) ;
    }
    public String toString() {
        return clef + " -> " + valeur ;
    }
}
