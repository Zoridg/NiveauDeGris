package dictionnaire.correction;
/** L'interface Couple permet de caractériser tout objet qui contient des paires
 * de valeurs, la première du type X, la seconde du type Y */
public interface Couple<X,Y> {
    X premier() ;                   
    Y second() ;
    void defPremier(X x) ;       
    void defSecond(Y y) ;
    boolean equals(Object o) ;
}
