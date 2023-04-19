// ALTRUY ALAN - JASON FOURET //

package be.ac.umons.student.fouretaltruy.quoridor.players;

import be.ac.umons.student.fouretaltruy.quoridor.game.QuoridorTray;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe regroupant les déplacements possible qu'un joueur de Quoridor peut effectuer
 * qui est implémentée de Serializable
 */
public class QuoridorMovePlayer implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Joueur qui veut effectuer un déplacement
     */
    private final QuoridorPlayers player;
    /**
     * Plateau sur lequel le joueur joue
     */
    private final QuoridorTray tray;
    /**
     * Position x du joueur
     */
    private final int pos_x;
    /**
     * Position y du joueur
     */
    private final int pos_y;
    /**
     * Directions vers lesquelles le joueur peut se déplacer
     */
    private final boolean[] choices = {false, false, false, false, false, false, false, false, false, false, false, false};

    /**
     * Initialise le mouvement d'un joueur
     *
     * @param _player : Le joueur qui souhaite se déplacer
     * @param _tray   : Le plateau sur lequel se trouve le joueur
     */
    public QuoridorMovePlayer(QuoridorPlayers _player, QuoridorTray _tray) {
        player = _player;
        tray = _tray;
        pos_x = player.getPos_x();
        pos_y = player.getPos_y();
    }

    /**
     * Cherche les directions dans lesquelles le oueur peut se déplacer
     */
    public void whereCanMove() {
        //Se deplacer vers le haut, donc qu'il n'y ait pas de Barrière au dessus
        if (tray.getTypeOfCell(pos_x - 1, pos_y) == 0) {
            //Y a-t-il un joueur
            if (tray.getTypeOfCell(pos_x - 2, pos_y) != 0) {
                // Si oui, regarder si barrière au dessus de l'autre joueur
                if (tray.getTypeOfCell(pos_x - 3, pos_y) != 0) {
                    if (tray.getTypeOfCell(pos_x - 2, pos_y - 1) == 0) {
                        choices[7] = true;
                    }
                    if (tray.getTypeOfCell(pos_x - 2, pos_y + 1) == 0) {
                        choices[1] = true;
                    }
                } else {
                    choices[8] = true;
                }
            } else {
                choices[0] = true;
            }
        }
        //Se deplacer vers la droite, donc qu'il n'y ait pas de Barrière à droite
        if (tray.getTypeOfCell(pos_x, pos_y + 1) == 0) {
            //Y a-t-il un joueur
            if (tray.getTypeOfCell(pos_x, pos_y + 2) != 0) {
                // Si oui, regarder si barrière à droite de l'autre joueur
                if (tray.getTypeOfCell(pos_x, pos_y + 3) != 0) {
                    if (tray.getTypeOfCell(pos_x - 1, pos_y + 2) == 0) {
                        choices[1] = true;
                    }
                    if (tray.getTypeOfCell(pos_x + 1, pos_y + 2) == 0) {
                        choices[3] = true;
                    }
                } else {
                    choices[9] = true;
                }
            } else {
                choices[2] = true;
            }
        }
        //Se deplacer vers le bas, donc qu'il n'y ait pas de Barrière en bas
        if (tray.getTypeOfCell(pos_x + 1, pos_y) == 0) {
            //Y a-t-il un joueur
            if (tray.getTypeOfCell(pos_x + 2, pos_y) != 0) {
                // Si oui, regarder si barrière en dessous de l'autre joueur
                if (tray.getTypeOfCell(pos_x + 3, pos_y) != 0) {
                    if (tray.getTypeOfCell(pos_x + 2, pos_y - 1) == 0) {
                        choices[5] = true;
                    }
                    if (tray.getTypeOfCell(pos_x + 2, pos_y + 1) == 0) {
                        choices[3] = true;
                    }
                } else {
                    choices[10] = true;
                }
            } else {
                choices[4] = true;
            }
        }
        //Se deplacer vers la gauche, donc qu'il n'y ait pas de Barrière à gauche
        if (tray.getTypeOfCell(pos_x, pos_y - 1) == 0) {
            //Y a-t-il un joueur
            if (tray.getTypeOfCell(pos_x, pos_y - 2) != 0) {
                // Si oui, regarder si barrière à gauche de l'autre joueur
                if (tray.getTypeOfCell(pos_x, pos_y - 3) != 0) {
                    if (tray.getTypeOfCell(pos_x - 1, pos_y - 2) == 0) {
                        choices[7] = true;
                    }
                    if (tray.getTypeOfCell(pos_x + 1, pos_y - 2) == 0) {
                        choices[5] = true;
                    }
                } else {
                    choices[11] = true;
                }
            } else {
                choices[6] = true;
            }
        }
    }

    /**
     * Retourne les cellules dans lesquelles le joueur peut se déplacer
     *
     * @return Cellules où l'on peut se déplacer
     */
    public boolean[][][] setPosAvailable() {
        boolean[][][] posAvailable = new boolean[19][19][12];
        whereCanMove();
        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < 19; y++) {
                Arrays.fill(posAvailable[x][y], false);
            }
        }
        if (choices[0] && pos_x - 2 < 19 && pos_x - 2 > 0 && pos_y < 19 && pos_y > 0) {
            posAvailable[pos_x - 2][pos_y][0] = true;
        }
        if (choices[1] && pos_x - 2 < 19 && pos_x - 2 > 0 && pos_y + 2 < 19 && pos_y + 2 > 0) {
            posAvailable[pos_x - 2][pos_y + 2][1] = true;
        }
        if (choices[2] && pos_x < 19 && pos_x > 0 && pos_y + 2 < 19 && pos_y + 2 > 0) {
            posAvailable[pos_x][pos_y + 2][2] = true;
        }
        if (choices[3] && pos_x + 2 < 19 && pos_x + 2 > 0 && pos_y + 2 < 19 && pos_y + 2 > 0) {
            posAvailable[pos_x + 2][pos_y + 2][3] = true;
        }
        if (choices[4] && pos_x + 2 < 19 && pos_x + 2 > 0 && pos_y < 19 && pos_y > 0) {
            posAvailable[pos_x + 2][pos_y][4] = true;
        }
        if (choices[5] && pos_x + 2 < 19 && pos_x + 2 > 0 && pos_y - 2 < 19 && pos_y - 2 > 0) {
            posAvailable[pos_x + 2][pos_y - 2][5] = true;
        }
        if (choices[6] && pos_x < 19 && pos_x > 0 && pos_y - 2 < 19 && pos_y - 2 > 0) {
            posAvailable[pos_x][pos_y - 2][6] = true;
        }
        if (choices[7] && pos_x - 2 < 19 && pos_x - 2 > 0 && pos_y - 2 < 19 && pos_y - 2 > 0) {
            posAvailable[pos_x - 2][pos_y - 2][7] = true;
        }
        if (choices[8] && pos_x - 4 < 19 && pos_x - 4 > 0 && pos_y < 19 && pos_y > 0) {
            posAvailable[pos_x - 4][pos_y][8] = true;
        }
        if (choices[9] && pos_x < 19 && pos_x > 0 && pos_y + 4 < 19 && pos_y + 4 > 0) {
            posAvailable[pos_x][pos_y + 4][9] = true;
        }
        if (choices[10] && pos_x + 4 < 19 && pos_x + 4 > 0 && pos_y < 19 && pos_y > 0) {
            posAvailable[pos_x + 4][pos_y][10] = true;
        }
        if (choices[11] && pos_x < 19 && pos_x > 0 && pos_y - 4 < 19 && pos_y - 4 > 0) {
            posAvailable[pos_x][pos_y - 4][11] = true;
        }
        return posAvailable;
    }

    /**
     * Permet de déplacer le joueur à l'emplacement souhaité
     *
     * @param choice : Choix de la direction du déplacement
     */
    public void move(int choice) {
        int x = 0, y = 0;
        switch (choice) {
            case 0:
                x = -2;
                break;
            case 1:
                x = -2;
                y = 2;
                break;
            case 2:
                y = 2;
                break;
            case 3:
                x = 2;
                y = 2;
                break;
            case 4:
                x = 2;
                break;
            case 5:
                x = 2;
                y = -2;
                break;
            case 6:
                y = -2;
                break;
            case 7:
                x = -2;
                y = -2;
                break;
            case 8:
                x = -4;
                break;
            case 9:
                y = 4;
                break;
            case 10:
                x = 4;
                break;
            case 11:
                y = -4;
                break;
        }
        player.newPos(x, y);
    }
}