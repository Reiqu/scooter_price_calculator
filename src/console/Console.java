package console;

import error.AccountNotFoundException;
import logic.Benutzerverwaltung;
import logic.Berechnung;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Console {
    private int wahl;
    private final Berechnung berechnung;
    private final Benutzerverwaltung benutzerverwaltung;
    public Console() {
        this.benutzerverwaltung = new Benutzerverwaltung();
        this.berechnung = new Berechnung(0.5f, 1.0f);

        auswahl();
    }

    @SuppressWarnings("InfiniteRecursion")
    private void auswahl() {;
        Scanner scanner = new Scanner(System.in);
        System.out.println("ScooTec Scooter Calculator!");
        System.out.println();
        System.out.println("Bitte geben sie ein, was sie machen wollen");
        System.out.println("[1] Streckenpreis berechnen");
        System.out.println("[2] Zeitpreis berechnen");
        System.out.println("[3] Preise anzeigen");
        System.out.println("[4] Preise bearbeiten");
        System.out.println("[5] Programm beenden");
        try {
            this.wahl = Integer.parseInt(scanner.nextLine());
            if (this.wahl == 1) {
                streckenpreis();
            } else if (this.wahl == 2) {
                zeitpreis();
            } else if (this.wahl == 3) {
                preiseAnzeigen();
            } else if (this.wahl == 4) {
                preiseBearbeiten();
            } else if (this.wahl == 5) {
                System.out.println("Programm beenden..");
                System.exit(0);
            } else {
                System.out.println("Falsche Eingabe. Bitte erneut versuchen");
                auswahl();
            }
            auswahl();
        } catch (Exception e) {
            System.out.println("Falsche Eingabe! Bitte noch einmal versuchen.");
            auswahl();
        }
    }

    private void streckenpreis() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Bitte geben sie die Strecke in km an: ");
        try {
            String streckeString = scanner.nextLine();
            float strecke = Float.parseFloat(streckeString.replaceAll(",", "\\."));
            System.out.println("Die Strecke betraegt: " + strecke + " km");
            float preis = berechnung.berechnePreisProStrecke(strecke);
            System.out.println("Der Preis betraegt: " + preis + " Euro");

        } catch (Exception e) {
            errorHandling("km");
        }
    }

    private void preiseAnzeigen() {
        System.out.println("Preise: ");
        System.out.println("Preis pro Minute: " + berechnung.getPreis_pro_minute() + " Euro");
        System.out.println("Preis pro Kilometer: " + berechnung.getPreis_pro_km() + " Euro");
        System.out.println("Preis pro Entsprechung: " + berechnung.getPreis_pro_entsperrung() + " Euro");
    }

    private void preiseBearbeiten() {
        Scanner scanner = new Scanner(System.in);
        java.io.Console console = System.console();
        try {

            System.out.println("Bitte authorisieren Sie sich");

//            String username = console.readLine("Nutzername: ");
 //           String password = Arrays.toString(console.readPassword("Passwort: "));

            java.io.Console cnsl = System.console();

            if (cnsl == null) {
                System.out.println(
                        "No console available");
                return;
            }

            // Read line
            String username = cnsl.readLine(
                    "Enter username : ");

            // Print username
            System.out.println(
                    "Nutzername : " + username);

            // Read password
            // into character array
            char[] password = cnsl.readPassword(
                    "Passwort : ");

            // Print password
            System.out.println(
                    "Password : " + password);


            if ((benutzerverwaltung.getBenutzer(username) != null)
                    && benutzerverwaltung.getBenutzer(username).checkPasswort(Arrays.toString(password))) {

                if (benutzerverwaltung.istAdmin(username)) {

                    System.out.print("Bitte geben sie den neuen Preis pro Minute an: ");
                    String preis_pro_minuteString = scanner.nextLine();
                    float preis_pro_minute = Float.parseFloat(preis_pro_minuteString.replaceAll(",", "\\."));
                    System.out.println("Der neue Preis pro Minute betraegt: " + preis_pro_minute + " Euro");
                    berechnung.setPreis_pro_minute(preis_pro_minute);


                    System.out.print("Bitte geben sie den neuen Preis pro Kilometer an: ");
                    String preis_pro_kmString = scanner.nextLine();
                    float preis_pro_km = Float.parseFloat(preis_pro_kmString.replaceAll(",", "\\."));
                    System.out.println("Der neue Preis pro Kilometer betraegt: " + preis_pro_km + " Euro");
                    berechnung.setPreis_pro_km(preis_pro_km);

                    System.out.print("Bitte geben sie den neuen Preis pro Entsprechung an: ");
                    String preis_pro_entsperrungString = scanner.nextLine();
                    float preis_pro_entsperrung = Float.parseFloat(preis_pro_entsperrungString.replaceAll(",", "\\."));
                    System.out.println("Der neue Preis pro Entsprechung betraegt: " + preis_pro_entsperrung + " Euro");
                    berechnung.setPreis_pro_entsperrung(preis_pro_entsperrung);

                } else {
                    System.out.println("Sie sind nicht berechtigt, diesen Vorgang durchzufuehren");
                    auswahl();
                }
            }
        } catch (AccountNotFoundException e) {
            System.out.println("Benutzer nicht gefunden und/oder Passwort falsch");
            errorHandling("preiseBearbeiten");
        } catch (Exception e) {
            errorHandling("preiseBearbeiten");
        }
    }

    private void zeitpreis() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Bitte geben sie die Dauer in Minuten an: ");
        try {
            String dauerString = scanner.nextLine();
            double dauer = Double.parseDouble(dauerString.replaceAll(",", "\\."));
            System.out.println("Die Dauer betraegt: " + dauer + " Minuten");
            System.out.println("Die Minuten werden automatisch aufgerundet");
            float preis = berechnung.berechnePreisProMinute((int) Math.ceil(dauer));
            System.out.println("Der Preis betraegt: " + preis + " Euro");

        } catch (Exception e) {
            errorHandling("min");
        }
    }

    private void errorHandling(String wahl) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Falsche Eingabe. Bitte erneut versuchen");
        System.out.println("Moechten Sie es noch einmal eingeben? [1] Ja [2] Nein");
        try {
            int tryAgain = Integer.parseInt(scanner.nextLine());

            if (tryAgain == 1) {
                if (Objects.equals(wahl, "km")) {
                    streckenpreis();
                } else if (Objects.equals(wahl, "min")) {
                    zeitpreis();
                } else if (Objects.equals(wahl, "preiseBearbeiten")) {
                    preiseBearbeiten();
                }
            } else {
                auswahl();
            }
        } catch (Exception e) {
            System.out.println("Falsche Eingabe. Bitte erneut versuchen");
            errorHandling(wahl);
        }
    }
}