package ui;

import logic.Berechnung;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministrationWindow {

    AdministrationWindow(Berechnung berechnung) {

        JFrame frame = new JFrame("Administration");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(panel_main);
        frame.setVisible(true);
        frame.pack();
        button_time_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float preis = Float.parseFloat(input_min.getText().replaceAll(",", "."));
                    berechnung.setPreis_pro_minute(preis);
                    label_output_status.setText("Preis pro Minute wurde gesetzt auf: ");
                    output_changes.setText(String.valueOf(preis));
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "Fehler: " + e1.getMessage());
                }
                frame.pack();
            }
        });
        button_km_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    float preis = Float.parseFloat(input_km.getText().replaceAll(",", "."));
                    berechnung.setPreis_pro_km(preis);
                    label_output_status.setText("Preis pro Kilometer wurde gesetzt auf: ");
                    output_changes.setText(String.valueOf(preis));
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "Fehler: " + e1.getMessage());
                }
                frame.pack();
            }
        });
        button_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });


        button_entsperrpreis_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    float preis = Float.parseFloat(input_entsperrpreis.getText().replaceAll(",", "."));
                    berechnung.setPreis_pro_entsperrung(preis);
                    label_output_status.setText("Entsperrpreis wurde gesetzt auf:");
                    output_changes.setText(String.valueOf(preis));
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "Fehler: " + e1.getMessage());
                }
                frame.pack();
            }
        });

        input_entsperrpreis.setText(String.valueOf(berechnung.getPreis_pro_entsperrung()));
        input_min.setText(String.valueOf(berechnung.getPreis_pro_minute()));
        input_km.setText(String.valueOf(berechnung.getPreis_pro_km()));


        frame.pack();
    }
    private JPanel panel_main;
    private JTextField input_min;
    private JLabel label_time;
    private JLabel label_km;
    private JButton button_time_save;
    private JTextField input_entsperrpreis;
    private JButton button_km_save;
    private JButton button_entsperrpreis_save;
    private JButton button_close;
    private JLabel label_entsperrpreis;
    private JLabel label_entsperrpreis_curr;
    private JLabel label_km_curr;
    private JLabel label_time_curr;
    private JLabel label_admin_title;
    private JLabel label_output_status;
    private JTextField input_km;
    private JTextField output_changes;


}
