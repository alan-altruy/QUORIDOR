package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;

public class QuoridorButton implements ActionListener
{
    private JButton button;
    private QuoridorPanel Qpanel;
    public int pos_x, pos_y, dir, numObj;
    public QuoridorButton(QuoridorPanel _panel, String name, Font police, int num, double pos_x, double pos_y, double width, double height)
    {
        Qpanel=_panel;
        numObj=num;
        button=new JButton(name);
        button.setBounds((int)pos_x, (int)pos_y, (int)width, (int)height);
        button.setFont(police);
        button.addActionListener(this);
    }
    public void setPlayerButton()
    {
        button.setOpaque(false);
        button.setBackground(new Color(0,true));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        setButton();
    }
    public void setFenceButton(int _pos_x, int _pos_y, int _dir)
    {
        pos_x=_pos_x;
        pos_y=_pos_y;
        dir=_dir;
        setPlayerButton();
    }
    public void setButton()
    {
        Qpanel.panel.add(button);
    }
    public void actionPerformed(ActionEvent e)
    {
        if (numObj<20)
        {
            Qpanel.setAction(numObj);
        }
        else if (numObj==100)
        {
            Qpanel.addFenceInGame(pos_x,pos_y,dir);
        }
        else
        {
            Qpanel.movePlayer(numObj-20);
        }
    }
}