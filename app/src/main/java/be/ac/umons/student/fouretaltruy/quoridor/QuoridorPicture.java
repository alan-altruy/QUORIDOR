package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.awt.Font;
import java.util.Objects;
import javax.imageio.ImageIO;

public class QuoridorPicture
{
    private QuoridorPanel Qpanel;
    private JLabel image;
    private ImageIcon icone;
    public QuoridorPicture(QuoridorPanel _panel, String Url, double pos_x, double pos_y, double dimX, double dimY)
    {
        Qpanel=_panel;
        icone = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(Url)));
        icone = new ImageIcon(icone.getImage().getScaledInstance((int)dimX,(int)dimY, Image.SCALE_DEFAULT));
        image = new JLabel(icone);
        image.setBounds((int)pos_x,(int)pos_y, (int)dimX, (int)dimY);
        Qpanel.panel.add(image);
    }
    public void remove()
    {
        Qpanel.panel.remove(image);
    }
    public void set()
    {
        Qpanel.panel.add(image);
    }
}