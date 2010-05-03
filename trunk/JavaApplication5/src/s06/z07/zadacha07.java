package s06.z07;

import info.clearthought.layout.TableLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.midi.*;


public class zadacha07 {


    public static void main(String[] args) {


        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();

        for (
                MidiDevice.Info info
                : infos)

        {
            System.out.println(info.getName());
            System.out.println(info.getVersion());
        }

        Synthesizer syn = null;
        for (
                MidiDevice.Info info
                : infos)

        {
            MidiDevice dev = MidiSystem.getMidiDevice(Info infos);
            if (dev instanceof Synthesizer) {
                syn = (Synthesizer) dev;
                break;
            }
        }
        if (syn.equals(null)) System.out.println("Synthesizer not found");

        Sequencer seq = null;
        for (
                MidiDevice.Info info
                : infos)

        {
            MidiDevice devseq = MidiSystem.getMidiDevice(Info info);
            if (devseq instanceof Sequencer) {
                seq = (Sequencer) devseq;
                break;
            }
        }
        if (seq.equals(null)) System.out.println("Sequencer not found");

        Transmitter t = null;
        try {
            t = seq.getTransmitter();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        Receiver r = null;
        try {
            r = syn.getReceiver();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        t.setReceiver(r);

        Sequence se = null;
        try {
            se = MidiSystem.getSequence(new File("../empire.mid"));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            seq.setSequence(se);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }


        TableLayout tl1 = new TableLayout(new double[]{0.3, 0.3, 0.3},
                new double[]{0.5, 0.5});
        JFrame f = new JFrame();
        f.setSize(800, 200);
        f.setLocationRelativeTo(null);
        f.setLayout(tl1);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JButton startb = new JButton("Play");
        final JButton stopb = new JButton("Stop");
        final JButton mute1 = new JButton("Mute 1");
        final JButton mute2 = new JButton("Mute 2");
        final JButton mute3 = new JButton("Mute 3");
        final JButton mute4 = new JButton("Mute 4");


        startb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 seq.start();
            }
        });

        f.add(startb, "0,0");
        f.add(stopb, "0,1");
        f.add(mute1, "1,0");
        f.add(mute2, "1,1");
        f.add(mute3, "2,0");
        f.add(mute4, "2,1");
    }
}
