package ui;

import error.AccountNotFoundException;
import logic.UserAdministration;
import logic.Calculation;

import javax.swing.*;
import java.awt.event.*;

public class AuthorizationWindow extends JDialog {
    private final UserAdministration userAdministration;

    private JPanel contentPane;
    private JButton button_login;
    private JButton button_cancel;
    private JTextField input_username;
    private JPasswordField input_password;
    private JLabel label_username;
    private JLabel label_password;
    private JPanel panel_main;

    public AuthorizationWindow(Calculation calculation, UserAdministration userAdministration) {
        setContentPane(contentPane);
        setTitle("Authentifizierung");
        setModal(true);
        getRootPane().setDefaultButton(button_login);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.userAdministration = userAdministration;

        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
            }
        });

        button_login.addActionListener(e -> {

            if (input_username.getText().equals("") || String.valueOf(input_password.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Benutzernamen und ein Passwort ein!");
            } else {
                try {
                    // Prüft, ob etwas eingeben wurde
                    if (AuthorizationWindow.this.userAdministration.getUser(input_username.getText()) != null) {
                        // Prüft, ob das Passwort stimmt
                        if (AuthorizationWindow.this.userAdministration.getUser(input_username.getText()).checkPassword(String.valueOf(input_password.getPassword()))) {
                            // Prüft, ob der Benutzer ein Administrator ist
                            if (AuthorizationWindow.this.userAdministration.isAdmin(input_username.getText())) {
                                AdministrationWindow administrationWindow = new AdministrationWindow(calculation);
                            } else {
                                JOptionPane.showMessageDialog(null, "Nicht autorisiert!");
                            }
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
        });

        button_cancel.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
