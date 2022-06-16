package ui;

import logic.Benutzerverwaltung;
import logic.Berechnung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;

public class UserInterface {
    private Berechnung berechnung;
    private Benutzerverwaltung benutzerverwaltung;


    private JFrame frame;
    private JPanel panel_main;
    private JLabel label_enter;
    private JTextField input_min_km;
    private JButton button_calculate;
    private JLabel label_main_title;
    private JButton button_close;
    private JRadioButton radio_min;
    private JRadioButton radio_km;
    private JTextField output;
    private JButton button_admin_section;
    private JButton button_clear;

    public UserInterface() {
        frame = new JFrame("ScooTec - Scooter Preisrechner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setContentPane(panel_main);

        berechnung = new Berechnung(0.5f, 0.2f);
        benutzerverwaltung = new Benutzerverwaltung();

        radio_min.setSelected(true);
        radio_min.setActionCommand("min");
        radio_km.setActionCommand("km");

        ButtonGroup group = new ButtonGroup();
        group.add(radio_min);
        group.add(radio_km);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        button_admin_section.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthorizationWindow authorizationWindow = new AuthorizationWindow(berechnung, benutzerverwaltung);
            }
        });
        button_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                frame.dispose();
            }
        });
        button_calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setForeground(new Color(0, 0, 0));
                try {
                    // Berechnet den Preis und setzt den Text im Output-Feld je nach Auswahl des Radio-Buttons
                    if (radio_min.isSelected()) {
                        double preis_min = Double.parseDouble(input_min_km.getText().replaceAll(",", "\\."));
                        float preis = berechnung.berechnePreisProMinute((float) Math.ceil(preis_min));
                        output.setText(String.valueOf(preis));
                    } else if (radio_km.isSelected()) {
                        double preis_km = Double.parseDouble(input_min_km.getText().replaceAll(",", "\\."));
                        float preis = berechnung.berechnePreisProStrecke((float) Math.ceil(preis_km));
                        output.setText(String.valueOf(preis));
                    }
                } catch (Exception e1) {
                    output.setText("Bitte einen gültigen Wert eingeben!");
                    output.setForeground(new Color(255, 0, 0));
                }
            }
        });
        radio_min.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input_min_km.setText("");
                label_enter.setText("Minuten:");
            }
        });
        radio_km.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input_min_km.setText("");
                label_enter.setText("Kilometer:");
            }
        });

        input_min_km.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                // Komma und Punkt erlauben
                if (c == '.' || c == ',') {
                } else {
                    // Enter triggert den Button
                    if (c == KeyEvent.VK_ENTER) {
                        button_calculate.doClick();
                    }
                    // Zahlen erlauben (keine Buchstaben)
                    if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                        evt.consume();
                    }
                }
            }
        });

        button_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("");
            }
        });
    }
}
