import dictionnaire.correction.Couple;
import dictionnaire.correction.CoupleObj;
import dictionnaire.correction.Dictionnaire;
import dictionnaire.correction.TabDict;
import image.ImageGrise;
import image.NiveauGris;

public class ImageDict implements ImageGrise {
    private Dictionnaire<Couple<Integer, Integer>, NiveauGris> tabDict;
    private int largeur;
    private int hauteur;


    public ImageDict(int largeur, int hauteur){
        this.tabDict = new TabDict<>();
        this.largeur = largeur;
        this.hauteur = hauteur;
        for(int i = 0; i < largeur; i++){
            for(int j = 0; j < hauteur; j++){
                Couple<Integer, Integer> coupleXY = new CoupleObj<>(i, j);
                this.tabDict.ajouter(coupleXY, NiveauGris.BLANC);
            }
        }
    }
    /**
     * Retourne la largeur de l’image
     */
    @Override
    public int largeur() {
        return largeur;
    }

    /**
     * Retourne la hauteur de l’image
     */
    @Override
    public int hauteur() {
        return hauteur;
    }

    /**
     * Retourne le niveau de gris du point de coordonnées (x,y)
     *
     * @param x
     * @param y
     */
    @Override
    public NiveauGris pointEn(int x, int y) {
        Couple<Integer, Integer> coupleXY = new CoupleObj<>(x, y);
        if(this.tabDict.contientClef(coupleXY)){
            return this.tabDict.valeurPour(coupleXY);
        }
        else {
            return null;
        }
    }

    /**
     * Fixe le niveau de gris du point de coordonnées (x,y) à la valeur spécifiée
     *
     * @param x
     * @param y
     * @param gris
     */
    @Override
    public void definirPoint(int x, int y, NiveauGris gris) {
        Couple<Integer, Integer> coupleXY = new CoupleObj<>(x, y);
        if(this.tabDict.contientClef(coupleXY)){
            this.tabDict.ajouter(coupleXY, gris);
        }
    }

    /**
     * Met en noir le point de coordonnées (x,y)
     *
     * @param x
     * @param y
     */
    @Override
    public void allumer(int x, int y) {
        Couple<Integer, Integer> coupleXY = new CoupleObj<>(x, y);
        if(this.tabDict.contientClef(coupleXY)){
            this.tabDict.ajouter(coupleXY, NiveauGris.NOIR);
        }
    }

    /**
     * Met en blanc le point de coordonnées (x,y)
     *
     * @param x
     * @param y
     */
    @Override
    public void eteindre(int x, int y) {
        Couple<Integer, Integer> coupleXY = new CoupleObj<>(x, y);
        if(this.tabDict.contientClef(coupleXY)){
            this.tabDict.ajouter(coupleXY, NiveauGris.BLANC);
        }
    }

    /**
     * Donne une valeur aléatoire (noir ou blanc) à chaque point de l’image
     */
    @Override
    public void randomize() {
        for(int i = 0; i < largeur; i++){
            for(int j = 0; j < hauteur; j++){
                Couple<Integer, Integer> coupleXY = new CoupleObj<>(i, j);
                this.tabDict.ajouter(coupleXY, NiveauGris.randomizeNB());
            }

        }
    }

    /**
     * Compte le nombre de points de l’image dont le niveau de gris est égal au niveau spécifié
     *
     * @param gris
     */
    @Override
    public int compterPoints(NiveauGris gris) {
        int cpt = 0;
        for(int i = 0; i < largeur; i++){
            for(int j = 0; j < hauteur; j++){
                Couple<Integer, Integer> coupleXY = new CoupleObj<>(i, j);
                if(this.tabDict.valeurPour(coupleXY).equals(gris)){
                    cpt++;
                }
            }
        }
        return cpt;
    }

