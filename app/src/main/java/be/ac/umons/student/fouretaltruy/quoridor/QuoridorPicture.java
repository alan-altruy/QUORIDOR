package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Font;
import javax.imageio.ImageIO;

public class QuoridorPicture implements Serializable
{
    private static final long serialVersionUID = 1L;
    private QuoridorPanel Qpanel;
    private JLabel image;
    private ImageIcon icone;
    public QuoridorPicture(QuoridorPanel _panel, String Url, double pos_x, double pos_y, double dimX, double dimY)
    {
        Qpanel=_panel;
        icone = new ImageIcon(Url);
        icone = new ImageIcon(icone.getImage().getScaledInstance((int)dimX,(int)dimY, Image.SCALE_DEFAULT));
        image = new JLabel(icone);
        image.setBounds((int)pos_x,(int)pos_y, (int)dimX, (int)dimY);
    }
    public void remove()
    {
        Qpanel.panel.remove(image);
    }
    public void set()
    {
        Qpanel.panel.add(image);
    }
    public JLabel getImage()
    {
        return image;
    }
    public ImageIcon getIcon()
    {
        return icone;
    }
}