package be.ac.umons.student.fouretaltruy.quoridor;

import java.util.*;
import java.io.*;

public class QuoridorPathFind implements Serializable
{
    private static final long serialVersionUID = 1L;
    private QuoridorTray tray, alreadyFind;
    private QuoridorGame game;
    private QuoridorPlayers[] players;
    private QuoridorMovePlayer movePlayer;
    private int win=17;
    public QuoridorPathFind(QuoridorGame _game)
    {
        game=_game;
        players=game.getAllPlayers();
        tray=game.getTray();
        win=17;
    }
    public boolean verif()
    {
        ArrayList<QuoridorPlayers> player;
        boolean verification=false;
        int nbVerifPlayer;
        for (int a=0; a<2; a++)
        {
            if (a==0)
            {
                players[1].rem();
            }
            else if (a==1)
            {
                players[0].rem();
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
                    movePlayer = new QuoridorMovePlayer(player.get(num), tray);
                    boolean[][][] choix = movePlayer.setPosAvailable();
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
    public void verif2()
    {
        HashMap<QuoridorPlayers, QuoridorPlayers> paths = new HashMap<QuoridorPlayers, QuoridorPlayers>();
        ArrayList<QuoridorPlayers> player;
        QuoridorPlayers playerWin=null;
        int nbVerifPlayer;
        alreadyFind= new QuoridorTray(game);
        player = new ArrayList<QuoridorPlayers>();
        player.add(new QuoridorPlayers(alreadyFind, 0, "", players[1].getPos_x(), players[1].getPos_y()));
        nbVerifPlayer=player.size();
        while (nbVerifPlayer>0)
        {
            nbVerifPlayer=player.size();
            for (int num=0; num<nbVerifPlayer; num++)
            {
                movePlayer = new QuoridorMovePlayer(player.get(num), tray);
                boolean[][][] choix = movePlayer.setPosAvailable();
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
                                if (x==1)
                                {
                                    playerWin=player.get(player.size()-1);
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
        QuoridorTray waw = new QuoridorTray(game);
        boolean verif=true;
        while (verif)
        {
            nextPos=movingPlayer;
            movingPlayer=paths.get(movingPlayer);
            new QuoridorPlayers(waw, 1, "", movingPlayer.getPos_x(), movingPlayer.getPos_y());
            if (movingPlayer.getPos_x()==players[1].getPos_x() && movingPlayer.getPos_y()==players[1].getPos_y())
            {
                verif=false;
            }
        }
        players[1].setPos(nextPos.getPos_x(), nextPos.getPos_y());
    }
}