package be.ac.umons.student.fouretaltruy.quoridor;/// ALTRUY ALAN - JASON FOURET //
import java.io.*;
public class QuoridorGame implements Serializable
{
    private static final long serialVersionUID = 1L;
	/**
		*  Le nombre de joueurs dans le jeu
		* 
        */
    private int nbPlayers=0;
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
    public boolean closeGame=false;
    public boolean newGame=false;
    public boolean loaded=false;
    public QuoridorTray tray = new QuoridorTray();

    /**
		*  Ajouter l'interface (QuoridorGui) au jeu
		* 
		*/
    public QuoridorGui gui = new QuoridorGui(this);
    /**
		*  Contient les joueurs du jeu
		* 
		*/
    private QuoridorPlayers[] players;
    /**
         * Permet d'initialiser le jeu:
         * 
         *   - Créer un afficheur/interface graphique (QuoridorGui)
         * 
         */
    public void showStartScreen()
    {
        gui.StartScreen();
    }
    public void InitPlayers()
	{
		switch (nbPlayers)
		{
			case 2:
				Init2Players();
				break;
		}
    }
    public void loadSave()
    {
        try {
            ObjectInputStream objectIn = new ObjectInputStream( new FileInputStream("src/main/resources/save.ser"));
            QuoridorGame gamesave = (QuoridorGame)objectIn.readObject();
            nbPlayers=gamesave.nbPlayers;
            players=gamesave.players;
            tray=gamesave.tray;
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
            ex.printStackTrace();
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
    public void showWinner(int num)
    {
        gui.GuiWinner(num);
        while(wait)
        {
            System.out.println("End Of Game");
        }
        wait=true;
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
    public void close()
    {
        wait=false;
        closeGame=true;
        gui.windows.setVisible(false);
        gui.windows.dispose();
    }
    public void setWait(boolean _wait)
    {
        wait=_wait;
    }
}
