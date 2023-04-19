package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.awt.Font;
import javax.imageio.ImageIO;

public class QuoridorPicture
{
    private QuoridorPanel Qpanel;
    private JLabel image;
    private ImageIcon icone;
    private int numObj;
    public QuoridorPicture(QuoridorPanel _panel, String Url, int pos_x, int pos_y, int dimX, int dimY)
    {
        Qpanel=_panel;
        icone = new ImageIcon(Url);
        icone = new ImageIcon(icone.getImage().getScaledInstance(dimX,dimY, Image.SCALE_DEFAULT));
        image = new JLabel(icone);
        image.setBounds(pos_x,pos_y, dimX, dimY);
        Qpanel.panel.add(image);
    }
}