package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;

public class QuoridorPanel
{
    private Dimension dimScreen= Toolkit.getDefaultToolkit().getScreenSize();
    private int poseInitX, poseInitY, dimXTray, dimYTray, dimXScreen, dimYScreen, dimPion, PlayerWhoIsPlaying;
    private String[] Theme1={"pionrouge.png","pionnoir.png","tray.png","fenceV.png","fenceH.png"};
    private Font police;
    public JFrame windows;
    public JPanel panel;
    private QuoridorGame game;
    private QuoridorButton but;
    private QuoridorPicture pic;
    private QuoridorGui gui;
    private QuoridorCell[][] cells;
    private boolean[][][]posAvailable= new boolean[19][19][12];
    private QuoridorMovePlayer movePlayer;
    public QuoridorPanel(QuoridorGui _gui, QuoridorGame _game)
    {
        gui=_gui;
        windows=gui.windows;
        game=_game;
        cells=game.tray.cells;
        panel=new JPanel();
        panel.setLayout(null);
        dimXScreen=(int)dimScreen.getWidth();
        dimYScreen=(int)dimScreen.getHeight();
        dimXTray=(((int)(dimYScreen/100)-1)*100);
        dimYTray=dimXTray;
        dimPion=(int)(dimXTray/9);
        poseInitX=((int)(dimXScreen/2-dimXTray/2));
        poseInitY=((int)(dimYScreen/2-dimYTray/2));
        police = new Font("FreeSans", Font.BOLD, 38);
    }
    public void MainMenu()
    {
        QuoridorButton but1Player,but2Player;
        but1Player = new QuoridorButton(this,"1 JOUEUR",police, 1, (int)(dimXScreen/2)-250, (int)(dimYScreen*0.33)-100,500,200);
        but2Player = new QuoridorButton(this,"2 JOUEURS",police, 2, (int)(dimXScreen/2)-250, (int)(dimYScreen*0.66)-100,500,200);
        but1Player.setButton();
        but2Player.setButton();
        windows.setContentPane(panel);
    }
    public void TwoPlayers(int num)
    {
        PlayerWhoIsPlaying=num;
        but = new QuoridorButton(this, "FENCE", police, 5, (int)((dimXScreen-dimXTray)/4)-50, (int)(dimYScreen/2)-25, 100, 50);
        but.setButton();
        pic=new QuoridorPicture(this, Theme1[2], poseInitX,poseInitY, dimXTray, dimYTray);
        posObjects(num);
        windows.setContentPane(panel);
    }
    public void setAction(int choice)
    {
        if (choice==2)
        {
            gui.setNbPlayers(2);
        }
        else if (choice==3 || choice==4)
        {
            movePlayer = new QuoridorMovePlayer(game.getPlayers(PlayerWhoIsPlaying), game.tray);
            canMove(choice);
        }
        else if (choice==5)
        {
            canMove(choice);
        }
    }
    public void canMove(int choice)
    {
        int pos_x,pos_y;
        if (choice!=5)
        {
            posAvailable=movePlayer.setPosAvailable();
        }
        for (int ligne=0; ligne<19;ligne++)
        {
            for (int cols=0; cols<19; cols++)
            {
                for (int z=0; z<12; z++)
                {
                    pos_x=poseInitX+(dimPion*((cols-1)/2))+5;
                    pos_y=poseInitY+(dimPion*((ligne-1)/2))+5;
                    if (choice!=5)
                    {
                        if (posAvailable[ligne][cols][z] && (choice==3 || choice==4))
                        {
                            but= new QuoridorButton(this, "o", police, z+20 , pos_x, pos_y, dimPion, dimPion);
                            but.setPlayerButton();
                        }
                    }
                    else if (choice==5 && cols>0 && cols<18 && ligne>0 && ligne<18)
                    {
                        if (cols%2==0 && ligne%2==1)
                        {
                            but= new QuoridorButton(this, "", police, 100 , pos_x+dimPion-(int)(dimPion/8), pos_y, (int)(dimPion/4), dimPion);
                            but.setFenceButton(ligne,cols, 0);
                        }
                        else if (cols%2==1 && ligne%2==0)
                        {
                            but= new QuoridorButton(this, "", police, 100 , pos_x, pos_y+dimPion-(int)(dimPion/8), dimPion, (int)(dimPion/4));
                            but.setFenceButton(ligne,cols, 1);
                        }
                    }
                }
            }
        }
        windows.setContentPane(panel);
    }
    public void addFenceInGame(int pos_x, int pos_y, int dir)
    {
        game.NewFence(pos_x,pos_y,dir);
    }
    public void posObjects(int numPlayer)
    {
        int pos_x, pos_y;
        int typeOfCell;
        for (int ligne=0; ligne<19;ligne++)
        {
            for (int cols=0; cols<19; cols++)
            {
                typeOfCell=cells[ligne][cols].GetType();
                pos_x=poseInitX+(dimPion*((cols-1)/2))+5;
                pos_y=poseInitY+(dimPion*((ligne-1)/2))+5;
                if (typeOfCell==1 || typeOfCell==2)
                {
                    if (numPlayer==1 && typeOfCell==2)
                    {
                        but= new QuoridorButton(this, "", police, numPlayer+3, pos_x, pos_y, dimPion, dimPion);
                        but.setPlayerButton();
                    }
                    else if (numPlayer==0 && typeOfCell==1)
                    {
                        but= new QuoridorButton(this, "", police, numPlayer+3, pos_x, pos_y, dimPion, dimPion);
                        but.setPlayerButton();
                    }
                    pic=new QuoridorPicture(this, Theme1[typeOfCell-1], pos_x, pos_y, dimPion, dimPion);
                }
                else if (typeOfCell==3 || typeOfCell==4)
                {
                    if ((cols%2==1 && ligne%2==0) || (cols%2==0 && ligne%2==1))
                    {
                        if (typeOfCell==3)
                        {
                            pic=new QuoridorPicture(this, Theme1[typeOfCell], pos_x+(int)(dimPion/2), pos_y, dimPion, dimPion);
                        }
                        else
                        {
                            pic=new QuoridorPicture(this, Theme1[typeOfCell], pos_x, pos_y+(int)(dimPion/2), dimPion, dimPion);
                        }
                    }
                }
            }
        }
    }
    public void movePlayer(int numObj)
    {
        movePlayer.MoveIt(numObj);
        game.setWait(false);
    }
}