package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //

import javax.swing.*;

/**
 * L'interface graphique du Quoridor qui hérite de JFrame
 */
public class QuoridorGui extends JFrame
{
    private static final long serialVersionUID = 1L;
    /**
         * Jeu dans lequel l'interface est utilisée
         */
    private QuoridorGame game;
    /**
         * Panneau utilisé par l'interface graphique
         */
    private QuoridorPanel panel;
    /**
         * Initialise l'interface graphique 
         * @param _game : Le jeu
         */
    public QuoridorGui(QuoridorGame _game)
    {
        game = _game;
        setTitle("Quoridor - The Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(7);
        setAlwaysOnTop(true);
        setUndecorated(true);
    }
    /**
         * Permet de créer l'interface de l'écran d'accueil
         */
    public void startScreen()
    {
        panel = new QuoridorPanel(this, game);
        panel.mainMenu();
        showGui();
    }
    /**
         * Permet de créer l'interface du menu de difficulté pour l'intelligence artificielle
         */
    public void difficultMenu()
    {
        panel = new QuoridorPanel(this, game);
        panel.chooseDifficultIA();
        showGui();
    }
    /**
         * Permet de créer l'interface du jeu
         */
    public void gui2Players()
    {
        panel = new QuoridorPanel(this, game);
        panel.twoPlayers(game.getPlayerWhoIsPlaying());
        showGui();
    }
    /**
         * Permet de créer l'interface du menu pour quitter le jeu
         */
    public void addMenu()
    {
        panel = new QuoridorPanel(this, game);
        panel.optionsGame();
        showGui();
    }
    /**
         * Permet de créer l'interface de l'écran de gagnant
         */
    public void guiWinner(final int num)
    {
        panel = new QuoridorPanel(this, game);
        panel.winner(num);
        showGui();
    }
    /**
         * Permet d'afficher l'interface graphique, la fenêtre
         */
    public void showGui()
    {
        setVisible(true);
        setAlwaysOnTop(true);
    }
    /**
         * Permet de rafraichir le panneau à l'écran
         */
    public void refresh()
    {
        setContentPane(panel);
        showGui();
    }
    /**
         * Permet de garder la fenêtre à jour
         */
    public void waitGui()
    {
        setFocusableWindowState(true);
    }
}