    /**
     * Retourne une image qui est le négatif de l’image courante
     */
    @Override
    public ImageGrise inverser() {
        ImageGrise imageGrise = new ImageDict(largeur, hauteur);
        for(int i = 0; i < this.largeur; i++){
            for(int j = 0; j < this.hauteur; j++){
                Couple<Integer, Integer> coupleXY = new CoupleObj<>(i, j);
                imageGrise.definirPoint(i, j, this.tabDict.valeurPour(coupleXY).inverser());
            }
        }
        return imageGrise;
    }

    /**
     * Retourne une image dont tous les points (sauf blancs) sont un niveau
     * plus clair que dans l’image courante
     */
    @Override
    public ImageGrise eclaircir() {
        ImageGrise imageGrise = new ImageDict(largeur, hauteur);
        for(int i = 0; i < this.largeur; i++){
            for(int j = 0; j < this.hauteur; j++){
                Couple<Integer, Integer> coupleXY = new CoupleObj<>(i, j);
                if(!this.tabDict.valeurPour(coupleXY).equals(NiveauGris.BLANC)){
                    imageGrise.definirPoint(i, j, this.tabDict.valeurPour(coupleXY).eclaircir());
                }
            }
        }
        return imageGrise;
    }

    /**
     * Retourne une image dont tous les points (sauf noirs) sont un niveau
     * plus foncé que dans l’image courante
     */
    @Override
    public ImageGrise assombrir() {
        ImageGrise imageGrise = new ImageDict(largeur, hauteur);
        for(int i = 0; i < this.largeur; i++){
            for(int j = 0; j < this.hauteur; j++){
                Couple<Integer, Integer> coupleXY = new CoupleObj<>(i, j);
                if(!this.tabDict.valeurPour(coupleXY).equals(NiveauGris.NOIR)){
                    imageGrise.definirPoint(i, j, this.tabDict.valeurPour(coupleXY).assombrir());
                }
            }
        }
        return imageGrise;
    }

    /**
     * Retourne une <B>copie</B> de l’image courante
     */
    @Override
    public ImageGrise dupliquer() {
        ImageGrise imageGrise = new ImageDict(largeur, hauteur);
        for(int i = 0; i < this.largeur; i++){
            for(int j = 0; j < this.hauteur; j++){
                imageGrise.definirPoint(i, j, pointEn(i, j));
            }
        }
        return imageGrise;
    }

    /**
     * Retourne une image en additionnant point par point les niveaux de gris de l’image
     * courante et de l’image en paramètre (les deux images doivent être de même taille)
     *
     * @param img
     */
    @Override
    public ImageGrise ajouter(ImageGrise img) {
        ImageGrise imageGrise = new ImageDict(largeur, hauteur);
        if(img.largeur() != largeur || img.hauteur() != hauteur){
            return null;
        }
        else {
            for(int i = 0; i < largeur; i++){
                for(int j = 0; j < hauteur; j++){
                     imageGrise.definirPoint(i, j, pointEn(i, j).ajouter(img.pointEn(i, j)));
                }
            }
        }
        return imageGrise;
    }

    /**
     * Retourne une image en retranchant point par point les niveaux de gris de l’image
     * courante et de l’image en paramètre (les deux images doivent être de même taille)
     *
     * @param img
     */
    @Override
    public ImageGrise soustraire(ImageGrise img) {
        ImageGrise imageGrise = new ImageTab(largeur, hauteur);
        if(img.largeur() != largeur || img.hauteur() != hauteur){
            return null;
        }
        else {
            for(int i = 0; i < largeur; i++){
                for(int j = 0; j < hauteur; j++){
                    imageGrise.definirPoint(i, j, pointEn(i, j).soustraire(img.pointEn(i, j)));
                }
            }
        }
        return imageGrise;
    }

