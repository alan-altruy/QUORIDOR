package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //
import java.util.Scanner;
public class QuoridorGame
{
    private int nbPlayers=0, nbFences=0;
    private int boucle;
    private int playerWhoIsPlaying;
    public QuoridorTray tray;
    private QuoridorPlayers[] players;
    private QuoridorGui gui;
    private boolean wait=true;
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
        gui.StartScreen();
        while (nbPlayers<=0)
        {
            System.out.println("Waiting Actionfezgzgezezg");
        }
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
            name="player"+(num+1);
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
        playerWhoIsPlaying=num;
        System.out.println("ok");
        gui.Gui2Players(num);
        while(wait)
        {
            System.out.println("Waiting Action");
        }
        wait=true;
    }
    /**
         * Permet d'ajouter une barrière mais avant tout de vérifier si c'est possible d'en ajouter une.
         * 
         * @param num
         *            Le numero associé au joueur.
         */
    public void NewFence(int pos_x,int pos_y,int dir)
    {
        if(VerifFence(pos_x,pos_y,dir))
        {
            if (players[playerWhoIsPlaying].GetUsedFences()<10)
            {
                nbFences++;
                players[playerWhoIsPlaying].setUsedFences();
                tray.AddFence(pos_x, pos_y, dir);
                wait=false;
            }
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
        gui.Gui2Players(num);
    }
    public int getNbPlayers()
    {
		return nbPlayers;
    }
    public void showWinner(int num)
    {
        gui.GuiWinner(num);
        while(wait)
        {
            System.out.println("End Of Game");
        }
        wait=true;
    }
    public int getNbFences()
    {
		return nbFences;
    }
    public void setNbPlayers(int newNb)
    {
        nbPlayers=newNb;
    }
    public QuoridorPlayers getPlayers (int numPlayer)
    {
        return players[numPlayer];
    }
    public void setWait(boolean _wait)
    {
        wait=_wait;
    }
}