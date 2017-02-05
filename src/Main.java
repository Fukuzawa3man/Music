package wac.is.god;

import javax.sound.midi.* ;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.*;

public class Main extends JFrame implements KeyListener{
   public JLabel label1;
    int key=0;
    int pich[] = {84,60,62,64,64,72,74,79,74,86,79,74,79,76,79,84,48,65,58,63,65,61,60,67,64,65,58,65,53,60,65,63,51,50,53,59,62,68,67,65,63,62,47,50,55,62,79,47,48,50,51,55,50,55,48,55,55,60,58,56,55,53,51,53,50,63,67,62,67,60,67,67,72,70,68,67,65,63,65,65,67,67,63,65,67,70,70,61,64,67,70,68,62,63,65,68,67,62,63,67,84,79,77,57,60,62,63,65,63,62,58,55,51,55,56,58,70,69,72,71,72,67,55,50,48,55,67,74,71,67,62,59,62,67,74,79,77,72,74,77,79,72,72,74,75,80,82,75,70,75,79,67,62,67,75,74,70,68,80,82,79,80,60,51,60,72,63,72,60,59};
    int n=0;

    public static void main(String[] args) {

        Main aa= new Main("ピアノ協奏曲1番 蠍火");
        aa.setVisible(true);
    }

    public Main(String title){
        setTitle(title);
        setFocusable(true);
        addKeyListener(this);
        setBounds(100,100,300,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p=new JPanel();

        label1 = new JLabel("キーボードを押して蠍火を演奏しよう");

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
        }
    }

    public void keyReleased(KeyEvent e){
        key=0;
        MIDI(key);

    }
    public void keyTyped(KeyEvent e){

    }
    }

