/** Cette interface représente de façon abstraite une image en niveaux de gris.
 * <BR>Toute image est caracterisée par ses dimensions (largeur et hauteur) et
 * par les niveaux de gris associés à chaque coordonnée (x, y).<P>
 * Par défaut, une image nouvellement créée doit être entièrement <B>blanche</B> */

package image;
public interface ImageGrise {
    /** Retourne la largeur de l’image */
    int largeur();
    /** Retourne la hauteur de l’image */
    int hauteur();
    /** Retourne le niveau de gris du point de coordonnées (x,y) */
    image.NiveauGris pointEn(int x, int y);
    /** Fixe le niveau de gris du point de coordonnées (x,y) à la valeur spécifiée */
    void definirPoint(int x, int y, image.NiveauGris gris);
    /** Met en noir le point de coordonnées (x,y) */
    void allumer(int x, int y);
    /** Met en blanc le point de coordonnées (x,y) */
    void eteindre(int x, int y);
    /** Donne une valeur aléatoire (noir ou blanc) à chaque point de l’image */
    void randomize();
    /** Compte le nombre de points de l’image dont le niveau de gris est égal au niveau spécifié */
    int compterPoints(image.NiveauGris gris);
    /** Retourne une image qui est le négatif de l’image courante */
    ImageGrise inverser();
    /** Retourne une image dont tous les points (sauf blancs) sont un niveau
     * plus clair que dans l’image courante */
    ImageGrise eclaircir();
    /** Retourne une image dont tous les points (sauf noirs) sont un niveau
     * plus foncé que dans l’image courante */
    ImageGrise assombrir();
    /** Retourne une <B>copie</B> de l’image courante */
    ImageGrise dupliquer();
    /** Retourne une image en additionnant point par point les niveaux de gris de l’image
     * courante et de l’image en paramètre (les deux images doivent être de même taille) */
    ImageGrise ajouter(ImageGrise img);
    /** Retourne une image en retranchant point par point les niveaux de gris de l’image
     * courante et de l’image en paramètre (les deux images doivent être de même taille) */
    ImageGrise soustraire(ImageGrise img);
    /** Retourne une image en faisant un OU Exclusif (XOR) point par
     * point les niveaux de gris de l’image courante et de l’image en
     * paramètre (les deux images doivent être de même taille) */
    ImageGrise XOR(ImageGrise img);
    /** Retourne une image qui représente "l’intersection" de l’image courante et de l’image
     * en paramètre : seuls les points qui ont le même niveau de gris dans les deux images sont
     * conservés (les deux images doivent être de même taille) */
    ImageGrise intersection(ImageGrise img);
    /** Retourne le niveau de gris moyen de l’image. Pour le calculer, il faut faire la
     * moyenne des niveaux de chaque point de l’image (ce qui revient à compter combien il y
     * a de points de chaque niveau de gris possible) */
    image.NiveauGris niveauMoyen();
    /** Retourne une image obtenue en augmentant le contraste de l’image courante. Pour
     * augmenter le contraste, il faut rendre les points sombres plus sombres qu’ils ne sont,
     * et les points clairs plus clairs. Un bon moyen de procéder consiste à calculer le
     * niveau de gris moyen de l’image, et assombrir (respectivement eclaircir) les points
     * plus sombres (resp. plus clairs) que ce niveau moyen */
    ImageGrise augmenterContraste();
}