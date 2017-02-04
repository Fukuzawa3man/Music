package wac.is.god;

import javax.sound.midi.* ;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.*;


public class Main extends JFrame implements KeyListener{
   public JLabel label1;
    int key=0;

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
                channel.noteOn(48, 127);
            }else{
                channel.noteOff(128);
                //Synsesizerを閉じる
                synthesizer.close();
            }
        } catch(Exception e){
            if(channel != null)  channel.allNotesOff();
        }
    }


    public void keyPressed(KeyEvent e){
        key=1;
        MIDI(key);
        label1.setText("押されたで");
        repaint();
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

