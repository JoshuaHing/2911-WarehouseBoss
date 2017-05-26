import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Music {
  private String path = new String("music//");
  private String file = new String("He's a Pirate.midi");

  private Sequence seq;
  private Sequencer midi;
  boolean sign; // determining playing or not
  public Music() {
    loadMusic();
  }
  public void loadMusic() {
    try {
      //read music files
      seq  = MidiSystem.getSequence(new File(path + file));
      //get a music player
      midi = MidiSystem.getSequencer();
      // open music player
      midi.open();
      //put music into music player
      midi.setSequence(seq);
      // start to play
      midi.start();
      // playing
      midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);

    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    sign = true;
  }

  public void stopPlay() {
    midi.stop();
    midi.close();
    sign = false;
  }

  public boolean isPlay() {
    return sign;
  }

  public void setMusic(String e){
    file = e;
  }

}
