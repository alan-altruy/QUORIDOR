package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //
import java.util.Scanner;
public class QuoridorGame
{
    private int nbPlayers=0, nbFences=0;
    private int boucle;
    public QuoridorTray tray;
    private QuoridorPlayers[] players;
    private QuoridorGui gui;
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
        gui = new QuoridorGui(this);
        nbFences=0;
        gui.MainMenu(this);
        while (nbPlayers<=0)
        {
            nbPlayers=gui.getNbPlayers();
            System.out.println("");
        }
        System.out.println(nbPlayers);
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
            tray.ChangeType_inCell(pos_x,pos_y, num+1);
        }
    }
    public String GetNameOfPlayer(int num)
    {
        return players[num].GetName();
    }
    public int GameHasWinner()
    {
        for (int nb=0; nb<nbPlayers; nb++)
        {
            if (players[nb].AreYouTheWinner())
            {
                return nb;
            }
        }
        return 3;
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
            case 2:
                MovePlayer(num);
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
        int pos_x= scan.nextInt()-1;
        System.out.println("Position y: ");
        scan= new Scanner (System.in);
        int pos_y= scan.nextInt()-1;
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
        boolean verif1=(pos_x%2==1 && (dir==1 || pos_y%2==1));
        boolean verif2=(pos_x%2==0 && (dir==0 || pos_y%2==0));
        if  (verif1 || verif2 || tray.VerifAlreadyFenceAndWall(pos_x, pos_y, dir))
        {
            return false;
        }
        return true;
    }
    public void MovePlayer(int num)
    {
        Scanner scan;
        System.out.println("Ou voulez-vous aller?: ");
        QuoridorMovePlayer move = new QuoridorMovePlayer(players[num], tray);
        scan= new Scanner (System.in);
        int choix1= scan.nextInt();
        move.MoveIt(choix1);
    }
    public int getNbPlayers()
    {
		return nbPlayers;
    }
    public int getNbFences()
    {
		return nbFences;
    }
    public void showUI()
    {
        gui.Gui2Players();
    }
}