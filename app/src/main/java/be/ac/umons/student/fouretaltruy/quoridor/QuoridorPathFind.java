package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //

import java.util.*;
import java.io.Serializable;

/**
 * La classe permettant à la fois de connaitre le chemin le plus court pour atteindre
 * une cellule gagante et de savoir si une barrière bloquerait le chemin d'un des joueurs.
 * Cette classe est implémentée de Serializable
 */
public class QuoridorPathFind implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
         * Plateau du jeu
         */
    private QuoridorTray tray;
    /**
         * Jeu executé
         */
    private QuoridorGame game;
    /**
         * Les joueurs jouant au jeu
         */
    private QuoridorPlayers[] players;
    /**
         * Ligne du plateau permettant de gagner 
         */
    private int win=17;
    /**
         * Initialise le pathFind du jeu
         * @param _game : Jeu executé
         */
    public QuoridorPathFind(QuoridorGame _game)
    {
        game=_game;
        players=game.getAllPlayers();
        tray=game.getTray();
        win=17;
    }
    /**
         * Permet de vérifier si une barrière peut être posée sans obstruer le passage des joueurs
         * @return True si le jeu permet aux deux joueurs de gagner
         * <li>False sinon</li>
         */
    public boolean verif()
    {
        ArrayList<QuoridorPlayers> player;
        QuoridorTray alreadyFind;
        boolean verification=false;
        int nbVerifPlayer;
        for (int a=0; a<2; a++)
        {
            if (a==0)
            {
                players[1].remove();
            }
            else if (a==1)
            {
                players[0].remove();
            }
            alreadyFind= new QuoridorTray(game);
            verification=false;
            player = new ArrayList<QuoridorPlayers>();
            player.add(new QuoridorPlayers(alreadyFind, 1, "", players[a].getPos_x(), players[a].getPos_y()));
            nbVerifPlayer=player.size();
            while (nbVerifPlayer>0)
            {
                nbVerifPlayer=player.size();
                for (int num=0; num<nbVerifPlayer; num++)
                {
                    boolean[][][] choix = new QuoridorMovePlayer(player.get(num), tray).setPosAvailable();
                    for (int x=0; x<19;x++)
                    {
                        for (int y=0; y<19; y++)
                        {
                            for (int z=0; z<7; z+=2)
                            {
                                if (choix[x][y][z] && alreadyFind.getTypeOfCell(x, y)==0)
                                {
                                    player.add(new QuoridorPlayers(alreadyFind, 1, "", x, y));
                                    if (x==win)
                                    {
                                        verification=true;
                                        num=nbVerifPlayer; x=19; y=19;z=7; nbVerifPlayer=0;
                                    }
                                }
                            }
                        }
                    }
                }
                for (int play=0; play<nbVerifPlayer; play++)
                {
                    player.remove(0);
                }
                //alreadyFind.show();
            }
            if (a==0)
            {
                players[1].set();
            }
            else if (a==1)
            {
                players[0].set();
            }
            win=1;
            if (!verification)
            {
                return verification;
            }
        }
        return verification;
    }
    /**
         * Permet de trouver le plus court chemin qu'un joueur a à effectuer pour gagner
         * @param numOfPlayer Numéro du joueur
         * @return Un tableau comprenant
         * <ul><li>La prochaine cellule (x,y) à atteindre par le chemin le plus court</li>
         * <li>Le nombre de déplacements nécessaires pour réussir</li></ul>
         */
    public int[] moveSmallPath(int numOfPlayer)
    {
        HashMap<QuoridorPlayers, QuoridorPlayers> paths = new HashMap<QuoridorPlayers, QuoridorPlayers>();
        ArrayList<QuoridorPlayers> player;
        QuoridorPlayers playerWin=null;
        QuoridorTray alreadyFind;
        int nbVerifPlayer;
        alreadyFind= new QuoridorTray(game);
        player = new ArrayList<QuoridorPlayers>();
        player.add(new QuoridorPlayers(alreadyFind, 0, "", players[numOfPlayer].getPos_x(), players[numOfPlayer].getPos_y()));
        nbVerifPlayer=player.size();
        if (numOfPlayer==1)
        {
            win=1;
        }
        while (nbVerifPlayer>0)
        {
            nbVerifPlayer=player.size();
            for (int num=0; num<nbVerifPlayer; num++)
            {
                boolean[][][] choix = new QuoridorMovePlayer(player.get(num), tray).setPosAvailable();
                for (int x=0; x<19; x++)
                {
                    for (int y=0; y<19; y++)
                    {
                        for (int z=0; z<12; z++)
                        {
                            if (choix[x][y][z] && alreadyFind.getTypeOfCell(x, y)==0)
                            {
                                player.add(new QuoridorPlayers(alreadyFind, 1, "", x, y));
                                paths.put(player.get(player.size()-1), player.get(num));
                                playerWin=player.get(player.size()-1);
                                if (x==win)
                                {
                                    num=nbVerifPlayer; x=19; y=19;z=12; nbVerifPlayer=0;
                                }
                            }
                        }
                    }
                }
            }
            for (int play=0; play<nbVerifPlayer; play++)
            {
                player.remove(0);
            }
        }
        QuoridorPlayers movingPlayer= playerWin, nextPos=null;
        boolean verif=true;
        int nbOfMoves=0;
        while (verif)
        {
            nbOfMoves++;
            nextPos=movingPlayer;
            movingPlayer=paths.get(movingPlayer);
            if (movingPlayer.getPos_x()==players[numOfPlayer].getPos_x() && movingPlayer.getPos_y()==players[numOfPlayer].getPos_y())
            {
                verif=false;
            }
        }
        return new int[]{nextPos.getPos_x(), nextPos.getPos_y(), nbOfMoves};
    }
}