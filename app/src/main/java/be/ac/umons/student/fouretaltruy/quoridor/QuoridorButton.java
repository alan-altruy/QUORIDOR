package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Un bouton du jeu Quoridor qui hérite de JButton
 */
public class QuoridorButton extends JButton
{
    private static final long serialVersionUID = 1L;
    /**
         * Image du bouton
         */
    private QuoridorPicture pic,pic2;
    /**
         * Le panneau affiché à l'écran
         */
    private QuoridorPanel panel;
    /**
         * La ligne sur laquelle le bouton de la barrière se situe
         */
    private int ligne;
    /**
         * La colonne sur laquelle le bouton de la barrière se situe
         */
    private int cols;
    /**
         * La direction du bouton de la barrière
         */
    private int dir;
    /**
         * La ligne sur laquelle le bouton de la barrière se siture
         */
    private int typeObj;
    /**
         * La position x du bouton
         */
    private double pos_x;
    /**
         * La position y du bouton
         */
    private double pos_y;
    /**
         * La largeur du bouton
         */
    private double width;
    /**
         * La hauteur du bouton
         */
    private double height;
    /**
         * Initialise un bouton de Quoridor
         * <ul><li>Enregistre les informations nécéssaires (position, taille, panneau, type)</li>
         * <li>Positionne le bouton sur le plateau</li></ul>
         * @param _panel : Panneau sur lequel le bouton sera positionné
         * @param _typeObj : Type de l'objet
         * @param _pos_x : Position x du bouton
         * @param _pos_y : Position y du bouton
         * @param _width : Largeur du bouton
         * @param _height : Hauteur du bouton
         */
    public QuoridorButton(QuoridorPanel _panel, int _typeObj, double _pos_x, double _pos_y, double _width, double _height)
    {
        pos_x=_pos_x;
        pos_y=_pos_y;
        width=_width;
        height=_height;
        panel=_panel;
        typeObj=_typeObj;
        setBounds((int)pos_x, (int)pos_y, (int)width, (int)height);
    }
    /**
         * Initialise un bouton transparent
         */
    public void setClearButton()
    {
        setOpaque(false);
        setBackground(new Color(0,true));
        setBorderPainted(false);
        setContentAreaFilled(false);
        set();
        actionButton();
    }
    /**
         * Initialise un bouton adapté pour une barrière
         * @param _ligne : Ligne sur laquelle se trouvera la barrière
         * @param _cols : Colonne sur laquelle se trouvera la barrière
         * @param _dir : Direction de la barrière
         * @param _pic : Image de la barrière
         */
    public void setFenceButton(int _ligne, int _cols, int _dir, QuoridorPicture _pic)
    {
        pic=_pic;
        ligne=_ligne;
        cols=_cols;
        dir=_dir;
        actionButton();
        setClearButton();
    }
    /**
         * Initialise un bouton principal
         * @param _pic : Image liée au bouton
         * @param _pic2 : Image lors du passage de la souris sur le bouton
         */
    public void setMainButton(QuoridorPicture _pic2, QuoridorPicture _pic)
    {
        pic=_pic;
        pic2=_pic2;
        pic2.set();
        setClearButton();
    }
    /**
         * Permet de gérer les actions liées à un bouton
         * <ul><li>Ajoute un ActionListener</li>
         * <li>Ajoute un MouseListener</li></ul>
         */
    public void actionButton()
    {
        addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e)  
            {
                QuoridorGame game = panel.getGame();
                if (typeObj<20)
                {
                    //panel.getGame().playSound(2);
                    panel.setAction(typeObj);
                    remove();
                }
                else if (typeObj==100)
                {
                    if (new QuoridorFence(game).newFence(ligne, cols, dir))
                    {
                        remove();
                    }
                }
                else
                {
                    //panel.getGame().playSound(panel.getGame().getPlayerWhoIsPlaying());
                    new QuoridorMovePlayer(game.getPlayer(game.getPlayerWhoIsPlaying()), game.getTray()).move(typeObj-20);
                    game.setWait(false);
                    remove();
                }
            }});
        addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e){}
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e)
            {
                if ((typeObj>=0 && typeObj<=2) || (typeObj>=7 & typeObj<=12))
                {
                    pic.set(); 
                    pic2.remove();
                    panel.refreshBg();
                }
                else if (typeObj==100 || typeObj==6)
                {
                    boolean verif1=new QuoridorFence(panel.getGame()).canSetFence(ligne,cols, dir);
                    boolean verif2=panel.getGame().getUsedFencesPlayer(panel.getGame().getPlayerWhoIsPlaying())<10;
                    if (typeObj==6 ||(typeObj==100 && verif1 && verif2))
                    {
                        if (typeObj==6)
                        {
                            pic2.remove();
                        }
                        pic.set();
                        panel.refresh();
                    }
                }
            }
            public void mouseExited(MouseEvent e)
            {
                if ((typeObj>=0 && typeObj<=2) || (typeObj>=7 & typeObj<=12))
                {
                    pic.remove();
                    pic2.set();
                    panel.refreshBg();
                }
                else if (typeObj==100 || typeObj==6)
                {
                    pic.remove();
                    if (typeObj==6)
                    {
                        pic2.set();
                    }
                    panel.refresh();
                }
            }
        });
    }
    /**
         * Ajoute le bouton au panneau
         */
    public void set()
    {
        if (!(panel.getGame().getNbPlayers()==1 && panel.getGame().getPlayerWhoIsPlaying()==1) || typeObj==8)
        {
            panel.add(this);
        }
    }
    /**
         * Retire le bouton du panneau
         */
    public void remove()
    {
        panel.remove(this);
    }
}