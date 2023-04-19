package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
public class QuoridorGui extends JFrame implements Serializable
{
    private static final long serialVersionUID = 1L;
    public QuoridorGame game;
    private QuoridorPanel panel;
    public QuoridorGui(QuoridorGame _game)
    {
        game=_game;
        this.setTitle("Quoridor - The Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
    }
    public void StartScreen()
    {
        panel = new QuoridorPanel(this);
        panel.MainMenu();
        showGui();
    }
    public void Gui2Players(int num)
    {
        panel = new QuoridorPanel(this);  
        panel.TwoPlayers(num);
        showGui();
    }
    public void addMenu()
    {
        panel = new QuoridorPanel(this);
        panel.optionsGame();
        showGui();
    }
    public void GuiWinner(int num)
    {
        panel = new QuoridorPanel(this);
        panel.Winner(num);
        showGui();
    }
    public void showGui()
    {
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }
    public void refresh()
    {
        this.setContentPane((JPanel)panel);
    }
    public void setNbPlayers (int newNb)
    {
        game.setNbPlayers(newNb);
    }
}