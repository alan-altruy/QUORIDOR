package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
public class QuoridorGui implements Serializable
{
    private static final long serialVersionUID = 1L;
    public QuoridorGame game;
    public JFrame windows;
    private QuoridorPanel MainPanel, Panel2Players, PanelWinner, PanelMenu;
    public QuoridorGui(QuoridorGame _game)
    {
        game=_game;
        windows= new JFrame();
        windows.setTitle("Quoridor - The Game");
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setLocationRelativeTo(null);
        windows.setExtendedState(windows.MAXIMIZED_BOTH);
        windows.setAlwaysOnTop(true);
        windows.setUndecorated(true);
    }
    public void StartScreen()
    {
        MainPanel= new QuoridorPanel(this);
        MainPanel.MainMenu();
        showGui();
    }
    public void Gui2Players(int num)
    {
        Panel2Players= new QuoridorPanel(this);  
        Panel2Players.TwoPlayers(num);
        showGui();
    }
    public void addMenu()
    {
        PanelMenu= new QuoridorPanel(this);
        PanelMenu.optionsGame();
        showGui();
    }
    public void GuiWinner(int num)
    {
        PanelWinner= new QuoridorPanel(this);
        PanelWinner.Winner(num);
        showGui();
    }
    public void showGui()
    {
        windows.setVisible(true);
    }
    public void setNbPlayers (int newNb)
    {
        game.setNbPlayers(newNb);
    }
}