package be.ac.umons.student.fouretaltruy.quoridor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.awt.Font;
import javax.imageio.ImageIO;
public class QuoridorGui implements ActionListener
{ 
    private Font police;
    private String PionRouge="../resources/pionrouge.png";
    private String PionNoir="../resources/pionnoir.png";
    private String TrayUrl="../resources/tray.png";
    private boolean action=false;
    private JButton btn1Joueur, btn2Joueur, btnValider;
    private Dimension dimScreen= Toolkit.getDefaultToolkit().getScreenSize();
    private int chiffre=0, dimXTray, dimYTray, nbPlayers;
    private int dimXScreen=(int)dimScreen.getWidth();
    private int dimYScreen=(int)dimScreen.getHeight();
    private int dimPion;
    private ImageIcon iconeTray,iconeNoir,iconeRouge;
    private JPanel panneau;
    private QuoridorGame game;
    private JLabel imageTray,imageRouge,imageNoir, namePlayer;
    private JFrame windows;
    private QuoridorTray tray;
    private QuoridorCell[][]cells;
    private JTextField nameOfPlayer;
    private int poseInitX,poseInitY;
    public QuoridorGui(QuoridorGame _game)
    {
        dimXTray=(int)(dimScreen.getHeight())-200;
        dimYTray=(int)(dimScreen.getHeight())-200;
        dimPion=(int)(dimXTray/9);
        poseInitX=((int)(dimXScreen/2-dimXTray/2));
        poseInitY=((int)(dimYScreen/2-dimYTray/2));
        setImage();
        game=_game;
        tray= game.tray;
        cells=tray.cells;
        windows= new JFrame();
        windows.setTitle("Quoridor - The Game");
        windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setLocationRelativeTo(null);
        windows.setBounds(0,0,(int)dimScreen.getWidth(), (int)dimScreen.getHeight());
        windows.setExtendedState(windows.MAXIMIZED_BOTH);
        windows.setAlwaysOnTop(true);
        windows.setUndecorated(true);
    }
    public void setImage()
    {
        iconeTray = new ImageIcon(TrayUrl);
        iconeTray = new ImageIcon(iconeTray.getImage().getScaledInstance(dimXTray,dimYTray, Image.SCALE_DEFAULT));
        iconeRouge = new ImageIcon(PionRouge);
        iconeRouge = new ImageIcon(iconeRouge.getImage().getScaledInstance(dimPion,dimPion, Image.SCALE_DEFAULT));
        iconeNoir = new ImageIcon(PionNoir);
        iconeNoir = new ImageIcon(iconeNoir.getImage().getScaledInstance(dimPion,dimPion, Image.SCALE_DEFAULT));
        imageTray = new JLabel(iconeTray);
        imageTray.setBounds(poseInitX,poseInitY, dimXTray, dimYTray);
        imageRouge = new JLabel(iconeRouge);
        imageNoir = new JLabel(iconeNoir);

    }
    public void MainMenu(QuoridorGame _game)
    {
        panneau=new JPanel();
        btn1Joueur = new JButton("1 JOUEUR");
        btn2Joueur = new JButton("2 JOUEURS");
        btn1Joueur.setBounds((int)(dimScreen.getWidth()/2)-250, (int)(dimScreen.getHeight()*0.33)-100, 500, 200);
        btn2Joueur.setBounds((int)(dimScreen.getWidth()/2)-250, (int)(dimScreen.getHeight()*0.66)-100, 500, 200);
        police = new Font("FreeSans", Font.BOLD, 38);
        btn1Joueur.setFont(police);
        btn2Joueur.setFont(police);
        btn1Joueur.addActionListener(this);
        btn2Joueur.addActionListener(this);
        panneau.add(btn1Joueur);
        panneau.add(btn2Joueur);
        showGui();
    }
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source==btn1Joueur)
        {
        }
        else if (source==btn2Joueur)
        {
            nbPlayers=2;
            init2players(0);
        }
        else if (source==btnValider)
        {
            init2players(2);
        }
        else if (source==nameOfPlayer)
        {
            String name =getTextField().getText();
            getLabel().setText(name);
        }
    }
    public void init2players(int num)
    {
        panneau= new JPanel();
        nameOfPlayer=new JTextField();
        nameOfPlayer.setColumns(20);
        nameOfPlayer.setBounds(((int)dimXScreen/2)-110, ((int)dimYScreen/2)-35, 220, 70);  
        panneau.add(nameOfPlayer);
        btnValider=new JButton("VALIDER");
        btnValider.setBounds(((int)dimXScreen/2)-20, ((int)dimYScreen/2)+50, 40, 20);
        panneau.add(btnValider);
        btnValider.addActionListener(this);
        namePlayer=new JLabel("Nom du joueur 1");
        showGui();
        //Gui2Players();
    }
    public JTextField getTextField()
    {
        return nameOfPlayer;
    }
    public JLabel getLabel()
    {
        return namePlayer;
    }
    public void Gui2Players()
    {
        panneau= new JPanel();  
        panneau.add(imageTray);
        posObjects();
        showGui();
    }
    public void posObjects()
    {
        int pos_x, pos_y;
        int typeOfCell;
        for (int ligne=0; ligne<19;ligne++)
        {
            pos_y=poseInitY+(dimPion*((ligne-1)/2));
            for (int cols=0; cols<19; cols++)
            {
                pos_x=poseInitX+(dimPion*((cols-1)/2));
                typeOfCell=cells[ligne][cols].GetType();
                switch (typeOfCell)
                {
                    case 1:
                        imageNoir.setBounds(pos_x+5, pos_y+5, dimPion, dimPion);
                        panneau.add(imageNoir);
                        break;
                    case 2:
                        imageRouge.setBounds(pos_x+5, pos_y+5, dimPion, dimPion);
                        panneau.add(imageRouge);
                        break;
                     
                }
            }
        }
    }
    public int returnInt()
    {
        return this.chiffre;
    }
    public boolean getAction()
    {
        return action;
    }
    public void showGui()
    {
        panneau.setLayout(null);
        windows.setContentPane(this.panneau);
        windows.setVisible(true);
    }
    public int getNbPlayers()
    {
        return nbPlayers;  
    }
}