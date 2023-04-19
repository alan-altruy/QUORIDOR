package be.ac.umons.student.fouretaltruy.quoridor;

public class QuoridorDisplay
{
    private QuoridorTray tray;
    public void showDisplay(QuoridorTray _tray)
    {
        tray=_tray;
        tray.show();
    }
}