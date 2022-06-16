package ui;

import error.AccountNotFoundException;
import logic.Benutzerverwaltung;
import logic.Berechnung;

import javax.swing.*;
import java.awt.event.*;

public class AuthorizationWindow extends JDialog {
    private Benutzerverwaltung benutzerverwaltung;

    private JPanel contentPane;
    private JButton button_login;
    private JButton button_cancel;
    private JTextField input_username;
    private JPasswordField input_password;
    private JLabel label_username;
    private JLabel label_password;
    private JPanel panel_main;

    private final Berechnung berechnung;

    public AuthorizationWindow(Berechnung berechnung, Benutzerverwaltung benutzerverwaltung) {
        setContentPane(contentPane);
        setTitle("Authentifizierung");
        setModal(true);
        getRootPane().setDefaultButton(button_login);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.berechnung = berechnung;
        this.benutzerverwaltung = benutzerverwaltung;

        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
            }
        });

        button_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (input_username.getText().equals("") || input_password.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Benutzernamen und ein Passwort ein!");
                } else {
                    try {
                        if (AuthorizationWindow.this.benutzerverwaltung.getBenutzer(input_username.getText()) != null) {
                            if (AuthorizationWindow.this.benutzerverwaltung.getBenutzer(input_username.getText()).checkPasswort(String.valueOf(input_password.getPassword()))) {
                                AdministrationWindow administrationWindow = new AdministrationWindow(berechnung);;
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Falsches Passwort!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Benutzer nicht gefunden!");
                        }
                    } catch (AccountNotFoundException e1) {
                        JOptionPane.showMessageDialog(null, "Benutzername oder Passwort falsch!");
                    }
                }
            }
        });

        button_cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
