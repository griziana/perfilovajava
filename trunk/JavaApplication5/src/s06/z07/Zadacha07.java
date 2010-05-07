package s06.z07;

import info.clearthought.layout.TableLayout;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;


public class Zadacha07 {
    private static Sequencer seq;
    private static File fi;

    public static void main(String[] args) {

        // Discover Connected Midi Devices - Start
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (
                MidiDevice.Info info
                : infos) {
            System.out.println(info.getName() + " " + info.getVersion() + ": " + info.getDescription());
        }

        // Synthesizer
        Synthesizer syn = null;
        try {
            syn = MidiSystem.getSynthesizer();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
/*        Synthesizer syn = null;
        for (MidiDevice.Info info : infos)

        {
            MidiDevice dev = null;
            try {
                dev = MidiSystem.getMidiDevice(info);
            } catch (MidiUnavailableException e) {
                e.printStackTrace();
            }
            if (dev instanceof Synthesizer) {
                syn = (Synthesizer) dev;
                break;
            }
        }*/
        if (syn == null) System.out.println("Synthesizer not found");

        // Sequencer
        try {
            seq = MidiSystem.getSequencer();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }

/*        Sequencer seq = null;
for (MidiDevice.Info info : infos)

{
    MidiDevice devseq = null;      Mb7Kr2cH8mz2
    try {
        devseq = MidiSystem.getMidiDevice(info);
    } catch (MidiUnavailableException e) {
        e.printStackTrace();
    }
    if (devseq instanceof Sequencer) {
        seq = (Sequencer) devseq;
        break;
    }
}*/
        if (seq == null) System.out.println("Sequencer not found");

        // Transmitter
        Transmitter t = null;
        try {
            t = seq.getTransmitter();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }

        // Receiver
        Receiver r = null;
        try {
            if (syn != null) {
                r = syn.getReceiver();
            }
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        if (t != null) {
            t.setReceiver(r);
        }


        TableLayout tl1 = new TableLayout(new double[]{0.333, 0.333, 0.333},
                new double[]{0.5, 0.5});
        final JFrame f = new JFrame();
        f.setSize(300, 200);
        f.setLocationRelativeTo(null);
        f.setLayout(tl1);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JButton chooseb = new JButton("Open");
        final JButton startb = new JButton("Play");
        final JButton stopb = new JButton("Stop");
        final JToggleButton mute1 = new JToggleButton("Mute 1");
        final JToggleButton mute2 = new JToggleButton("Mute 2");
        final JToggleButton mute3 = new JToggleButton("Mute 3");


        chooseb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("MIDI-פאיכ (*.mid)", "mid");
                JFileChooser fc = new JFileChooser(".");
                fc.addChoosableFileFilter(filter);
                int returnv = fc.showOpenDialog(fc);
                if (returnv == JFileChooser.APPROVE_OPTION) {
                    fi = fc.getSelectedFile();
                    mute1.setSelected(false);
                    mute2.setSelected(false);
                    mute3.setSelected(false);
                } else {
                    System.out.println("To continue work choose the MIDI file.");
                }
            }
        });

        startb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sequence se;
                try {
                    se = MidiSystem.getSequence(fi);
                    seq.setSequence(se);
                    seq.open();
                    seq.start();
  //                  System.out.println(seq.getSequence().getTracks().length);


                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });

        stopb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seq.stop();
                seq.close();
            }
        });

        mute1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!seq.getTrackMute(0)) {
                    seq.setTrackMute(0, true);
                } else seq.setTrackMute(0, false);
            }
        });

        mute2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!seq.getTrackMute(1)) {
                    seq.setTrackMute(1, true);
                } else seq.setTrackMute(1, false);
            }
        });

        mute2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!seq.getTrackMute(2)) {
                    seq.setTrackMute(2, true);
                } else seq.setTrackMute(2, false);
            }
        });

        f.add(chooseb, "0,0");
        f.add(startb, "1,0");
        f.add(stopb, "2,0");
        f.add(mute1, "0,1");
        f.add(mute2, "1,1");
        f.add(mute3, "2,1");


        f.setVisible(true);
    }
}
