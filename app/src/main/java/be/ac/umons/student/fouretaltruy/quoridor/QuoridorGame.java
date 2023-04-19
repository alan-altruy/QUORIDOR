package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //

import java.io.*;

/**
 * Le jeu du Quoridor qui est implémenté de Serializable
 */
public class QuoridorGame implements Serializable
{
    private static final long serialVersionUID = 1L;
	/**
		*  Le nombre de joueurs humains dans le jeu
        */
    private int nbPlayers;
    /**
		*  Le numéro du joueur qui est en train de jouer
		*/
    private int playerWhoIsPlaying;
    /**
		*  Permet de savoir si le joueur a fait une action
		*/ 
    private boolean wait=true;
    /**
		*  Plateau du jeu
        */
    private QuoridorTray tray;
    /**
		*  Permet de savoir si le jeu doit recommencer
        */
    private boolean newGame=false;
    /**
		*  Permet de savoir si une sauvegarde doit être chargée
        */
    private boolean loaded=false;
    /**
		* Interface graphique du jeu
		*/
    private QuoridorGui gui = new QuoridorGui(this);
    /**
		*  Les joueurs du jeu
        */
    private QuoridorPlayers[] players;
    /**
		*  L'intelligence artificielle du jeu
        */
    private QuoridorAI ai= null;
    //private QuoridorSound sound= new QuoridorSound();
    /**
         * Permet d'initialiser l'écran d'accueil:<p>
         *   <ul><li>Créer le plateau (QuoridorTray)</li>
         *   <li>Afficher </li></ul>
         */
    public void showStartScreen()
    {
        gui.startScreen();
    }
    /**
         * Permet de sauvegarder le jeu dans un fichier save.ser
         */
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
    /**
         * Permet de charger une sauvegarde
         */
    public void loadSave()
    {
        try {
            ObjectInputStream objectIn = new ObjectInputStream( new FileInputStream("src/main/resources/save.ser"));
            QuoridorGame gamesave = (QuoridorGame)objectIn.readObject();
            players=gamesave.getAllPlayers();
            tray=gamesave.getTray();
            nbPlayers=gamesave.getNbPlayers();
            ai=gamesave.getAi();
            playerWhoIsPlaying=gamesave.getPlayerWhoIsPlaying();
            objectIn.close();
            loaded=true;
            wait=false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(" /!\\ Save Error /!\\");
        }
    }
    /**
         * Permet de savoir si un fichier sauvegarde (save.ser) existe
         * @return True si on peut charger une partie
         * <li>False si on ne peut pas charger une partie</li>
         */
    public boolean canLoadSave()
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
         * Permet d'initialiser deux joueurs humains
         */
    public void init2Players()
    {
        String name;
        int pos_x=1;
        int pos_y=9;
        nbPlayers=2;
        tray = new QuoridorTray(this);
        players=new QuoridorPlayers[2];
        for (int num=0; num<nbPlayers; num++) // Chaque itération permet d'initialiser un joueur
        {
            name="steve";
            if (num==1)
            {
                pos_x=17;
                name="creeper";
            }
            players[num]= new QuoridorPlayers(tray, num, name, pos_x, pos_y);
        }
        wait=false;
    }
    /**
         * Permet d'initialiser un joueur et une intelligence artificielle
         * @param hardIA : difficulté de l'intelligence artificielle
         * <ul><li>False : Normal</li><li>True : Difficile</li></ul>
         */
    public void initAI(boolean hardIA)
    {
        int pos_x=1;
        int pos_y=9;
        nbPlayers=1;
        tray = new QuoridorTray(this);
        players=new QuoridorPlayers[2];
        players[0]= new QuoridorPlayers(tray, 0, "steve", pos_x, pos_y);
        pos_x=17;
        ai = new QuoridorAI(this, 1, "creeper", pos_x, pos_y, hardIA);
        players[1]= (QuoridorPlayers) ai;
        wait=false;
    }
    /**
         * Permet de savoir s'il y a un gagnant parmis les joueurs
         * @return Le numéro du joueur gagant s'il y en a un
         * <li> sinon 3</li>
         */
    public int hasWinner()
    {
        for (int nb=0; nb<2; nb++)
        {
            if (players[nb].areYouTheWinner())
            {
                return nb;
            }
        }
        return 3;
    }
    /**
         * Permet à un joueur de faire une action (soit ajouter une barrière, soit bouger son pion)
         * @param num
         *      Le numero du joueur en train de jouer.
         */
    public void playerAction(int num)
    {
        playerWhoIsPlaying=num;
        gui.gui2Players();
        if (nbPlayers==1 && num==1)
        {
            ai.action();
            wait=false;
        }
        gui.gui2Players();
    }
    /**
         * Demande à l'interface graphique d'afficher le joueur gagant
         * @param winner : Numéro du joueur gagant
         */
    public void showWinner(int winner)
    {
        gui.guiWinner(winner);
    }
    /**
         * Permet de retourner le nom d'un joueur
         * @param num : Numéro du joueur
         * @return Le nom du joueur
         */
    public String getNameOfPlayer(int num)
    {
        return players[num].getName();
    }
    /**
         * Permet de rtourner le nombre de joueurs humains dans le jeu
         * @return Le nombre de joueurs humains
         */
    public int getNbPlayers()
    {
		return nbPlayers;
    }
    /**
         * Permet de modifier le nombre de joueus humains dans le jeu
         * @param _nbPlayers : Nombre de joueurs
         */
    public void setNbPlayers(int _nbPlayers)
    {
		nbPlayers = _nbPlayers;
    }
    /**
         * Permet de retourner le nombre de barrières utilisées par un joueur
         * @param numOfPlayer : Le numéro du joueur
         * @return Le nombre de barrières utilisées par le joueur
         */
    public int getUsedFencesPlayer(int numOfPlayer)
    {
        return players[numOfPlayer].getUsedFences();
    }
    /**
         * Permet d'incrémenter le nombre de barrières du joueur qui est occupé de jouer
         */
    public void setUsedFencesPlayer()
    {
        players[playerWhoIsPlaying].setUsedFences();
    }
    /**
         * Permet de modifier l'état de l'action du joueur
         * @param _wait : True si le joueur a joué, False sinon
         */
    public void setWait(boolean _wait)
    {
        wait=_wait;
    }
    /**
         * Permet de retourner l'état de l'action du joueur
         * @return True: il a déjà joué
         * <li>False: il n'a pas encore joué</li>
         */
    public boolean getWait()
    {
        return wait;
    }
    /**
         * Permet de savoir si un nouveau jeu a été demandé
         * @return True si un nouveau jeu a été demandé<li>False sinon</li>
         */
    public boolean getNewGame()
    {
        return newGame;
    }
    /**
         * Permet de retourner le numéro du joueur qui est en train de jouer
         * @return Le numéro du joueur
         */
    public int getPlayerWhoIsPlaying()
    {
        return playerWhoIsPlaying;
    }
    /**
         * Permet de changer l'état de newGame
         * @param _newGame Le nouvel état de newGame
         */
    public void setNewGame(boolean _newGame)
    {
        newGame=_newGame;
    }
    /**
         * Permet de changer l'état de loaded
         * @param _loaded Le nouvel état de loaded
         */
    public void setLoaded(boolean _loaded)
    {
        loaded=_loaded;
    }
    /**
         * Permet de savoir si une sauvegarde a été chargée
         * @return True si une sauvegarde a été chargée<li>False sinon</li>
         */
    public boolean getLoaded()
    {
        return loaded;
    }
    /**
         * Permet de retourner le plateau du jeu
         * @return Le plateau du jeu
         */
    public QuoridorTray getTray()
    {
        return tray;
    }
    /**
         * Permet de retourner un joueur du jeu
         * @param numOfPlayer : Le numéro du joueur
         * @return Le joueur
         */
    public QuoridorPlayers getPlayer(int numOfPlayer)
    {
        return players[numOfPlayer];
    }
    /**
         * Permet de retourner une intelligence artificielle
         * @return L'intelligence artificielle
         */
    public QuoridorAI getAi()
    {
        return ai;
    }
    /**
         * Permet de retourner tous les joueurs du jeu
         * @return Tous les joueurs
         */
    public QuoridorPlayers[] getAllPlayers()
    {
        return players;
    }
    /**
         * Permet d'afficher la fenêtre du jeu
         */
    public void showGui()
    {
        gui.showGui();
    }
    /**
         * Permet de faire patienter la fenêtre le temps qu'un joueur fasse une action
         */
    public void waitGui()
    {
        gui.waitGui();
    }
    /**
         * Permet de lancer un son
         * @param numOfSong : Le numéro du son
         */
    public void playSound(int numOfSong)
    {
        //sound.play(numOfSong);
    }
}