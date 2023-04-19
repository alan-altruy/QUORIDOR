package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;

public class QuoridorButton implements ActionListener
{
    private JButton button;
    private int numObj;
    private QuoridorPanel Qpanel;
    public QuoridorButton(QuoridorPanel _panel, String name, Font police, int num, int pos_x, int pos_y, int width, int height)
    {
        Qpanel=_panel;
        numObj=num;
        button=new JButton(name);
        button.setBounds(pos_x, pos_y, width, height);
        button.setFont(police);
        button.addActionListener(this);
    }
    public void setPlayerButton()
    {
        button.setOpaque(false);
        button.setBackground(new Color(0,true));
        setButton();
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
        else
        {
            Qpanel.movePlayer(numObj-20);
        }
    }
}