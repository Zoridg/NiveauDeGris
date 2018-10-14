package dictionnaire.correction;
import java.util.Iterator ;

// Cette classe implémente l'itérateur qui permet de
// parcourir les clefs d'un TabDict.
// Elle n'est pas publique car on ne souhaite pas que
// cet itérateur soit manipulé autrement qu'à travers
// l'interface java.util.Iterator
class DictIterator<K> implements Iterator<K> {
    // l'instance de TabDict sur laquelle on travaille
    private TabDict<K,?> dict ;
    // indice de l'élément qui sera retourné par next
    private int position ;

    DictIterator(TabDict<K,?> dict) {
        this.dict = dict ;
        position = 0 ;
    }

    public boolean hasNext() {
        return position < dict.nbElements() ;
    }

    public K next() {
        if (!this.hasNext())
            return null ;
        K key = dict.clefPourIndex(position) ;
        position++ ;
        return key ;
    }

    public void remove() {
        if (position > 0) {
            position-- ;
            dict.remove(position) ;
        }       
    }

}
