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
    private int playerWhoIsPlaying;
    /**
		*  Permet de savoir si le joueur a fait une action
		* 
		*/ 
    private boolean wait=true;
    /**
		*  Ajouter le plateau (QuoridorTray au jeu)
		* 
        */
    private boolean newGame=false;
    private boolean loaded=false;
    private QuoridorTray tray;

    /**
		*  Ajouter l'interface (QuoridorGui) au jeu
		* 
		*/
    private QuoridorGui gui = new QuoridorGui(this);
    /**
		*  Contient les joueurs du jeu
		* 
		*/
    private QuoridorPlayers[] players;
    private QuoridorFence fence;
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
        gui.startScreen();
    }
    public void save()
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/save.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            System.out.println("Game has been saved!");
            newGame=true;
            wait=false;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void loadSave()
    {
        try {
            ObjectInputStream objectIn = new ObjectInputStream( new FileInputStream("src/main/resources/save.ser"));
            QuoridorGame gamesave = (QuoridorGame)objectIn.readObject();
            players=gamesave.getAllPlayers();
            tray=gamesave.getTray();
            fence= new QuoridorFence(this);
            playerWhoIsPlaying=gamesave.getPlayerWhoIsPlaying();
            objectIn.close();
            loaded=true;
            wait=false;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(" /!\\ Save Error /!\\");
        }
    }
    public boolean  canLoadSave()
    {
        try {
            ObjectInputStream objectIn = new ObjectInputStream( new FileInputStream("src/main/resources/save.ser"));
            objectIn.close();
            System.out.println("Save correctly loaded");
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
    public void init2Players()
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
            tray.setTypeOfCell(pos_x,pos_y, num+1);
        }
        wait=false;
    }
    public int hasWinner()
    {
        for (int nb=0; nb<nbPlayers; nb++)
        {
            if (players[nb].areYouTheWinner())
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
    public void playerAction(int num)
    {
        playerWhoIsPlaying=num;
        gui.gui2Players();
    }
    public void showWinner(int num)
    {
        gui.guiWinner(num);
    }
    public String getNameOfPlayer(int num)
    {
        return players[num].getName();
    }
    public int getNbPlayers()
    {
		return nbPlayers;
    }
    public int getUsedFencesPlayer(int num)
    {
        return players[num].getUsedFences();
    }
    public void setUsedFencesPlayer()
    {
        players[playerWhoIsPlaying].setUsedFences();
    }
    public void setWait(boolean _wait)
    {
        wait=_wait;
    }
    public boolean getWait()
    {
        return wait;
    }
    public boolean getNewGame()
    {
        return newGame;
    }
    public int getPlayerWhoIsPlaying()
    {
        return playerWhoIsPlaying;
    }
    public void setNewGame(boolean _newGame)
    {
        newGame=_newGame;
    }
    public void setLoaded(boolean _loaded)
    {
        loaded=_loaded;
    }
    public boolean getLoaded()
    {
        return loaded;
    }
    public QuoridorTray getTray()
    {
        return tray;
    }
    public QuoridorPlayers getPlayer(int num)
    {
        return players[num];
    }
    public QuoridorPlayers[] getAllPlayers()
    {
        return players;
    }
    public QuoridorFence getFence()
    {
        return fence;
    }
    public QuoridorGui getGui()
    {
        return gui;
    }
}