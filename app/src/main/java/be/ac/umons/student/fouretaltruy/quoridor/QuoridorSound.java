package be.ac.umons.student.fouretaltruy.quoridor;

import java.io.*;
import javax.sound.sampled.*;

public class QuoridorSound extends Thread implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String[] nameObj= {"steve","creeper", "button", "fence"};
    private Clip[] sound= new Clip[4];
    public QuoridorSound()
    {
        AudioInputStream audio;
        File file;
        for (int a=0; a<4; a++)
        {
            try {
                file = new File("src/main/resources/sounds/"+nameObj[a]+"sound.wav");
                audio = AudioSystem.getAudioInputStream(file);
                sound[a] = AudioSystem.getClip();
                sound[a].open(audio);
                } catch (Exception e) {
                
                System.out.println("Audiofile not found: "+nameObj[a]+"sound.wav");
            }
        }
    }
    public void play (int num)
    {
        sound[num].loop(0);
    }
}