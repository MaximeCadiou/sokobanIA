
import Controlleur.Joueur;
import Controlleur.NoeudRecherche;
import Modèle.PlateauIA;

public class JSoko {

    public static void main(String[] args){

        final int lvl = 45;
        PlateauIA p1 = new PlateauIA("./src/niveaux/lvl"+Integer.toString(lvl)+".sok");


        //Coeff Eval puis Coeff Coups
        NoeudRecherche.setCoefs(1, 1);

        //Coeff Caisses puis Coeff Joueur
        PlateauIA.setCoeffs(1, 1);

        Joueur j = new Joueur(true,p1);

        try{
            j.Astar(10000);
            j.afficherSolution();

        }
        catch(Error e){
            System.err.println(e);
            System.out.println("        Nb itérations : " + j.getIterations());
        }


        Partie partie = new Partie(lvl);
        Plateau p = new Plateau(partie);
        VuePartie vp2 = new VuePartie(p);
        VuePlateau vp = new VuePlateau(p);

        javax.swing.JFrame f = new javax.swing.JFrame("Sokoban IA");
        f.getContentPane().add(vp2,java.awt.BorderLayout.SOUTH);
        f.getContentPane().add(vp,java.awt.BorderLayout.CENTER);
        f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(vp);
        f.pack();

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation((screenSize.width-f.getWidth())/2,(screenSize.height-f.getHeight())/2);
        f.setJMenuBar(new MenuSoko(p));
        f.setResizable(false);
        f.setVisible(true);

        vp.afficherSolution(j.getDirectionsSolution());
    }
}
