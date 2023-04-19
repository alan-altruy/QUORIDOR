package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
public class QuoridorGui extends JFrame
{
    private static final long serialVersionUID = 1L;
    private QuoridorGame game;
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
    public void startScreen()
    {
        panel = new QuoridorPanel(this, game);
        panel.mainMenu();
        showGui();
    }
    public void gui2Players()
    {
        panel = new QuoridorPanel(this, game);  
        panel.twoPlayers(game.getPlayerWhoIsPlaying());
        showGui();
    }
    public void addMenu()
    {
        panel = new QuoridorPanel(this, game);
        panel.optionsGame();
        showGui();
    }
    public void guiWinner(int num)
    {
        panel = new QuoridorPanel(this, game);
        panel.winner(num);
        showGui();
    }
    public void showGui()
    {
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }
    public void showGui2()
    {
        this.setAlwaysOnTop(true);
    }
    public void refresh()
    {
        this.setContentPane(panel);
    }
}