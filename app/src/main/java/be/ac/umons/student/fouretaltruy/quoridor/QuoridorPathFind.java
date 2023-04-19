package be.ac.umons.student.fouretaltruy.quoridor;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class QuoridorPathFind implements Serializable
{
    private static final long serialVersionUID = 1L;
    private QuoridorTray tray, alreadyFind;
    private QuoridorGame game;
    private QuoridorPlayers[] players;
    private QuoridorMovePlayer movePlayer;
    private int win=17;
    private int ligne, cols, dir;
    public QuoridorPathFind(QuoridorGame _game)
    {
        game=_game;
        players=game.players;
        tray=game.tray;
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
                tray.cells[players[1].GetPos_x()][players[1].GetPos_y()].ChangeType(0);
            }
            else if (a==1)
            {
                tray.cells[players[0].GetPos_x()][players[0].GetPos_y()].ChangeType(0);
            }
            alreadyFind= new QuoridorTray();
            verification=false;
            player = new ArrayList<QuoridorPlayers>();
            player.add(new QuoridorPlayers(1, "", players[a].GetPos_x(), players[a].GetPos_y()));
            nbVerifPlayer=player.size();
            alreadyFind.cells[player.get(0).GetPos_x()][player.get(0).GetPos_y()].ChangeType(1);
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
                                if (choix[x][y][z] && alreadyFind.cells[x][y].GetType()==0)
                                {
                                    player.add(new QuoridorPlayers(1, "", x, y));
                                    alreadyFind.cells[x][y].ChangeType(1);
                                    if (x==win)
                                    {
                                        verification=true;
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
            if (a==0)
            {
                tray.cells[players[1].GetPos_x()][players[1].GetPos_y()].ChangeType(2);
            }
            else if (a==1)
            {
                tray.cells[players[0].GetPos_x()][players[0].GetPos_y()].ChangeType(1);
            }
            win=1;
            if (!verification)
            {
                return verification;
            }
        }
        return verification;
    }
}
