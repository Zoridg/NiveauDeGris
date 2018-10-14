import image.* ;
import image.ImageGrise;
import image.NiveauGris;

/** Catalogue d'images en niveaux de gris (generees par des methodes statiques) :
 * quelques pieces honorables heraldiques (en noir et blanc) ainsi que des images
 * obtenue par des calculs parametriques (ellipses) */
public class Catalogue 
{
    public static ImageGrise fasce()
    {
	ImageGrise img = new ImageTab(21,21) ;
	for (int x=0; x<21; x++)
	    for (int i=-2; i<=2; i++)
		img.allumer(x, 10+i) ;
	return img ;
    }

    public static ImageGrise pal() 
    {
	ImageGrise img = new ImageTab(21,21) ;
	for (int y=0; y<21; y++)
	    for (int i=-2; i<=2; i++)
		img.allumer(10+i, y) ;
	return img ;
    }

    public static ImageGrise bande() 
    {
	ImageGrise img = new ImageTab(21,21) ;
	for (int x=0; x<21; x++)
	    for (int i=-2; i<=2; i++)
		img.allumer(Math.max(0,Math.min(20,x+i)), x) ;
	return img ;    
    }

    public static ImageGrise barre() 
    {
	ImageGrise img = new ImageTab(21,21) ;
	for (int x=0; x<21; x++)
	    for (int i=-2; i<=2; i++)
		img.allumer(Math.max(0,Math.min(20,20-(x+i))), x) ;
	return img ;
    }
    
    public static ImageGrise ellipse1(int kx, int ky) 
    {
	ImageGrise img = new ImageTab(200,200) ;
	NiveauGris gris ;
	int x, y ;
	for (double angle=0; angle<2*Math.PI; angle+=0.01)
	    {
		x = (int)(Math.cos(angle)*90) ;
		y = (int)(Math.sin(angle)*90) ;
		gris = NiveauGris.deLuminosite(Math.abs(Math.cos(angle*kx*ky)+1)) ;
		img.definirPoint(x/kx+100, y/ky+100, gris.assombrir()) ; 
	    }			
	return img ;
    }

    public static ImageGrise ellipse2(int phi) 
    {
	ImageGrise img = new ImageTab(200,200) ;
	NiveauGris gris ;
	int x, y ;
	for (double angle=0; angle<2*Math.PI; angle+=0.01)
	    {
		x = (int)(Math.cos(angle+phi*Math.PI/180)*90) ;
		y = (int)(Math.sin(angle)*90) ;
		gris = NiveauGris.deLuminosite(Math.abs(Math.cos(angle*phi/Math.PI)+1)) ;
		img.definirPoint(x/2+100, y+100, gris.assombrir()) ; 
	    }			
	return img ;
    }

    public static void main(String [] arg) 
    {
//	ImageGrise img = fasce().ajouter(barre().eclaircir().inverser());
//	System.out.println(img);
//	Afficheur.afficher(img,20);
//	Afficheur.afficher(ellipse1(1,2).ajouter(ellipse2(-30)), 2);

    }
}
