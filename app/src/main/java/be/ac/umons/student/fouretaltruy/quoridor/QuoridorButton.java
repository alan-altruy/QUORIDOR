package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuoridorButton extends JButton
{
    private static final long serialVersionUID = 1L;
    private QuoridorPicture pic,pic2;
    private QuoridorPanel panel;
    private int cols, ligne, dir, numObj;
    private double pos_x,pos_y,width,height;
    public QuoridorButton(QuoridorPanel _panel, int num, double _pos_x, double _pos_y, double _width, double _height)
    {
        pos_x=_pos_x;
        pos_y=_pos_y;
        width=_width;
        height=_height;
        panel=_panel;
        numObj=num;
        setBounds((int)pos_x, (int)pos_y, (int)width, (int)height);
    }
    public void setPlayerButton()
    {
        setOpaque(false);
        setBackground(new Color(0,true));
        setBorderPainted(false);
        setContentAreaFilled(false);
        set();
        actionButton();
    }
    public void setFenceButton(int _pos_x, int _pos_y, int _dir, QuoridorPicture _pic)
    {
        pic=_pic;
        cols=_pos_x;
        ligne=_pos_y;
        dir=_dir;
        actionButton();
        setPlayerButton();
    }
    public void setMainButton(QuoridorPicture _pic2, QuoridorPicture _pic)
    {
        pic=_pic;
        pic2=_pic2;
        pic2.set();
        setPlayerButton();
    }
    public void actionButton()
    {
        addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent e)
            {
                if (numObj<20)
                {
                    //panel.getGame().playSound(2);
                    panel.setAction(numObj);
                    remove();
                }
                else if (numObj==100)
                {
                    if (panel.getGame().getFence().newFence(cols,ligne,dir))
                    {
                        //panel.getGame().playSound(3);
                        panel.getGame().setWait(false);
                        remove();
                    } 
                }
                else
                {
                    //panel.getGame().playSound(panel.getGame().getPlayerWhoIsPlaying());
                    panel.setMovePlayer(numObj-20);
                    remove();
                }
                
            }
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e)
            {
                if ((numObj>=0 && numObj<=2) || (numObj>=7 & numObj<=12))
                {
                    pic.set(); 
                    pic2.remove();
                    panel.refreshBg();
                }
                else if (numObj==100 || numObj==6)
                {
                    boolean verif1=panel.getGame().getFence().canSetFence(cols,ligne, dir);
                    boolean verif2=panel.getGame().getUsedFencesPlayer(panel.getGame().getPlayerWhoIsPlaying())<10;
                    if (numObj==6 ||(numObj==100 && verif1 && verif2))
                    {
                        if (numObj==6)
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
                if ((numObj>=0 && numObj<=2) || (numObj>=7 & numObj<=12))
                {
                    pic.remove();
                    pic2.set();
                    panel.refreshBg();
                }
                else if (numObj==100 || numObj==6)
                {
                    pic.remove();
                    if (numObj==6)
                    {
                        pic2.set();
                    }
                    panel.refresh();
                }
            }
        });
    }
    public void set()
    {
        if (!(panel.getGame().getNbPlayers()==1 && panel.getGame().getPlayerWhoIsPlaying()==1) || numObj==8)
        {
            panel.add(this);
        }
    }
    public void remove()
    {
        panel.remove(this);
    }
}