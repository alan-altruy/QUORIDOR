// ALTRUY ALAN - JASON FOURET //

package be.ac.umons.student.fouretaltruy.quoridor.gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Classe permettant de créer une image pour le jeu Quoridor qui est implémentée de Serializable
 */
public class QuoridorPicture implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
         * Panneau sur lequel l'image sera affichée
         */
    private QuoridorPanel panel;
    /**
         * Image utilisée
         */
    private JLabel image;
    /**
         * Initialise une image pour un panneau
         * @param _panel : Panneau où l'image sera affichée
         * @param url : Chemin d'accès à l'image
         * @param pos_x : Position x de l'image
         * @param pos_y : Position y de l'image
         * @param width : Largeur de l'image
         * @param height : Hauteur de l'image
         */
    public QuoridorPicture(QuoridorPanel _panel, String url, double pos_x, double pos_y, double width, double height)
    {
        panel=_panel;
        ImageIcon icone = new ImageIcon(url);
        icone = new ImageIcon(icone.getImage().getScaledInstance((int)width,(int)height, Image.SCALE_DEFAULT));
        image = new JLabel(icone);
        image.setBounds((int)pos_x,(int)pos_y, (int)width, (int)height);
    }
    /**
         * Permet d'ajouter une image à un panneau
         */
    public void set()
    {
        panel.add(image);
    }
    /**
         * Permet de retirer une image d'un panneau
         */
    public void remove()
    {
        panel.remove(image);
    }
}