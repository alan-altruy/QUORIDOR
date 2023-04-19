package be.ac.umons.student.fouretaltruy.quoridor;// ALTRUY ALAN - JASON FOURET //

import java.io.*;
import javax.sound.sampled.*;

/**
 * Classe permettant de jouer des sons pour le jeu Quoridor qui hérite de Threads et qui
 * est implémentée de Serializable
 */
public class QuoridorSound extends Thread implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
         * Liste des sons diponibles
         */
    private String[] nameObj= {"steve","creeper", "button", "fence"};
    /**
         * Liste des sons disponibles
         */
    private Clip[] sound= new Clip[4];
    /**
         * Initialise les sons du Quoridor
         */
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
    /**
         * Permet de lancer un son
         * @param numOfSong : Numéro attribué au son
         */
    public void play (int numOfSong)
    {
        sound[numOfSong].loop(0);
    }
}