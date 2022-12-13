package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program extends JFrame implements ActionListener {
    JTextField pik;
    JTextField kier;
    JTextField karo;
    JTextField trefl;
    JButton zamiana;
    JButton losuj;
    JButton wyczysc;
    static JDialog d;
    BufferedImage image;
    public Program() {
        super("Kalkulator wartości ręki");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(600, 150);
        setBackground(Color.lightGray);

        JLabel pikLabel = new JLabel();
        pikLabel.setIcon(new ImageIcon(new ImageIcon("pik.png").getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT)));
        add(pikLabel);
        pik = new JTextField(10);
        add(pik);
        JLabel kierLabel = new JLabel();
        kierLabel.setIcon(new ImageIcon(new ImageIcon("kier.png").getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT)));
        kier = new JTextField(10);
        add(kierLabel);
        add(kier);

        JLabel karoLabel = new JLabel();
        karoLabel.setIcon(new ImageIcon(new ImageIcon("karo.png").getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT)));
        karo = new JTextField(10);
        add(karoLabel);
        add(karo);

        JLabel treflLabel = new JLabel();
        treflLabel.setIcon(new ImageIcon(new ImageIcon("trefl.png").getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT)));
        trefl = new JTextField(10);
        add(treflLabel);
        add(trefl);
        zamiana = new JButton("Punkty");
        zamiana.addActionListener(this);
        add(zamiana);
        losuj = new JButton("Losuj");
        losuj.addActionListener(this);
        add(losuj);
        wyczysc = new JButton("Wyczyść");
        wyczysc.addActionListener(this);
        add(wyczysc);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        if (label.equals("Punkty")) {
            try {
                String pikValue = pik.getText();
                String kierValue = kier.getText();
                String karoValue = karo.getText();
                String treflValue = trefl.getText();
                String input = "S" + pikValue + "H" + kierValue + "D" + karoValue + "C" + treflValue;
                Integer value = new Calc().run(input);
                Frame f= new Frame();
                d = new JDialog(f , "Wynik" , true);
                d.setLayout( new FlowLayout() );
                Button b = new Button ("OK");
                b.addActionListener (e1 -> Program.d.setVisible(false));
                d.add( new Label ("Wartość ręki: " + value + "\n") );
                d.add(b);
                d.setSize(350,350);
                d.setVisible(true);
            } catch (NumberFormatException ev) {
                System.out.println("Blad argumentow!? Wpisz poprawne wartosci!");
            }
        } else if (label.equals("Losuj")) {
            String[] output = new RandomDeck().run();
            trefl.setText(output[3].replaceAll("X", "10").replaceAll("C", ""));
            kier.setText(output[1].replaceAll("X", "10").replaceAll("H", ""));
            karo.setText(output[2].replaceAll("X", "10").replaceAll("D", ""));
            pik.setText(output[0].replaceAll("X", "10").replaceAll("S", ""));
        } else if (label.equals("Wyczyść")) {
            trefl.setText("");
            kier.setText("");
            karo.setText("");
            pik.setText("");
        } else if (label.equals("Zamknij")) {
            System.exit(0);
        }

    }
}