    /**
     * Retourne une image en faisant un OU Exclusif (XOR) point par
     * point les niveaux de gris de l’image courante et de l’image en
     * paramètre (les deux images doivent être de même taille)
     *
     * @param img
     */
    @Override
    public ImageGrise XOR(ImageGrise img) {
        ImageGrise imageGrise = new ImageTab(largeur, hauteur);
        if(img.largeur() != largeur || img.hauteur() != hauteur){
            return null;
        }
        else {
            for(int i = 0; i < largeur; i++){
                for(int j = 0; j < hauteur; j++){
                    imageGrise.definirPoint(i, j, pointEn(i, j).XOR(img.pointEn(i, j)));
                }
            }
        }
        return imageGrise;
    }

    /**
     * Retourne une image qui représente "l’intersection" de l’image courante et de l’image
     * en paramètre : seuls les points qui ont le même niveau de gris dans les deux images sont
     * conservés (les deux images doivent être de même taille)
     *
     * @param img
     */
    @Override
    public ImageGrise intersection(ImageGrise img) {
        ImageGrise imageGrise = new ImageTab(largeur, hauteur);
        if(img.largeur() != largeur || img.hauteur() != hauteur){
            return null;
        }
        else {
            for(int i = 0; i < largeur; i++){
                for(int j = 0; j < hauteur; j++){
                    if(this.pointEn(i, j) == img.pointEn(i, j)){
                        imageGrise.definirPoint(i, j, pointEn(i, j));
                    }
                    else {
                        imageGrise.definirPoint(i, j, pointEn(i, j));
                        imageGrise.eteindre(i, j);
                    }
                }
            }
        }
        return imageGrise;
    }

    /**
     * Retourne le niveau de gris moyen de l’image. Pour le calculer, il faut faire la
     * moyenne des niveaux de chaque point de l’image (ce qui revient à compter combien il y
     * a de points de chaque niveau de gris possible)
     */
    @Override
    public NiveauGris niveauMoyen() {
        int niveauBlanc = compterPoints(NiveauGris.BLANC);
        int niveauGrisClair = compterPoints(NiveauGris.GRIS_CLAIR);
        int niveauGrisMoyen = compterPoints(NiveauGris.GRIS_MOYEN);
        int niveauGrisFonce = compterPoints(NiveauGris.GRIS_FONCE);
        int niveauNoir = compterPoints(NiveauGris.NOIR);
        int somme = niveauBlanc + niveauGrisClair + niveauGrisMoyen + niveauGrisFonce + niveauNoir;
        int result = (niveauBlanc * NiveauGris.BLANC.ordinal()) + (niveauGrisClair * NiveauGris.GRIS_CLAIR.ordinal()) +
                (niveauGrisMoyen * NiveauGris.GRIS_MOYEN.ordinal()) + (niveauGrisFonce * NiveauGris.GRIS_FONCE.ordinal()) +
                (niveauNoir * NiveauGris.NOIR.ordinal());
        result = result / somme;
        return NiveauGris.values()[result];
    }

    /**
     * Retourne une image obtenue en augmentant le contraste de l’image courante. Pour
     * augmenter le contraste, il faut rendre les points sombres plus sombres qu’ils ne sont,
     * et les points clairs plus clairs. Un bon moyen de procéder consiste à calculer le
     * niveau de gris moyen de l’image, et assombrir (respectivement eclaircir) les points
     * plus sombres (resp. plus clairs) que ce niveau moyen
     */
    @Override
    public ImageGrise augmenterContraste() {
        ImageGrise imageGrise = new ImageTab(largeur, hauteur);
        NiveauGris niveauMoyen = this.niveauMoyen();
        for(int i = 0; i < this.largeur; i++){
            for(int j = 0; j < this.hauteur; j++){
                NiveauGris niveauGrisInstance = pointEn(i, j);
                if(niveauGrisInstance.ordinal() > niveauMoyen.ordinal()){
                    imageGrise.definirPoint(i, j, pointEn(i, j).assombrir());
                }
                else if(niveauGrisInstance.ordinal() < niveauMoyen.ordinal()){
                    imageGrise.definirPoint(i, j, pointEn(i, j).eclaircir());
                }
            }
        }
        return imageGrise;
    }
}
