package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Program extends Frame implements ActionListener {
    TextField pik;
    TextField kier;
    TextField karo;
    TextField trefl;
    Button zamiana;
    Button losuj;
    Button wyczysc;
    static Dialog d;

    public Program() {
        super("Kalkulator wartości ręki");
        BufferedImage imageFile = new BufferedImage("/trefl.png");
        try{
            imageFile = ImageIO.read(imageFile);
        }catch(Exception e){

        }
        setSize(600, 150);
        pik = new TextField(10);
        add(pik);
        add(new Label("Kier:"));
        kier = new TextField(10);
        add(kier);
        add(new Label("Karo:"));
        karo = new TextField(10);
        add(karo);
        add(new Label("Trefl:"));
        trefl = new TextField(10);
        add(trefl);
        setLayout(new FlowLayout());
        setBackground(Color.lightGray);
        zamiana = new Button("Punkty");
        zamiana.addActionListener(this);
        add(zamiana);
        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);
        Menu menu = new Menu("Plik");
        MenuItem mi = new MenuItem("Zamknij", new MenuShortcut('1'));
        menu.add(mi);
        menu.addActionListener(this);
        menuBar.add(menu);
        losuj = new Button("Losuj");
        losuj.addActionListener(this);
        add(losuj);
        wyczysc = new Button("Wyczyść");
        wyczysc.addActionListener(this);
        add(wyczysc);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
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
                d = new Dialog(f , "Wynik" , true);
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
