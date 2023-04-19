// ALTRUY ALAN - JASON FOURET //

package be.ac.umons.student.fouretaltruy.quoridor.game;

import java.io.Serializable;

/**
 * Plateau du jeu Quoridor qui est implémentée de Serializable
 */
public class QuoridorTray implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Jeu associé au plateau
     */
    private final QuoridorGame game;
    /**
     * Caractères utilisés pour afficher le plateau sur un terminal
     */
    private final String[] chars = {"●", "●", "|", "─", "■", "|", "─"};
    /**
     * Cellules du plateau
     */
    private final int[][] cells;

    /**
     * Initialise le plateau du jeu
     *
     * @param _game : Jeu associé au plateau
     */
    public QuoridorTray(QuoridorGame _game) {
        game = _game;
        cells = new int[19][19];
        initCellsAndWalls();
    }

    /**
     * Permet d'initialiser les cellules du plateau élémentaires (cellules vides et murs extérieurs)
     */
    public void initCellsAndWalls() {
        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < 19; y++) {
                if (x == 0 || x == 18 || y == 0 || y == 18) {
                    cells[x][y] = 5;
                } else {
                    cells[x][y] = 0;
                }
            }
        }
    }

    /**
     * Permet de verifier si un mur ou une barrière se situe déjà dans les cellules où une barrière soit se positionner
     *
     * @param pos_x : La position x désirée pour positionner la barrière
     * @param pos_y : La position y désirée pour positionner la barrière
     * @param dir   : La direction désirée pour positionner la barrière
     * @return True si une barrière ou un mur se trouve déjà à cet emplacement
     * <li>False sinon</li>
     */
    public boolean verifAlreadyFenceAndWall(int pos_x, int pos_y, int dir) {
        for (int curs = 0; curs <= 2; curs++) {
            if (dir == 0 && pos_x + curs < 19) {
                if (cells[pos_x + curs][pos_y] != 0) {
                    return false;
                }
            } else if (dir == 1 && pos_y + curs < 19) {
                if (cells[pos_x][pos_y + curs] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Permet d'afficher le plateau avec interface rudimentaire (terminal)
     */
    public void show() {
        StringBuilder TrayBoard = new StringBuilder();
        for (int ligne = 0; ligne < 19; ligne++) {
            for (int cols = 0; cols < 19; cols++) {
                int typeOfCell = cells[ligne][cols];
                if (typeOfCell != 0) {
                    TrayBoard.append(chars[typeOfCell - 1]);
                } else if (cols % 2 == 0 || ligne % 2 == 0) {
                    TrayBoard.append("□");
                } else {
                    TrayBoard.append("◌");
                }
                TrayBoard.append(" ");
            }
            TrayBoard.append("\n");
        }
        System.out.println(TrayBoard);
    }

    /**
     * Permet de modifier le type d'une cellule du plateau
     *
     * @param pos_x : Position x de la cellule
     * @param pos_y : Position y de la cellule
     * @param type  : Nouveau type de la cellule
     */
    public void setTypeOfCell(int pos_x, int pos_y, int type) {
        cells[pos_x][pos_y] = type;
    }

    /**
     * Permet de verifier si les joueurs peuvent accéder aux cellules gagnantes
     *
     * @return True si les joueurs peuvent passer malgré les barrières positionnées
     * <li>False sinon</li>
     */
    public boolean canPass() {
        return new QuoridorPathFind(game).verif();
    }

    /**
     * Permet de retourner le type d'une cellule
     *
     * @param pos_x : Position x de la cellule
     * @param pos_y : Position y de la cellule
     * @return Type de la cellule
     */
    public int getTypeOfCell(int pos_x, int pos_y) {
        return cells[pos_x][pos_y];
    }
}