package s06.z07;

import info.clearthought.layout.TableLayout;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class zadacha07 {
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
    MidiDevice devseq = null;
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
        final JFrame[] f = {new JFrame()};
        f[0].setSize(300, 200);
        f[0].setLocationRelativeTo(null);
        f[0].setLayout(tl1);
        f[0].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JButton chooseb = new JButton("Open");
        final JButton startb = new JButton("Play");
        final JButton stopb = new JButton("Stop");
        final JButton mute1 = new JButton("Mute 1");
        final JButton mute2 = new JButton("Mute 2");
        final JButton mute3 = new JButton("Mute 3");


        chooseb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("MIDI-פאיכ (*.mid)", "mid");
                JFileChooser fc = new JFileChooser(".");
                fc.addChoosableFileFilter(filter);
                int returnv = fc.showOpenDialog(fc);
                if (returnv == JFileChooser.APPROVE_OPTION) {
                    fi = fc.getSelectedFile();

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
                    System.out.println(seq.getSequence().getTracks().length);


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

        f[0].add(chooseb, "0,0");
        f[0].add(startb, "1,0");
        f[0].add(stopb, "2,0");
        f[0].add(mute1, "0,1");
        f[0].add(mute2, "1,1");
        f[0].add(mute3, "2,1");


        f[0].setVisible(true);
    }
}
