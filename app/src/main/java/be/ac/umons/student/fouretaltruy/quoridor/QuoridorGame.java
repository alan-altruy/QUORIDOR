package be.ac.umons.student.fouretaltruy.quoridor;
import java.util.Scanner;
public class QuoridorGame
{
    private int nbPlayers;
    private QuoridorTray tray;
    private QuoridorDisplay display;
    private QuoridorPlayers[] players;
    public int InitGame()
    {
        tray = new QuoridorTray();
        display = new QuoridorDisplay();
        System.out.println("Nb de joueurs: ");
        Scanner scanNbPlayers = new Scanner(System.in);
        nbPlayers=scanNbPlayers.nextInt();
        Init2Players();
        return nbPlayers;
    }
    public void Init2Players()
    {
        Scanner scan;
        String name;
        int pos_x=0;
        int pos_y=8;
        for (int num=1; num<=nbPlayers; num++)
        {
            players=new QuoridorPlayers[nbPlayers];
            System.out.println("Nom du joueur "+num+": ");
            scan= new Scanner(System.in);
            name=scan.nextLine();
            if (num==2)
            {
                pos_x=16;
            }
            players[num-1]= new QuoridorPlayers(num, name, pos_x, pos_y);
        }
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
}