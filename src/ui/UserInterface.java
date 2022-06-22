package ui;

import logic.UserAdministration;
import logic.Calculation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class UserInterface {
    private final Calculation calculation;
    private final UserAdministration userAdministration;


    private final JFrame frame;
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
    private JLabel label_curr_price_min;
    private JLabel label_curr_price_km;
    private JButton button_update_prices;
    private JLabel label_curr_price_unlock;

    /**
     * User Interface Constructor
     * erstellt das User Interface und behandelt die ActionListener vom Hauptfenster
     */
    public UserInterface() {
        frame = new JFrame("ScooTec - Scooter Preisrechner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setContentPane(panel_main);

        calculation = new Calculation(0.5f, 0.2f);
        userAdministration = new UserAdministration();

        radio_min.setSelected(true);
        radio_min.setActionCommand("min");
        radio_km.setActionCommand("km");

        ButtonGroup group = new ButtonGroup();
        group.add(radio_min);
        group.add(radio_km);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        /**
         *  Actionlistener, um Verwaltungsfenster zu öffnen bzw. zuerst das Anmelde-Fenster
         */
        button_admin_section.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthorizationWindow authorizationWindow = new AuthorizationWindow(calculation, userAdministration);
            }
        });

        // Listener vom Close Button
        button_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                frame.dispose();
            }
        });

        // Listener vom Berechnen Button, der unter anderem Input abfragt und auch die Komma in Punkte umwandelt
        // inkl. Exceptionhandling
        button_calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setForeground(new Color(0, 0, 0));
                try {
                    // Berechnet den Preis und setzt den Text im Output-Feld je nach Auswahl des Radio-Buttons
                    if (radio_min.isSelected()) {
                        double preis_min = Double.parseDouble(input_min_km.getText().replaceAll(",", "\\."));
                        float preis = calculation.calcPricePerMin((float) Math.ceil(preis_min));
                        output.setText(String.valueOf(preis));
                    } else if (radio_km.isSelected()) {
                        double preis_km = Double.parseDouble(input_min_km.getText().replaceAll(",", "\\."));
                        float preis = calculation.calcPricePerKM((float) Math.ceil(preis_km));
                        output.setText(String.valueOf(preis));
                    }
                } catch (Exception e1) {
                    output.setText("Bitte einen gültigen Wert eingeben!");
                    output.setForeground(new Color(255, 0, 0));
                }
            }
        });

        // Wenn Minute ausgewählt wird, dann wird Text geändert
        radio_min.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input_min_km.setText("");
                label_enter.setText("Minuten:");
            }
        });

        // Wenn Kilometer ausgewählt wird, dann wird Text geändert
        radio_km.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input_min_km.setText("");
                label_enter.setText("Kilometer:");

            }
        });

        // KeyListener, um unerlaubte Inhalte beim Input abzufangen // nur Zahlen und Kommata/Punkte sind zulässig
        // außerdem soll bei Enter der Berechnungs-Button ausgelöst werden
        input_min_km.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent event) {
                char c = event.getKeyChar();
                // Enter triggert den Button

                if (c == KeyEvent.VK_ENTER) {
                    button_calculate.doClick();
                }
                // Zahlen erlauben inkls Backspace und Entfernen, Komma und Punkte (keine Buchstaben)
                if (!(
                        Character.isDigit(c)
                                || (c == KeyEvent.VK_BACK_SPACE)
                                || (c == KeyEvent.VK_DELETE)
                                || c == KeyEvent.VK_COMMA
                                || c == KeyEvent.VK_PERIOD
                )) {
                    event.consume();
                }
            }
        });

        // Listener: Inputfeld leeren
        button_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("");
            }
        });

        label_curr_price_km.setText("Aktuell: " + calculation.getPrice_per_km() + "€");
        label_curr_price_min.setText("Aktuell: " + calculation.getPrice_per_min() + "€");
        label_curr_price_unlock.setText("Entsperrpreis: " + calculation.getPrice_per_unlock() + "€");

        button_update_prices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label_curr_price_km.setText("Aktuell: " + calculation.getPrice_per_km() + "€");
                label_curr_price_min.setText("Aktuell: " + calculation.getPrice_per_min() + "€");
                label_curr_price_unlock.setText("Entsperrpreis: " + calculation.getPrice_per_unlock() + "€");
            }
        });
    }
}
