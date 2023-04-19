package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.Font;
import java.io.*;

public class QuoridorButton implements Serializable
{
    private static final long serialVersionUID = 1L;
    private JButton button;
    private QuoridorPicture pic,pic2;
    private QuoridorPanel Qpanel;
    public int cols, ligne, dir, numObj;
    private double pos_x,pos_y,width,height;
    public QuoridorButton(QuoridorPanel _panel, String name, Font police, int num, double _pos_x, double _pos_y, double _width, double _height)
    {
        pos_x=_pos_x;
        pos_y=_pos_y;
        width=_width;
        height=_height;
        Qpanel=_panel;
        numObj=num;
        button=new JButton(name);
        button.setBounds((int)pos_x, (int)pos_y, (int)width, (int)height);
        button.setFont(police);
        actionClick();
    }
    public void setPlayerButton()
    {
        button.setOpaque(false);
        button.setBackground(new Color(0,true));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        setButton();
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
        actionButton();
        setPlayerButton();
    }
    public void actionClick ()
    {
        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e)  
            {
                if (numObj<20)
                {
                    Qpanel.setAction(numObj);
                }
                else if (numObj==100)
                {
                    Qpanel.addFenceInGame(cols,ligne,dir);
                }
                else
                {
                    Qpanel.movePlayer(numObj-20);
                }
                removeButton();
            }});
    }
    public void actionButton()
    {
        button.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e){}
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e)
            {
                if ((numObj>=0 && numObj<=2) || (numObj>=7 & numObj<=10))
                {
                    pic.set(); 
                    pic2.remove();
                    Qpanel.refreshBg();
                }
                else if (numObj==100 || numObj==6)
                {
                    if (numObj==6 ||(numObj==100 && Qpanel.game.VerifFence(cols,ligne, dir) && Qpanel.game.NbFencesPlayer(Qpanel.PlayerWhoIsPlaying)<10))
                    {
                        if (numObj==6)
                        {
                            pic2.remove();
                        }
                        pic.set();
                        Qpanel.refresh();
                    }
                }
            }
            public void mouseExited(MouseEvent e)
            {
                pic.remove();
                if ((numObj>=0 && numObj<=2) || (numObj>=7 & numObj<=10))
                {
                    pic2.set();
                    Qpanel.refreshBg();
                }
                else if (numObj==100 || numObj==6)
                {
                    if (numObj==6)
                        {
                            pic2.set();
                        }
                    Qpanel.refresh();
                }
                
            }});
    }
    public void setButton()
    {
        Qpanel.panel.add(button);
    }
    public void removeButton()
    {
        Qpanel.panel.remove(button);
    }
}