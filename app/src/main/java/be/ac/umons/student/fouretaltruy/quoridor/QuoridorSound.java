package be.ac.umons.student.fouretaltruy.quoridor;

import java.io.*;
import javax.sound.sampled.*;

public class QuoridorSound extends Thread implements Serializable
{
    private AudioInputStream audio=null;
    private Clip sound=null;
    private static final long serialVersionUID = 1L;
    public QuoridorSound(String nameObj)
    {
        try {
            File file = new File("src/main/resources/sounds/"+nameObj+"sound.wav");
            audio = AudioSystem.getAudioInputStream(file);
            sound = AudioSystem.getClip();
            sound.open(audio);
            } catch (Exception e) {
            
            System.out.println("Audiofile not found: "+nameObj+"sound.wav");
        }
            sound.start();      
    }
}