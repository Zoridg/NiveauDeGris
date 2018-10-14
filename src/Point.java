package image ;

import dictionnaire.correction.Couple;

/** La classe <code>Point</code> représente un couple de coordonnées
 *  (x, y) dans une image en niveaux de gris. Elle est utilisée pour 
 *  l'implémentation de l'image par un dictionnaire dans lequel elle
 *  sert de clef, associée au niveau de gris comme valeur.
 *  @see image.ImageDict
 */
public class Point implements Couple<Integer,Integer> {
    private int x, y ;

    public Point(int x, int y) {
        this.x = x ; 
        this.y = y ;
    }

    public String toString() { 
        return "("+x+", "+y+")" ; 
    }

    int getX() { return x ; }
    int getY() { return y ; }

    public Integer premier() { return x ; }   
    public Integer second() { return y ; }
    
    public void defPremier(Integer i) { this.x = i ; }
    public void defSecond(Integer i) { this.y = i ; }

    public boolean equals(Object o) {
        if (o == null)
            return false ;
        if (! (o instanceof Point)) 
            return false ;
        Point p = (Point) o ;
        return (x == p.getX()) && (y == p.getY()) ;
    }
}
