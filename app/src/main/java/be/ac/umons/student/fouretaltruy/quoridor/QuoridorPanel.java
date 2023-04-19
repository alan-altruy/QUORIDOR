package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //

import javax.swing.*;
import java.awt.*;

/**
 * Panneau d'affichage pour l'interface graphique du Quoridor
 * qui hérite de JPanel
 */
public class QuoridorPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    /**
         * Dimensions de l'écran
         */
    private Dimension dimScreen= Toolkit.getDefaultToolkit().getScreenSize();
    /**
         * Position x du plateau
         */
    private double pos_xTray;
    /**
         * Position y du plateau
         */
    private double pos_yTray;
    /**
         * Largeur du plateau
         */
    private double widthTray;
    /**
         * Hauteur du plateau
         */
    private double heightTray;
    /**
         * Largeur de l'écran
         */
    private double widthScreen;
    /**
         * Hauteur de l'écran
         */
    private double heightScreen;
    /**
         * Largeur d'une barrière
         */
    private double widthFence;
    /**
         * Hauteur d'une barrière
         */
    private double heightFence;
    /**
         * Largeur du pion
         */
    private double widthPion;
    /**
         * Adresses des resources du programme
         */
    private String a="src/main/resources/";
    /**
         * Extension des images
         */
    private String b=".png";
    /**
         * Chemins des images
         */
    private String[] Theme1={a+"pionrouge"+b,a+"pionnoir"+b,a+"tray"+b,a+"fenceV"+b,a+"fenceH"+b,a+"valid"+b,a+"title"+b, a+"titlevalid"+b};
    /**
         * Jeu affiché sur le panneau
         */
    private QuoridorGame game;
    /**
         * Interface graphique où le panneau est affiché
         */
    private QuoridorGui gui;
    /**
         * Mémoire de bouton du panneau
         */
    private QuoridorButton but;
    /**
         * Mémoire de l'image 1 du panneau
         */
    private QuoridorPicture pic;
    /**
         * Mémoire de l'image 2 du panneau
         */
    private QuoridorPicture pic2;
    /**
         * Image du plateau
         */
    private QuoridorPicture picOfTray =null;
    /**
         * Image du fond de la fenêtre
         */
    private QuoridorPicture bg=null;
    /**
         * Initialise un panneau qui s'affichera sur la fenêtre
         * @param _gui : Interface graphique du jeu
         * @param _game : Jeu affiché sur le panneau
         */
    public QuoridorPanel(QuoridorGui _gui, QuoridorGame _game)
    {
        gui=_gui;
        game=_game;
        setLayout(null);
        widthScreen=dimScreen.getWidth();
        heightScreen=dimScreen.getHeight();
        widthTray=(heightScreen-0.2*heightScreen);
        heightTray=widthTray;
        widthPion=(widthTray/37*3);
        widthFence=(widthTray/37*7);
        heightFence=(heightTray/37);
        pos_xTray=(widthScreen/2-widthTray/2);
        pos_yTray=(heightScreen/2-heightTray/2);
    }
    /**
         * Permet de créer le panneau de l'écran d'accueil
         */
    public void mainMenu()
    {
        double height,width, pos_x,pos_y;
        // Creation du bouton solo
        width=widthScreen/2;height=heightScreen/10;
        pos_x=width-(widthScreen/4); pos_y=(heightScreen/2)-height/2;
        but = new QuoridorButton(this, 1, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"solo"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"solovalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Creation du bouton multiplayer
        pos_y=(heightScreen/2)+height;
        but = new QuoridorButton(this, 2, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"multiplayer"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"multiplayervalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Creation du bouton Quiiter le jeu
        pos_y+=height*2;
        if (game.canLoadSave())
        {
            but = new QuoridorButton(this, 10, pos_x, pos_y,height*13/3,height);
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
        but = new QuoridorButton(this, 0, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"exit"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"exitvalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Ajout du backgound Menu Principal
        bg=new QuoridorPicture(this, a+"mainmenu"+b, 0,0, widthScreen, heightScreen);
        bg.set();
        //Affiche les éléments à l'écran
        refresh();
    }
    /**
         * Permet de créer le panneau du menu de difficulté pour l'intelligence artificielle
         */
    public void chooseDifficultIA()
    {
        double height,width, pos_x,pos_y;
        // Creation du bouton Normale
        height=heightScreen/10;
        width=height*13/3;
        pos_x=(widthScreen/2)-(widthScreen/4); pos_y=(heightScreen/3)-height/2;
        but = new QuoridorButton(this, 11, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"easy"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"easyvalid"+b, pos_x, pos_y,width,height);
        // Creation du bouton Difficile
        but.setMainButton(pic, pic2);
        pos_x+=widthScreen/2-width;
        but = new QuoridorButton(this, 12, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"hard"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"hardvalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Creation du bouton Menu Principal
        width=widthScreen/2;
        pos_x=width-(widthScreen/4);
        pos_y+=height*2;
        but = new QuoridorButton(this, 8, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"home"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"homevalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Ajout du background
        bg=new QuoridorPicture(this, a+"background"+b, 0,0, widthScreen, heightScreen);
        bg.set();
        //Affiche les éléments à l'écran
        refresh();
    }
    /**
         * Permet de créer l'interface du jeu
         * @param numOfPlayer : Le numéro du joueur en train de jouer
         */
    public void twoPlayers(int numOfPlayer)
    {
        if (numOfPlayer==0)
        {
            pic = new QuoridorPicture(this, a+"steve"+b, ((widthScreen-widthTray)/4)-((widthScreen-widthTray)/8*0.75), heightScreen/8,((widthScreen-widthTray)/4*0.75), ((widthScreen-widthTray)/8*0.75));
            pic.set();
            pic = new QuoridorPicture(this, a+"creeperno"+b, widthScreen-pos_xTray/2-((widthScreen-widthTray)/8*0.75), heightScreen/8,((widthScreen-widthTray)/4*0.75), ((widthScreen-widthTray)/8*0.75));
            pic.set();
        }
        else if (numOfPlayer==1)
        {
            pic = new QuoridorPicture(this, a+"creeper"+b, widthScreen-pos_xTray/2-((widthScreen-widthTray)/8*0.75), heightScreen/8,((widthScreen-widthTray)/4*0.75), ((widthScreen-widthTray)/8*0.75));
            pic.set();
            pic = new QuoridorPicture(this, a+"steveno"+b, ((widthScreen-widthTray)/4)-((widthScreen-widthTray)/8*0.75), heightScreen/8,((widthScreen-widthTray)/4*0.75), ((widthScreen-widthTray)/8*0.75));
            pic.set();
        }
        but = new QuoridorButton(this, 6, widthScreen/2-(pos_yTray*0.75*3), pos_yTray/8,pos_yTray*0.75*6, pos_yTray*0.75);
        pic = new QuoridorPicture(this, Theme1[6], widthScreen/2-(pos_yTray*0.75*3), pos_yTray/8,pos_yTray*0.75*6, pos_yTray*0.75);
        pic2 = new QuoridorPicture(this, Theme1[7], widthScreen/2-(pos_yTray*0.75*3), pos_yTray/8,pos_yTray*0.75*6, pos_yTray*0.75);
        but.setMainButton(pic, pic2);
        picOfTray=new QuoridorPicture(this, Theme1[2], pos_xTray,pos_yTray, widthTray, heightTray);
        bg=new QuoridorPicture(this, a+"background"+b, 0,0, widthScreen, heightScreen);
        picOfTray.set();
        bg.set();
        posObjects(numOfPlayer);
        refresh();
    }
    /**
         * Permet de créer le panneau du menu pour quitter le jeu
         */
    public void optionsGame()
    {
        double height,width, pos_x,pos_y;
        // Creation du bouton solo
        width=widthScreen/2;height=heightScreen/10;
        pos_x=width-(widthScreen/4); pos_y=(heightScreen/3)-height/2;
        but = new QuoridorButton(this, 8, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"home"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"homevalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Creation du bouton multiplayer
        pos_y=(heightScreen/3)+height;
        but = new QuoridorButton(this, 7, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"save"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"savevalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Creation du bouton Quiiter le jeu
        pos_x+=width;
        width=height*13/3;
        pos_x-=width;
        pos_y+=height*2;
        but = new QuoridorButton(this, 9, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"back"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"backvalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        // Ajout du backgound Menu Principal
        bg=new QuoridorPicture(this, a+"background"+b, 0,0, widthScreen, heightScreen);
        bg.set();
        //Affiche les éléments à l'écran
        refresh();
    }
    /**
         * Permet de créer le panneau de l'écran de gagnant
         * @param numOfWinner : Numéro du joeur gagnant
         */
    public void winner(int numOfWinner)
    {
        double height,width, pos_x,pos_y;
        String name= game.getNameOfPlayer(numOfWinner);
        width=widthScreen*0.75; height=width/6;
        pos_x=widthScreen/2-width/2; pos_y=heightScreen/3-height/2;
        pic = new QuoridorPicture(this, a+name+"win"+b, pos_x, pos_y,width,height);
        pic.set();
        height=heightScreen/10; width=widthScreen/2;
        pos_x=widthScreen/2-width/2; pos_y+=3*height;
        but = new QuoridorButton(this, 8, pos_x, pos_y,width,height);
        pic = new QuoridorPicture(this, a+"home"+b, pos_x, pos_y,width,height);
        pic2 = new QuoridorPicture(this, a+"homevalid"+b, pos_x, pos_y,width,height);
        but.setMainButton(pic, pic2);
        bg=new QuoridorPicture(this, a+"background"+b, 0,0, widthScreen, heightScreen);
        bg.set();
        refresh();
    }
    /**
         * Permet de faire l'action d'un bouton
         * @param typeOfButton : Type du bouton
         */
    public void setAction(int typeOfButton) 
    {
        if (typeOfButton==0)
        {
            System.exit(0);
        }
        else if (typeOfButton==1)
        {
            gui.difficultMenu();
        }
        else if (typeOfButton==2)
        {
            game.init2Players();
        }
        else if (typeOfButton==3 || typeOfButton==4)
        {
            canMove();
        }
        else if (typeOfButton==6)
        {
            gui.addMenu();
        }
        else if (typeOfButton==7)
        {
            game.save();
        }
        else if (typeOfButton==8)
        {
            game.setNewGame(true);
        }
        else if (typeOfButton==9)
        {
            gui.gui2Players();
        }
        else if (typeOfButton==10)
        {
            game.loadSave();
            gui.gui2Players();
        }
        else if (typeOfButton==11)
        {
            game.init2AI(true);
        }
        else if (typeOfButton==12)
        {
            game.initAI(true);
        }
    }
    /**
         * Permet d'initialiser les boutons où le joueur peut se déplacer
         */
    public void canMove()
    {
        double pos_x,pos_y;
        boolean[][][] posAvailable=new QuoridorMovePlayer(game.getPlayer(game.getPlayerWhoIsPlaying()), game.getTray()).setPosAvailable();
        for (int ligne=0; ligne<19;ligne++)
        {
            for (int cols=0; cols<19; cols++)
            {
                for (int z=0; z<12; z++)
                {
                    pos_x=pos_xTray+(widthTray/37*4)*((cols-1)/2)+(widthTray/37);
                    pos_y=pos_yTray+(heightTray/37*4)*((ligne-1)/2)+(widthTray/37);
                    if (posAvailable[ligne][cols][z])
                    {
                        but= new QuoridorButton(this,   z+20 , pos_x, pos_y, widthPion, widthPion);
                        pic=new QuoridorPicture(this, Theme1[5], pos_x, pos_y, widthPion, widthPion);
                        pic.set();
                        but.setClearButton();
                    }
                }
            }      
        }
        refresh();
    }
    /**
         * Permet de placer toutes les images nécessaires pour le tour d'un joueur
         * @param numPlayer : Numéro du joueur en train de jouer
         */
    public void posObjects(int numPlayer)
    {
        double pos_x, pos_y;
        int typeOfCell;
        for (int x=0; x<2; x++)
        {
            pos_x=widthScreen-pos_xTray/2-widthFence/2;
            if (x==0)
            {
                pos_x=((widthScreen-widthTray)/4)-widthFence/2;
            }
            for (int y=0; y<10-game.getUsedFencesPlayer(x);y++)
            {
                pos_y= heightScreen/4+y*(heightFence*3);
                pic=new QuoridorPicture(this, a+"fenceH"+b, pos_x, pos_y, widthFence, heightFence);
                pic.set();
            }
        }
        for (int ligne=0; ligne<19;ligne++)
        {
            for (int cols=0; cols<19; cols++)
            {
                // Tous les emplacements de barrières dispo
                boolean humanVerif= !game.getPlayer(numPlayer).isAnAi();
                if (cols>0 && cols<18 && ligne>0 && ligne<18 && humanVerif)
                {
                    pos_x=pos_xTray+(widthTray/37*4)*((cols)/2);
                    pos_y=pos_yTray+(heightTray/37*4)*((ligne)/2);
                    if (cols%2==0 && ligne%2==1 && ligne<17)
                    {
                        but= new QuoridorButton(this, 100, pos_x, pos_y+widthPion/3, (widthPion/3), widthPion);
                        pic=new QuoridorPicture(this, a+"fenceVvalid"+b, pos_x, pos_y+heightFence, heightFence, widthFence);
                        but.setFenceButton(ligne,cols, 0, pic);
                    }
                    else if (cols%2==1 && ligne%2==0 && cols<17)
                    {
                        but= new QuoridorButton(this, 100, pos_x+widthPion/3, pos_y, widthPion, (widthPion/3));
                        pic=new QuoridorPicture(this, a+"fenceHvalid"+b, pos_x+heightFence, pos_y, widthFence, heightFence);
                        but.setFenceButton(ligne,cols, 1, pic);
                    }
                }
                typeOfCell=game.getTray().getTypeOfCell(ligne, cols);
                // Ou le joueur peut-il se deplacer
                if (typeOfCell==1 || typeOfCell==2)
                {
                    pos_x=pos_xTray+(widthTray/37*4)*((cols-1)/2)+(widthTray/37);
                    pos_y=pos_yTray+(heightTray/37*4)*((ligne-1)/2)+(widthTray/37);
                    if (numPlayer==1 && typeOfCell==2 && humanVerif)
                    {
                        but= new QuoridorButton(this, numPlayer+3, pos_x, pos_y, widthPion, widthPion);
                        but.setClearButton();
                    }
                    else if (numPlayer==0 && typeOfCell==1 && humanVerif)
                    {
                        but= new QuoridorButton(this, numPlayer+3, pos_x, pos_y, widthPion, widthPion);
                        but.setClearButton();
                    }
                    pic=new QuoridorPicture(this, Theme1[typeOfCell-1], pos_x, pos_y, widthPion, widthPion);
                    pic.set();
                }
                else if (typeOfCell==3 || typeOfCell==4)
                {
                    pos_x=pos_xTray+(widthTray/37*4)*(cols/2)+(widthTray/37);
                    pos_y=pos_yTray+(heightTray/37*4)*(ligne/2)+(widthTray/37);
                    if ((cols%2==1 && ligne%2==0) || (cols%2==0 && ligne%2==1))
                    {
                        if (typeOfCell==3)
                        {
                            pic=new QuoridorPicture(this, Theme1[typeOfCell], pos_x-heightFence, pos_y, heightFence, widthFence);
                            pic.set();
                        }
                        else
                        {
                            pic=new QuoridorPicture(this, Theme1[typeOfCell], pos_x, pos_y-heightFence, widthFence, heightFence);
                            pic.set();
                        }
                    }
                }
            }
        }
    }
    /**
         * Permet de rafraichir l'écran
         */
    public void refresh()
    {
        if (picOfTray!=null)picOfTray.remove();
        bg.remove();
        if (picOfTray!=null)picOfTray.set();
        bg.set();
        gui.refresh();
    }
    /**
         * Retourne le jeu affiché sur le panneau
         * @return Le jeu affiché
         */
    public QuoridorGame getGame()
    {
        return game;
    }
}