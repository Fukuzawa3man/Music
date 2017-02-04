package wac.is.god;

import javax.sound.midi.* ;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.*;

public class Main extends JFrame implements KeyListener{
   public JLabel label1;
    int key=0;
    int pich[] = {84,60,62,64,64,72,74,79,74,86,79,74,79,76,79,84,48,65,58,63,65,61,60,67,64,65,58,65,53,60,65,63,51,50,53,59,62,68,67,65,63,62,47,50,55,62,79,47};
    int n=0;

    public static void main(String[] args) {

        Main aa= new Main("タイトル");
        aa.setVisible(true);
    }

    public Main(String title){
        setTitle(title);
        setFocusable(true);
        addKeyListener(this);
        setBounds(100,100,300,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p=new JPanel();

        label1 = new JLabel("こんにちは");

        p.add(label1);

        Container contentPane= getContentPane();
        contentPane.add(p);
    }

    public void MIDI(int key){
        MidiChannel channel = null;
        try {
            //システムからSynsesizer を得て、開く。
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            //システムからSoundbank を得る
            Soundbank soundbank = synthesizer.getDefaultSoundbank();

            //Soundbank 中の最初のInstrument(音色)を使うよう設定する
            Instrument[] instruments = synthesizer.getDefaultSoundbank().getInstruments();
            synthesizer.loadInstrument(instruments[0]);
            //SynsesizerからChannelを得る。
            channel = synthesizer.getChannels()[0];

            //音の高さ48、強さ127で1秒間鳴らす
            if(key==1) {
                channel.noteOn(pich[n], 127);
            }else{
                channel.noteOff(128);
                //Synsesizerを閉じる
                synthesizer.close();
                n++;

            }
        } catch(Exception e){
            if(channel != null)  channel.allNotesOff();
        }
    }


    public void keyPressed(KeyEvent e){
        if(key==0) {
            key = 1;
            MIDI(key);
            label1.setText("押されたで");
            repaint();
        }
    }

    public void keyReleased(KeyEvent e){
        key=0;
        MIDI(key);
        label1.setText("離れたで");
        repaint();
    }
    public void keyTyped(KeyEvent e){

    }
    }

