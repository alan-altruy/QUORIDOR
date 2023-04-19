package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.*;

public class QuoridorPanel implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Dimension dimScreen= Toolkit.getDefaultToolkit().getScreenSize();
    private double poseInitX, poseInitY, dimXTray, dimYTray, dimXScreen, dimYScreen, dimPion, dimXFence,dimYFence;
    public int PlayerWhoIsPlaying;
    private String a="src/main/resources/",b=".png";
    private String[] Theme1={a+"pionrouge"+b,a+"pionnoir"+b,a+"tray"+b,a+"fenceV"+b,a+"fenceH"+b,a+"valid"+b,a+"title"+b, a+"titlevalid"+b};
    private Font police;
    public JFrame windows;
    public JPanel panel, panelWait;
    public QuoridorGame game;
    private QuoridorButton but;
    private QuoridorPicture pic,pic2, picOfTray, bg;
    private QuoridorGui gui;
    private QuoridorCell[][] cells;
    private boolean[][][]posAvailable= new boolean[19][19][12];
    private QuoridorMovePlayer movePlayer;
    public QuoridorPanel(QuoridorGui _gui)
    {
        gui=_gui;
        windows=gui.windows;
        game=gui.game;
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
        double height,width, pos_x,pos_y;
        // Creation du bouton solo
        width=dimXScreen/2;height=dimYScreen/10;
        pos_x=width-(dimXScreen/4); pos_y=(dimYScreen/2)-height/2;
        but = new QuoridorButton(this,"",police, 1, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"solo"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"solovalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Creation du bouton multiplayer
        pos_y=(dimYScreen/2)+height;
        but = new QuoridorButton(this,"",police, 2, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"multiplayer"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"multiplayervalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Creation du bouton Quiiter le jeu
        pos_y+=height*2;
        if (game.canLoadSave())
        {
            but = new QuoridorButton(this,"",police, 10, pos_x, pos_y,height*13/3,height);
            pic = new QuoridorPicture(this, a+"load"+b, pos_x, pos_y,height*13/3,height);
            pic2 = new QuoridorPicture(this, a+"loadvalid"+b, pos_x, pos_y,height*13/3,height);
            but.setMainButton(pic, pic2);
        }
        else
        {
            pic = new QuoridorPicture(this, a+"loadno"+b, pos_x, pos_y,height*13/3,height);
            pic.set();
        }
        pos_x+=width;
        width=height*13/3;
        pos_x-=width;
        but = new QuoridorButton(this,"",police, 0, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"exit"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"exitvalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Ajout du backgound Menu Principal
        bg=new QuoridorPicture(this, a+"mainmenu"+b, 0,0, dimXScreen, dimYScreen);
        bg.set();
        //Affiche les éléments à l'écran
        refreshBg();
    }
    public void TwoPlayers(int num)
    {
        PlayerWhoIsPlaying=num;
        if (num==0)
        {
            pic = new QuoridorPicture(this, a+"steve"+b, ((dimXScreen-dimXTray)/4)-((dimXScreen-dimXTray)/8*0.75), dimYScreen/8,((dimXScreen-dimXTray)/4*0.75), ((dimXScreen-dimXTray)/8*0.75));
            pic.set();
            pic = new QuoridorPicture(this, a+"creeperno"+b, dimXScreen-poseInitX/2-((dimXScreen-dimXTray)/8*0.75), dimYScreen/8,((dimXScreen-dimXTray)/4*0.75), ((dimXScreen-dimXTray)/8*0.75));
            pic.set();
        }
        else if (num==1)
        {
            pic = new QuoridorPicture(this, a+"creeper"+b, dimXScreen-poseInitX/2-((dimXScreen-dimXTray)/8*0.75), dimYScreen/8,((dimXScreen-dimXTray)/4*0.75), ((dimXScreen-dimXTray)/8*0.75));
            pic.set();
            pic = new QuoridorPicture(this, a+"steveno"+b, ((dimXScreen-dimXTray)/4)-((dimXScreen-dimXTray)/8*0.75), dimYScreen/8,((dimXScreen-dimXTray)/4*0.75), ((dimXScreen-dimXTray)/8*0.75));
            pic.set();
        }
        but = new QuoridorButton(this,"",police, 6, dimXScreen/2-(poseInitY*0.75*3), poseInitY/8,poseInitY*0.75*6, poseInitY*0.75);
        pic = new QuoridorPicture(this, Theme1[6], dimXScreen/2-(poseInitY*0.75*3), poseInitY/8,poseInitY*0.75*6, poseInitY*0.75);
        pic2 = new QuoridorPicture(this, Theme1[7], dimXScreen/2-(poseInitY*0.75*3), poseInitY/8,poseInitY*0.75*6, poseInitY*0.75);
        but.setMainButton(pic, pic2);
        posObjects(num);
        picOfTray=new QuoridorPicture(this, Theme1[2], poseInitX,poseInitY, dimXTray, dimYTray);
        bg=new QuoridorPicture(this, a+"background"+b, 0,0, dimXScreen, dimYScreen);
        picOfTray.set();
        bg.set();
        windows.setContentPane(panel);
    }
    public void optionsGame()
    {
        double height,width, pos_x,pos_y;
        // Creation du bouton solo
        width=dimXScreen/2;height=dimYScreen/10;
        pos_x=width-(dimXScreen/4); pos_y=(dimYScreen/3)-height/2;
        but = new QuoridorButton(this,"",police, 8, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"home"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"homevalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Creation du bouton multiplayer
        pos_y=(dimYScreen/3)+height;
        but = new QuoridorButton(this,"",police, 7, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"save"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"savevalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Creation du bouton Quiiter le jeu
        pos_x+=width;
        width=height*13/3;
        pos_x-=width;
        pos_y+=height*2;
        but = new QuoridorButton(this,"",police, 9, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"back"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"backvalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Ajout du backgound Menu Principal
        bg=new QuoridorPicture(this, a+"background"+b, 0,0, dimXScreen, dimYScreen);
        bg.set();
        //Affiche les éléments à l'écran
        refreshBg();
    }
    public void Winner(int num)
    {
        double height,width, pos_x,pos_y;
        String name= game.GetNameOfPlayer(num);
        width=dimXScreen*0.75; height=width/6;
        pos_x=dimXScreen/2-width/2; pos_y=dimYScreen/3-height/2;
        pic = new QuoridorPicture(this, a+name+"win"+b, pos_x, pos_y,width,height);
        pic.set();
        height=dimYScreen/10; width=dimXScreen/2;
        pos_x=dimXScreen/2-width/2; pos_y+=3*height;
        but = new QuoridorButton(this,"",police, 8, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"home"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"homevalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        bg=new QuoridorPicture(this, a+"background"+b, 0,0, dimXScreen, dimYScreen);
        bg.set();
        refreshBg();
    }
    public void setAction(int choice) 
    {
        if (choice==0)
        {
            game.close();
        }
        else if (choice==1)
        {
            
        }
        else if (choice==2)
        {
            game.setNbPlayers(2);
        }
        else if (choice==3 || choice==4)
        {
            movePlayer = new QuoridorMovePlayer(game.getPlayers(PlayerWhoIsPlaying), game.tray);
            canMove(choice);
        }
        else if (choice==6)
        {
            gui.addMenu();
        }
        else if (choice==7)
        {
            try
            {
                FileOutputStream fileOut = new FileOutputStream(a+"/save.ser");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(game);
                objectOut.close();
                System.out.println("The Object  was succesfully written to a file");
                game.newGame=true;
                game.wait=false;
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else if (choice==8)
        {
            game.newGame=true;
            game.wait=false;
        }
        else if (choice==9)
        {
            gui.Gui2Players(game.playerWhoIsPlaying);
        }
        else if (choice==10)
        {
            game.loadSave();
            gui.Gui2Players(game.playerWhoIsPlaying);
        }
    }
    public void canMove(int choice)
    {
        double pos_x,pos_y;
        posAvailable=movePlayer.setPosAvailable();  
        for (int ligne=0; ligne<19;ligne++)
        {
            for (int cols=0; cols<19; cols++)
            {
                for (int z=0; z<12; z++)
                {
                    pos_x=poseInitX+(dimXTray/37*4)*((cols-1)/2)+(dimXTray/37);
                    pos_y=poseInitY+(dimYTray/37*4)*((ligne-1)/2)+(dimXTray/37);
                    if (posAvailable[ligne][cols][z] && (choice==3 || choice==4))
                    {
                        but= new QuoridorButton(this, "", police, z+20 , pos_x, pos_y, dimPion, dimPion);
                        pic=new QuoridorPicture(this, Theme1[5], pos_x, pos_y, dimPion, dimPion);
                        pic.set();
                        but.setPlayerButton();
                    }
                }
            }
                    
        }
        refresh();
    }
    public void addFenceInGame(int pos_x, int pos_y, int dir)
    {
        game.NewFence(pos_x,pos_y,dir);
    }
    public void posObjects(int numPlayer)
    {
        double pos_x, pos_y;
        int typeOfCell;
        for (int x=0; x<2; x++)
        {
            pos_x=dimXScreen-poseInitX/2-dimXFence/2;
            if (x==0)
            {
                pos_x=((dimXScreen-dimXTray)/4)-dimXFence/2;
            }
            for (int y=0; y<10-game.NbFencesPlayer(x);y++)
            {
                pos_y= dimYScreen/4+y*(dimYFence*3);
                pic=new QuoridorPicture(this, a+"fenceH"+b, pos_x, pos_y, dimXFence, dimYFence);
                pic.set();
            }
        }
        for (int ligne=0; ligne<19;ligne++)
        {
            for (int cols=0; cols<19; cols++)
            {
                if (cols>0 && cols<18 && ligne>0 && ligne<18)
                {
                    pos_x=poseInitX+(dimXTray/37*4)*((cols)/2);
                    pos_y=poseInitY+(dimYTray/37*4)*((ligne)/2);
                    if (cols%2==0 && ligne%2==1 && ligne<17)
                    {
                        but= new QuoridorButton(this, "", police, 100 , pos_x, pos_y+dimPion/3, (dimPion/3), dimPion);
                        pic=new QuoridorPicture(this, a+"fenceVvalid"+b, pos_x, pos_y+dimYFence, dimYFence, dimXFence);
                        but.setFenceButton(ligne,cols, 0, pic);
                    }
                    else if (cols%2==1 && ligne%2==0 && cols<17)
                    {
                        but= new QuoridorButton(this, "", police, 100 , pos_x+dimPion/3, pos_y, dimPion, (dimPion/3));
                        pic=new QuoridorPicture(this, a+"fenceHvalid"+b, pos_x+dimYFence, pos_y, dimXFence, dimYFence);
                        but.setFenceButton(ligne,cols, 1, pic);
                    }
                }
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
                    pic.set();
                }
                else if (typeOfCell==3 || typeOfCell==4)
                {
                    pos_x=poseInitX+(dimXTray/37*4)*(cols/2)+(dimXTray/37);
                    pos_y=poseInitY+(dimYTray/37*4)*(ligne/2)+(dimXTray/37);
                    if ((cols%2==1 && ligne%2==0) || (cols%2==0 && ligne%2==1))
                    {
                        if (typeOfCell==3)
                        {
                            pic=new QuoridorPicture(this, Theme1[typeOfCell], pos_x-dimYFence, pos_y, dimYFence, dimXFence);
                            pic.set();
                        }
                        else
                        {
                            pic=new QuoridorPicture(this, Theme1[typeOfCell], pos_x, pos_y-dimYFence, dimXFence, dimYFence);
                            pic.set();
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
    public void refresh()
    {
        picOfTray.remove();
        bg.remove();
        picOfTray.set();
        bg.set();
        windows.setContentPane(panel);
    }
    public void refreshBg()
    {
        bg.remove();
        bg.set();
        windows.setContentPane(panel);
    }
}