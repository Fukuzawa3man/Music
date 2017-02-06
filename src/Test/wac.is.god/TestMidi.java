package wac.is.god;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestMidi {
     @Test
     public void MIDI() throws Exception {
         Main main = new Main();
         for(int i = 0; i < 200; i++){
            main.MIDI(1);
            main.MIDI(0);
         }
    }
}
