package be.ac.umons.student.fouretaltruy.quoridor;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class QuoridorPathFind implements Serializable
{
    private static final long serialVersionUID = 1L;
    private QuoridorTray tray, alreadyFind= new QuoridorTray();
    private QuoridorGame game;
    private QuoridorPlayers[] players;
    private QuoridorMovePlayer movePlayer;
    private int win=1;
    private int ligne, cols, dir;
    public QuoridorPathFind(QuoridorGame _game, int pos_x, int pos_y, int _dir)
    {
        game=_game;
        players=game.players;
        tray=game.tray;
        ligne=pos_x;
        cols=pos_y;
        dir=_dir;
    }
    public boolean verif()
    {
        ArrayList<QuoridorPlayers> player;
        boolean verification=false;
        int nbVerifPlayer;
        for (int a=0; a<2; a++)
        {
            player = new ArrayList<QuoridorPlayers>();
            player.add(players[a]);
            nbVerifPlayer=player.size();
            alreadyFind.cells[player.get(0).GetPos_x()][player.get(0).GetPos_y()].ChangeType(1);
            while (nbVerifPlayer>0)
            {
                for (int num=0; num<nbVerifPlayer; num++)
                {
                    movePlayer = new QuoridorMovePlayer(player.get(0), tray);
                    boolean[][][] choix = movePlayer.setPosAvailable();
                    for (int x=0; x<19;x++)
                    {
                        for (int y=0; y<19; y++)
                        {
                            for (int z=0; z<12; z++)
                            {
                                if (choix[x][y][z] && alreadyFind.cells[x][y].GetType()==0)
                                {
                                    player.add(new QuoridorPlayers(1, "", x, y));
                                    alreadyFind.cells[x][y].ChangeType(1);
                                    if (x==win)
                                    {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                    player.remove(0);
                    nbVerifPlayer=player.size();
                }
            }
            if (!verification)
            {
                return verification;
            }
        }
        return verification;
    }
}