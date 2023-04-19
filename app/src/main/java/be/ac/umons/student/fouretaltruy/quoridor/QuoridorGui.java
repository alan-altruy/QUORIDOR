package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
public class QuoridorGui
{
    private QuoridorGame game;
    public JFrame windows;
    private QuoridorPanel MainPanel, Panel2Players, PanelWinner;
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
        MainPanel= new QuoridorPanel(this, game);
        MainPanel.MainMenu();
        showGui();
    }
    public void Gui2Players(int num)
    {
        Panel2Players= new QuoridorPanel(this, game);  
        Panel2Players.TwoPlayers(num);
        showGui();
    }
    public void GuiWinner(int num)
    {
        PanelWinner= new QuoridorPanel(this, game);
        PanelWinner.Winner(num);
        showGui();
        game.setWait(false);
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