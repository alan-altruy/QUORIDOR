package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;

public class QuoridorPanel
{
    private Dimension dimScreen= Toolkit.getDefaultToolkit().getScreenSize();
    private double poseInitX, poseInitY, dimXTray, dimYTray, dimXScreen, dimYScreen, dimPion, dimXFence,dimYFence;
    private int PlayerWhoIsPlaying;
    private String res="";
    private String[] Theme1={res+"pionrouge.png",res+"pionnoir.png",res+"tray.png",res+"fenceV.png",res+"fenceH.png",res+"valid.png"};
    private Font police;
    public JFrame windows;
    public JPanel panel;
    private QuoridorGame game;
    private QuoridorButton but;
    private QuoridorPicture pic, picOfTray;
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
        dimXScreen=dimScreen.getWidth();
        dimYScreen=dimScreen.getHeight();
        dimXTray=(dimYScreen-0.2*dimYScreen);
        dimYTray=dimXTray;
        dimPion=(dimXTray/37*3);
        dimXFence=(dimXTray/37*7);
        dimYFence=(dimYTray/37);
        poseInitX=(dimXScreen/2-dimXTray/2);
        poseInitY=(dimYScreen/2-dimYTray/2);
        police = new Font("FreeSans", Font.BOLD, 30);
    }
    public void MainMenu()
    {
        QuoridorButton but1Player,but2Player;
        but1Player = new QuoridorButton(this,"1 JOUEUR",police, 1, (dimXScreen/2)-250, (dimYScreen*0.33)-100,500,200);
        but2Player = new QuoridorButton(this,"2 JOUEURS",police, 2, (dimXScreen/2)-250, (dimYScreen*0.66)-100,500,200);
        but1Player.setButton();
        but2Player.setButton();
        windows.setContentPane(panel);
    }
    public void TwoPlayers(int num)
    {
        PlayerWhoIsPlaying=num;
        but = new QuoridorButton(this, "Au tour de joueur "+(num+1), police, 9, ((dimXScreen-dimXTray)/4)-200, (dimYScreen/4)-25, 400, 50);
        but.setPlayerButton();
        but = new QuoridorButton(this, "FENCE", police, 5, ((dimXScreen-dimXTray)/4)-100, (dimYScreen/2)-25, 200, 50);
        but.setButton();
        if (game.NbFencesPlayerMax(num))
        {
            but = new QuoridorButton(this, "Vous n'avez plus de barrières", police, 9, (dimXScreen/2)-300, ((dimYScreen-dimYTray)/2)-75, 600, 50);
            but.setPlayerButton();
        }
        posObjects(num);
        picOfTray=new QuoridorPicture(this, Theme1[2], poseInitX,poseInitY, dimXTray, dimYTray);
        windows.setContentPane(panel);
    }
    public void Winner(int num)
    {
        but = new QuoridorButton(this, "LE GAGANT EST LE JOUEUR "+(num+1), police, 9, (dimXScreen/2)-400, (dimYScreen/2)-30, 800, 60);
        but.setButton();
        windows.setContentPane(panel);
    }
    public void setAction(int choice)
    {
        if (choice==2)
        {
            game.setNbPlayers(2);
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
        double pos_x,pos_y;
        if (choice!=5)
        {
            posAvailable=movePlayer.setPosAvailable();  
        }
        picOfTray.remove();
        for (int ligne=0; ligne<19;ligne++)
        {
            for (int cols=0; cols<19; cols++)
            {
                for (int z=0; z<12; z++)
                {
                    if (choice!=5)
                    {
                        pos_x=poseInitX+(dimXTray/37*4)*((cols-1)/2)+(dimXTray/37);
                        pos_y=poseInitY+(dimYTray/37*4)*((ligne-1)/2)+(dimXTray/37);
                        if (posAvailable[ligne][cols][z] && (choice==3 || choice==4))
                        {
                            but= new QuoridorButton(this, "o", police, z+20 , pos_x, pos_y, dimPion, dimPion);
                            pic=new QuoridorPicture(this, Theme1[5], pos_x, pos_y, dimPion, dimPion);
                            but.setPlayerButton();
                        }
                    }
                    else if (choice==5 && cols>0 && cols<18 && ligne>0 && ligne<18)
                    {
                        pos_x=poseInitX+(dimXTray/37*4)*((cols)/2);
                        pos_y=poseInitY+(dimYTray/37*4)*((ligne)/2);
                        if (cols%2==0 && ligne%2==1)
                        {
                            but= new QuoridorButton(this, "", police, 100 , pos_x, pos_y+dimPion/3, (dimPion/3), dimPion);
                            but.setFenceButton(ligne,cols, 0);
                        }
                        else if (cols%2==1 && ligne%2==0)
                        {
                            but= new QuoridorButton(this, "", police, 100 , pos_x+dimPion/3, pos_y, dimPion, (dimPion/3));
                            but.setFenceButton(ligne,cols, 1);
                        }
                    }
                }
            }
        }
        picOfTray.set();
        windows.setContentPane(panel);
    }
    public void addFenceInGame(int pos_x, int pos_y, int dir)
    {
        game.NewFence(pos_x,pos_y,dir);
    }
    public void posObjects(int numPlayer)
    {
        double pos_x, pos_y;
        int typeOfCell;
        int VPos=-5, HPos=-5;
        for (int ligne=0; ligne<19;ligne++)
        {
            for (int cols=0; cols<19; cols++)
            {
                typeOfCell=cells[ligne][cols].GetType();
                if (typeOfCell==1 || typeOfCell==2)
                {
                    pos_x=poseInitX+(dimXTray/37*4)*((cols-1)/2)+(dimXTray/37);
                    pos_y=poseInitY+(dimYTray/37*4)*((ligne-1)/2)+(dimXTray/37);
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
                    pos_x=poseInitX+(dimXTray/37*4)*(cols/2)+(dimXTray/37);
                    pos_y=poseInitY+(dimYTray/37*4)*(ligne/2)+(dimXTray/37);
                    if ((cols%2==1 && ligne%2==0) || (cols%2==0 && ligne%2==1))
                    {
                        if (typeOfCell==3)
                        {
                            if (VPos+2!=ligne)
                            {
                                pic=new QuoridorPicture(this, Theme1[typeOfCell], pos_x-dimYFence, pos_y, dimYFence, dimXFence);
                                VPos=ligne;
                            }
                        }
                        else
                        {
                            if (HPos+2!=cols)
                            {
                                pic=new QuoridorPicture(this, Theme1[typeOfCell], pos_x, pos_y-dimYFence, dimXFence, dimYFence);
                                HPos=cols;
                            }
                        }
                    }
                }
            }
            HPos=-5;
        }
    }
    public void movePlayer(int numObj)
    {
        movePlayer.MoveIt(numObj);
        game.setWait(false);
    }
}
