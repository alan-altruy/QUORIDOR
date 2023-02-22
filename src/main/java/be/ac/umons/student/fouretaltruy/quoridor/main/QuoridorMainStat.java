// ALTRUY ALAN - JASON FOURET //

package be.ac.umons.student.fouretaltruy.quoridor.main;

import be.ac.umons.student.fouretaltruy.quoridor.game.*;

public class QuoridorMainStat
{
    public static void main (String[] args)
    {
        boolean IA1=false, IA2=false;
        int counts=Integer.parseInt(args[0]);
        int var;
        int[] win={0,0};
        if (args[1].equals("difficile"))
        {
            IA1=true;
        }
        if (args[2].equals("difficile"))
        {
            IA2=true;
        }
        for (int a=0; a<counts; a++)
        {
            QuoridorGame game= new QuoridorGame();
            game.init2AI(IA1, IA2);
            while (game.hasWinner()==3)
            {
                for (int player=0; player<2; player++)
                {
                    game.setPlayerWhoIsPlaying(player);
                    game.getAi(player).action();
                }
            }
            if (args.length>3 && (args[3].equals("oui")))
            {
                game.getTray().show();
            }
            var=game.hasWinner();
            win[var]++;
            System.out.println("Winner= "+args[var+1]);
        }
        for (int x=0; x<2; x++)
        System.out.println("L'IA "+args[x+1]+" a gagné "+win[x]+" parties, elle a donc gagné "+(int)(((win[x]+0.0)/counts)*100)+"% des parties ");
    }
}