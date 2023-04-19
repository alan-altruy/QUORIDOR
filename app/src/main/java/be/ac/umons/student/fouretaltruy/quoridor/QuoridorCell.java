package be.ac.umons.student.fouretaltruy.quoridor;

public class QuoridorCell
{
    private int pos_x, pos_y, type;
    public QuoridorCell(int _pos_x, int _pos_y, int _type)
    {
        /* 
        <type> est une variable qui permet de savoir quel objet se trouve dans la cellule,
           si <type>=0 --> rien ne se trouve dans cette cellule
           si <type>=1 --> un joueur(<player>) se trouve dans cette cellule
           si <type>=2 --> une barrière verticale(fence) se trouve dans cette cellule
           si <type>=3 --> une barrière horizontale(fence) se trouve dans cette cellule
           si <type>=4 --> un mur(wall) se trouve dans cette cellule
        */
        type=_type;
        pos_x=_pos_x;
        pos_y=_pos_y;
    }
    public void ChangeType(int _type)
    {
        type= _type;
    }
    public int GetType()
    {
        return type;
    }
}