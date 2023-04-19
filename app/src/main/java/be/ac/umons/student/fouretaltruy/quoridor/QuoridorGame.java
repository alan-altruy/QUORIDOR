package be.ac.umons.student.fouretaltruy.quoridor;

import java.util.Scanner;
public class QuoridorGame
{
    private int nbPlayers;
    private QuoridorTray tray;
    private QuoridorDisplay display;
    private QuoridorPlayers[] players;
    private QuoridorFence[] fences;
    private int nbFences;
    public QuoridorGame()
    {
		fences= new QuoridorFence[10];
        tray = new QuoridorTray();
        display = new QuoridorDisplay();
        nbFences=0;
        System.out.println("Nb de joueurs: ");
        Scanner scanNbPlayers = new Scanner(System.in);
        nbPlayers=scanNbPlayers.nextInt();
        Init2Players();
    }
    public void Init2Players()
    {
        Scanner scan;
        String name;
        int pos_x=0;
        int pos_y=4;
        for (int num=1; num<=nbPlayers; num++)
        {
            players=new QuoridorPlayers[nbPlayers];
            System.out.println("Nom du joueur "+num+": ");
            scan= new Scanner(System.in);
            name=scan.nextLine();
            if (num==2)
            {
                pos_x=8;
            }
            players[num-1]= new QuoridorPlayers(num, name, pos_x, pos_y);
            tray.ChangePlayer_inCell(pos_x,pos_y, true);
        }
    }
    public void NewFence()
    {
		nbFences++;
    }
    public void RefreshPosPlayers()
    {
        for (int a=0; a<=nbPlayers; a++)
        {

        }
    }
    public void showDisplay()
    {
        display.showDisplay(tray);
    }
    public int getNbPlayers()
    {
		return nbPlayers;
	}
}
