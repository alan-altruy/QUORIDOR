package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //
import java.util.Scanner;
public class QuoridorGame
{
    private int nbPlayers, nbFences;
    private QuoridorTray tray;
    private QuoridorDisplay display;
    private QuoridorPlayers[] players;
    /**
         * Permet d'initialiser le jeu:
         * 
         *   - Créer un plateau de jeu (tray)
         *   - Créer un afficheur/interface graphique (Display)
         * 
         */
    public QuoridorGame()
    {
        tray = new QuoridorTray();
        display = new QuoridorDisplay(tray);
        nbFences=0;
        System.out.println("Nb de joueurs: ");
        Scanner scanNbPlayers = new Scanner(System.in);
        nbPlayers=scanNbPlayers.nextInt();
        Init2Players();
    }
    /**
         * Permet d'initialiser plusieurs instances players de QuoridorPlayers
         * et modifier le type de la cellule qui contient à préent un joueur
         * 
         */
    public void Init2Players()
    {
        Scanner scan;
        String name;
        int pos_x=1;
        int pos_y=9;
        players=new QuoridorPlayers[nbPlayers];
        for (int num=0; num<nbPlayers; num++) // Chaque itération permet d'initialiser un joueur
        {
            System.out.println("Nom du joueur "+(num+1)+": ");
            scan= new Scanner(System.in);
            name=scan.nextLine();
            if (num==1)
            {
                pos_x=17;
            }
            players[num]= new QuoridorPlayers(num, name, pos_x, pos_y);
            tray.ChangeType_inCell(pos_x,pos_y, 1);
        }
    }
    /**
         * Permet à un joueur de faire une action dans le jeu
         * (soit ajouter une barrière, soit bouger son pion)
         * 
         * @param num
         *            Le numero associé au joueur.
         */
    public void PlayerAction(int num)
    {
        Scanner scan;
        System.out.println("Au tour de "+players[num].GetName()+"\n\nQue voulez-vous faire?\n1. Positionner une barrière\n2. Bouger le pion\nChoix: ");
        scan =new Scanner(System.in);
        int choix= scan.nextInt();
        switch (choix)
        {
            case 1:
                NewFence(num);
                break;
        }
    }
    /**
         * Permet d'ajouter une barrière mais avant tout de vérifier si c'est possible d'en ajouter une.
         * 
         * @param num
         *            Le numero associé au joueur.
         */
    public void NewFence(int num)
    {
        Scanner scan;
        System.out.println("Position x: ");
        scan= new Scanner (System.in);
        int pos_x= scan.nextInt();
        System.out.println("Position y: ");
        scan= new Scanner (System.in);
        int pos_y= scan.nextInt();
        System.out.println("Direction (v=0 , h=1): ");
        scan= new Scanner (System.in);
        int dir= scan.nextInt();
        if(VerifFence(pos_x,pos_y,dir))
        {
            nbFences++;
            tray.AddFence(pos_x, pos_y, dir);
        }
        else
        {
            PlayerAction(num);
        }
    }
    public boolean VerifFence(int pos_x, int pos_y, int dir)
    {
        boolean verif=true;
        if (pos_x<1 || pos_x>17 || pos_y<1 || pos_y>17)
        {
            verif=false;
        }
        else if ((pos_x%2==1 && (dir==1 || pos_y%2==1)) || tray.VerifAlreadyFenceAndWall(pos_x, pos_y, dir))
        {
            verif=false;
        }
        return verif;
    }
    public void showDisplay()
    {
        display.showDisplay(tray);
    }
    public int getNbPlayers()
    {
		return nbPlayers;
    }
    public int getNbFences()
    {
		return nbFences;
	}
}