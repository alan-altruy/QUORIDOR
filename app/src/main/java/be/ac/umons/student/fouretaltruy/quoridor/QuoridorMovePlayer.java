package be.ac.umons.student.fouretaltruy.quoridor;

import java.io.*;
public class QuoridorMovePlayer implements Serializable
{
    private static final long serialVersionUID = 1L;
    private QuoridorPlayers player;
    private QuoridorTray tray;
    private int pos_x, pos_y;
    private boolean[] choix={false,false,false,false,false,false,false,false,false,false,false,false}; 
    private String[] select={"Nord","Nord-Est","Est","Sud-Est","Sud","Sud-Ouest","Ouest","Nord-Ouest", "Nord-Nord","Est-Est","Sud-Sud","Ouest-Ouest"}; //utilisée initialement pour jouer depuis le terminal avec une interface rudimentaire
    public QuoridorMovePlayer(QuoridorPlayers _player, QuoridorTray _tray)
    {
        player=_player;
        tray=_tray;
        pos_x=player.GetPos_x();
        pos_y=player.GetPos_y();
    }
    public void MoveIt(int choix)
    {
        int x=0,y=0;
        switch (choix)
        {
        case 0:
            x=-2;
            break;
        case 1:
            x=-2; y=2;
            break;
        case 2:
            y=2;
            break;
        case 3:
            x=2; y=2;
            break;
        case 4:
            x=2;
            break;
        case 5:
            x=2; y=-2;
            break;
        case 6:
            y=-2;
            break;
        case 7:
            x=-2; y=-2;
            break;
        case 8:
            x=-4;
            break;
        case 9:
            y=4;
            break;
        case 10:
            x=4;
            break;
        case 11:
            y=-4;
            break;
        }
        player.NewPos(x,y);
        tray.ChangeType_inCell(pos_x, pos_y, 0);
        tray.ChangeType_inCell(pos_x+x, pos_y+y, player.GetNum()+1);
    }
    public void YourChoice()
    {
        //utilisée initialement pour jouer depuis le terminal avec une interface rudimentaire
        for (int x=0; x<12; x++)
        {
            if (choix[x])
            {
                System.out.println(x+". "+select[x]);
            }
        }
    }
    public void WhereCanMove()
    {
        //Se deplacer vers le haut, donc qu'il n'y ait pas de Barrière au dessus
            if (tray.GetTypeIn_Cell(pos_x-1, pos_y)==0)
            {
             //Y a-t-il un joueur
                if (tray.GetTypeIn_Cell(pos_x-2, pos_y)!=0)
                {
                    // Si oui, regarder si barrière au dessus de l'autre joueur
                    if (tray.GetTypeIn_Cell(pos_x-3, pos_y)!=0)
                    {
                        if (tray.GetTypeIn_Cell(pos_x-2, pos_y-1)==0)
                        {
                            choix[7]=true;
                        }
                        if (tray.GetTypeIn_Cell(pos_x-2, pos_y+1)==0)
                        {
                            choix[1]=true;
                        }
                    }
                    else
                    {
                        choix[8]=true;
                    }
                }
                else
                {
                    choix[0]=true;
                }
            }
        //Se deplacer vers la droite, donc qu'il n'y ait pas de Barrière à droite
            if (tray.GetTypeIn_Cell(pos_x, pos_y+1)==0)
            {
             //Y a-t-il un joueur
                if (tray.GetTypeIn_Cell(pos_x, pos_y+2)!=0)
                {
                    // Si oui, regarder si barrière à droite de l'autre joueur
                    if (tray.GetTypeIn_Cell(pos_x, pos_y+3)!=0)
                    {
                        if (tray.GetTypeIn_Cell(pos_x-1, pos_y+2)==0)
                        {
                            choix[1]=true;
                        }
                        if (tray.GetTypeIn_Cell(pos_x+1, pos_y+2)==0)
                        {
                            choix[3]=true;
                        }
                    }
                    else
                    {
                        choix[9]=true;
                    }
                }
                else
                {
                    choix[2]=true;
                }
            }
        //Se deplacer vers le bas, donc qu'il n'y ait pas de Barrière en bas
            if (tray.GetTypeIn_Cell(pos_x+1, pos_y)==0)
            {
             //Y a-t-il un joueur
                if (tray.GetTypeIn_Cell(pos_x+2, pos_y)!=0)
                {
                    // Si oui, regarder si barrière en dessous de l'autre joueur
                    if (tray.GetTypeIn_Cell(pos_x+3, pos_y)!=0)
                    {
                        if (tray.GetTypeIn_Cell(pos_x+2, pos_y-1)==0)
                        {
                            choix[3]=true;
                        }
                        if (tray.GetTypeIn_Cell(pos_x+2, pos_y+1)==0)
                        {
                            choix[5]=true;
                        }
                    }
                    else
                    {
                        choix[10]=true;
                    }
                }
                else
                {
                    choix[4]=true;
                }
            }
        //Se deplacer vers la gauche, donc qu'il n'y ait pas de Barrière à gauche
            if (tray.GetTypeIn_Cell(pos_x, pos_y-1)==0)
            {
             //Y a-t-il un joueur
                if (tray.GetTypeIn_Cell(pos_x, pos_y-2)!=0)
                {
                    // Si oui, regarder si barrière à gauche de l'autre joueur
                    if (tray.GetTypeIn_Cell(pos_x, pos_y-3)!=0)
                    {
                        if (tray.GetTypeIn_Cell(pos_x-1, pos_y-2)==0)
                        {
                            choix[5]=true;
                        }
                        if (tray.GetTypeIn_Cell(pos_x+1, pos_y-2)==0)
                        {
                            choix[7]=true;
                        }
                    }
                    else
                    {
                        choix[11]=true;
                    }
                }
                else
                {
                    choix[6]=true;
                }
            }
        }
        public boolean[] getChoix()
        {
            return choix;
        }
        public boolean[][][] setPosAvailable()
        {
            boolean[][][] posAvailable= new boolean[19][19][12];
            WhereCanMove();
            for (int x=0; x<19; x++)
            {
                for (int y=0; y<19; y++)
                {
                    for (int z=0; z<12; z++)
                    {
                        posAvailable[x][y][z]=false;
                    }
                }
            }
            if (choix[0])
            {
                posAvailable[pos_x-2][pos_y][0]=true;
            }
            if (choix[1])
            {
                posAvailable[pos_x-2][pos_y+2][1]=true;
            }
            if (choix[2])
            {
                posAvailable[pos_x][pos_y+2][2]=true;
            }
            if (choix[3])
            {
                posAvailable[pos_x+2][pos_y+2][3]=true;
            }
            if (choix[4])
            {
                posAvailable[pos_x+2][pos_y][4]=true;
            }
            if (choix[5])
            {
                posAvailable[pos_x+2][pos_y-2][5]=true;
            }
            if (choix[6])
            {
                posAvailable[pos_x][pos_y-2][6]=true;
            }
            if (choix[7])
            {
                posAvailable[pos_x-2][pos_y-2][7]=true;
            }
            if (choix[8])
            {
                posAvailable[pos_x-4][pos_y][8]=true;
            }
            if (choix[9])
            {
                posAvailable[pos_x][pos_y+4][9]=true;
            }
            if (choix[10])
            {
                posAvailable[pos_x+4][pos_y][10]=true;
            }
            if (choix[11])
            {
                posAvailable[pos_x][pos_y-4][11]=true;
            }
            return posAvailable;
        }
    }