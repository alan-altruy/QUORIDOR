package be.ac.umons.student.fouretaltruy.quoridor;/// ALTRUY ALAN - JASON FOURET //
import java.io.*;
public class QuoridorGame implements Serializable
{
    private static final long serialVersionUID = 1L;
	/**
		*  Le nombre de joueurs dans le jeu
		* 
        */
    private int nbPlayers=2;
    /**
		*  Le numéro du joueur qui est en train de jouer
		* 
		*/
    public int playerWhoIsPlaying;
    /**
		*  Permet de savoir si le joueur a fait une action
		* 
		*/ 
    public boolean wait=true;
    /**
		*  Ajouter le plateau (QuoridorTray au jeu)
		* 
        */
    public boolean newGame=false;
    public boolean loaded=false;
    public QuoridorTray tray;

    /**
		*  Ajouter l'interface (QuoridorGui) au jeu
		* 
		*/
    public QuoridorGui gui = new QuoridorGui(this);
    /**
		*  Contient les joueurs du jeu
		* 
		*/
    public QuoridorPlayers[] players;
    public QuoridorFence fence;
    /**
         * Permet d'initialiser le jeu:
         * 
         *   - Créer un afficheur/interface graphique (QuoridorGui)
         * 
         */
    public void showStartScreen()
    {
        tray = new QuoridorTray();
        fence = new QuoridorFence(this);
        gui.StartScreen();
    }
    public void loadSave()
    {
        try {
            ObjectInputStream objectIn = new ObjectInputStream( new FileInputStream("src/main/resources/save.ser"));
            QuoridorGame gamesave = (QuoridorGame)objectIn.readObject();
            nbPlayers=gamesave.nbPlayers;
            players=gamesave.players;
            tray=gamesave.tray;
            fence= new QuoridorFence(this);
            nbPlayers=gamesave.nbPlayers;
            playerWhoIsPlaying=gamesave.playerWhoIsPlaying;
            objectIn.close();
            loaded=true;
            wait=false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean  canLoadSave()
    {
        try {
            ObjectInputStream objectIn = new ObjectInputStream( new FileInputStream("src/main/resources/save.ser"));
            objectIn.close();
            return true;
        } catch (Exception ex) {
            System.out.println("No save found");
        }
        return false;
    }
    /**
         * Permet d'initialiser plusieurs instances players de QuoridorPlayers
         * et modifier le type de la cellule qui contient à préent un joueur
         * 
         */
    public void Init2Players()
    {
        String name;
        int pos_x=1;
        int pos_y=9;
        players=new QuoridorPlayers[nbPlayers];
        for (int num=0; num<nbPlayers; num++) // Chaque itération permet d'initialiser un joueur
        {
            name="steve";
            if (num==1)
            {
                pos_x=17;
                name="creeper";
            }
            players[num]= new QuoridorPlayers(num, name, pos_x, pos_y);
            tray.ChangeType_inCell(pos_x,pos_y, num+1);
        }
        wait=false;
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
        gui.Gui2Players(num);
    }
    public void showWinner(int num)
    {
        gui.GuiWinner(num);
    }
    public String GetNameOfPlayer(int num)
    {
        return players[num].GetName();
    }
    public int getNbPlayers()
    {
		return nbPlayers;
    }
    public QuoridorPlayers getPlayers (int numPlayer)
    {
        return players[numPlayer];
    }
    public int NbFencesPlayer(int num)
    {
        return players[num].GetUsedFences();
    }
    public void setNbPlayers(int newNb)
    {
        nbPlayers=newNb;
        wait=false;
    }
    public void setWait(boolean _wait)
    {
        wait=_wait;
    }
}
