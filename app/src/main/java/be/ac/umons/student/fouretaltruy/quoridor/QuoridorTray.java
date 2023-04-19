package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //
import java.io.*;
public class QuoridorTray implements Serializable
{
	private static final long serialVersionUID = 1L;
	private QuoridorGame game;
	private final String[]chars = {"●","●","|","─","■"};
	private int[][] cells;
	/**
         * Permet d'initialiser le plateau du jeu.
         * 
         */
	public QuoridorTray(QuoridorGame _game)
	{
		game=_game;
		cells= new int[19][19];
		initCellsAndWalls();
	}
	/**
         * Permet d'initialiser les cellules du plateau élémentaires (cellules vides et murs extérieurs).
         * 
         */
	public boolean canPass()
	{
		return new QuoridorPathFind(game).verif();
	}
	public void initCellsAndWalls()
	{
		for (int x=0; x<19;x++)
		{
			for (int y=0; y<19;y++)
			{
				if (x==0 || x==18 || y==0 || y==18)
				{
					cells[x][y]=5;
				}
				else
				{
					cells[x][y]=0;
				}
			}
		}
	}
	/**
         * Permet de retourner une verifiation permettant de dire si un mur ou une barrière
		 * se situe déjà dans les cellules où il souhaite se placer.
         * 
         * @param pos_x
         *            La position x de l'endroit désiré pour positionner la barrière.
         * @param pos_y
         *            La position y de l'endroit désiré pour positionner la barrière.
         * @param dir
         *            La direction de la barrière désirée (0= vertical , 1= horizontal).
		 * 
		 * @return
		 * 			  La méthode va retourner true si une barrière ou un mur se trouve déjà à cet emplacement
		 *            et retournera false dans le cas contraire.
         */
	public boolean verifAlreadyFenceAndWall(int pos_x, int pos_y, int dir)
	{
		for (int curs=0; curs<=2; curs++)
		{
			int verif0=cells[pos_x+curs][pos_y];
			int verif1=cells[pos_x][pos_y+curs];
			if (dir==0 && (verif0!=0))
			{
				return true;
			}
			else if (dir==1 && (verif1!=0))
			{
				return true;
			}
		}
		return false;
	}
	public void setTypeOfCell(int pos_x, int pos_y, int type)
	{
		cells[pos_x][pos_y]=type;
	}
	public int getTypeOfCell(int pos_x, int pos_y)
	{
		return cells[pos_x][pos_y];
	}
	/*	*
         * Permet d'afficher le plateau
         * 
         */
	public void show()
	{
		String TrayBoard="";
		for (int ligne=0; ligne<19;ligne++)
		{
			for (int cols=0; cols<19; cols++)
			{
				int typeOfCell=cells[ligne][cols];
				if (typeOfCell!=0)
				{
					TrayBoard+= chars[typeOfCell-1];
				}
				else if (cols%2==0 || ligne%2==0)
				{
					TrayBoard+="□";
				}
				else
				{
					TrayBoard+="◌";
				}
				TrayBoard+=" ";
			}
			TrayBoard+="\n";
		}
		System.out.println(TrayBoard);
	}
}