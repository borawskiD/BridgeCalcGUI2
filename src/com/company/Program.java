package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Program extends JFrame implements ActionListener {
    JTextField pik;
    JTextField kier;
    JTextField karo;
    JTextField trefl;
    JButton zamiana;
    JButton losuj;
    JButton wyczysc;
    JPanel panelTop = new JPanel(new FlowLayout());
    JPanel panelMid = new JPanel(new FlowLayout());
    JPanel panelBot = new JPanel(new FlowLayout());
    static JDialog d;

    public Program() {
        super("Kalkulator wartości ręki");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 135);

        JLabel pikLabel = new JLabel();
        pikLabel.setIcon(new ImageIcon(new ImageIcon("pik.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        pik = new JTextField(10);
        panelTop.add(pikLabel);
        panelTop.add(pik);
        add(panelTop, BorderLayout.NORTH);

        JLabel kierLabel = new JLabel();
        kierLabel.setIcon(new ImageIcon(new ImageIcon("kier.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        kier = new JTextField(10);

        JLabel karoLabel = new JLabel();
        karoLabel.setIcon(new ImageIcon(new ImageIcon("karo.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        karo = new JTextField(10);


        panelMid.add(kierLabel);
        panelMid.add(kier);

        zamiana = new JButton("Punkty");
        zamiana.addActionListener(this);
        panelMid.add(zamiana);
        losuj = new JButton("Losuj");
        losuj.addActionListener(this);
        panelMid.add(losuj);
        wyczysc = new JButton("Wyczyść");
        wyczysc.addActionListener(this);
        panelMid.add(wyczysc);
        panelMid.add(karoLabel);
        panelMid.add(karo);
        add(panelMid, BorderLayout.CENTER);
        //Bottom
        JLabel treflLabel = new JLabel();
        treflLabel.setIcon(new ImageIcon(new ImageIcon("trefl.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        trefl = new JTextField(10);
        panelBot.add(treflLabel);
        panelBot.add(trefl);
        add(panelBot, BorderLayout.SOUTH);
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
                Frame f = new Frame();
                d = new JDialog(f, "Wynik", true);
                d.setLayout(new FlowLayout());
                Button b = new Button("OK");
                b.addActionListener(e1 -> Program.d.setVisible(false));
                d.add(new Label("Wartość ręki: " + value + "\n"));
                d.add(b);
                d.setSize(350, 350);
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
