import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SoundPlayer {

    // Methode zum Abspielen einer Sounddatei in einem neuen Thread
    public static void playSound(String filePath) {
        new Thread(() -> {
            try {
                // AudioInputStream aus der angegebenen Datei abrufen
                File soundFile = new File(filePath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

                // Audioformat und Line-Infos abrufen
                AudioFormat format = audioStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);

                // Clip öffnen und Audio-Daten laden
                Clip audioClip = (Clip) AudioSystem.getLine(info);
                audioClip.open(audioStream);
                audioClip.start();

                // Warten, bis der Clip fertig abgespielt ist
                while (!audioClip.isRunning()) {
                    Thread.sleep(100);
                }
                while (audioClip.isRunning()) {
                    Thread.sleep(100);
                }

                // Clip schließen
                audioClip.close();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
