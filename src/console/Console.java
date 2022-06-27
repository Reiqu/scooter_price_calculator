package console;

import error.AccountNotFoundException;
import logic.UserAdministration;
import logic.Calculation;

import java.util.Objects;
import java.util.Scanner;

public class Console {
    private final Calculation calculation;
    private final UserAdministration userAdministration;

    // Konstrukteur
    public Console() {
        this.userAdministration = new UserAdministration();
        this.calculation = new Calculation(0.5f, 1.0f);

        auswahl();
    }

    @SuppressWarnings("InfiniteRecursion")
    private void auswahl() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ScooTec Scooter Preis Rechner");
        System.out.println();
        System.out.println("Bitte geben sie ein, was sie machen wollen");
        System.out.println("[1] Streckenpreis berechnen");
        System.out.println("[2] Zeitpreis berechnen");
        System.out.println("[3] Preise anzeigen");
        System.out.println("[4] Preise bearbeiten");
        System.out.println("[5] Programm beenden");
        try {
            int wahl = Integer.parseInt(scanner.nextLine());
            if (wahl == 1) {
                streckenpreis();
            } else if (wahl == 2) {
                zeitpreis();
            } else if (wahl == 3) {
                preiseAnzeigen();
            } else if (wahl == 4) {
                preiseBearbeiten();
            } else if (wahl == 5) {
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


    // Streckenpreis berechnen
    private void streckenpreis() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Bitte geben sie die Strecke in km an: ");
        try {
            String streckeString = scanner.nextLine();
            float strecke = Float.parseFloat(streckeString.replaceAll(",", "\\."));
            System.out.println("Die Strecke betraegt: " + strecke + " km");
            float preis = calculation.calcPricePerKM(strecke);
            System.out.println("Der Preis betraegt: " + preis + " Euro");

        } catch (Exception e) {
            errorHandling("km");
        }
    }


    // Preise anzeigen
    private void preiseAnzeigen() {
        System.out.println("Preise: ");
        System.out.println("Preis pro Minute: " + calculation.getPrice_per_min() + " Euro");
        System.out.println("Preis pro Kilometer: " + calculation.getPrice_per_km() + " Euro");
        System.out.println("Preis pro Entsprechung: " + calculation.getPrice_per_unlock() + " Euro");
    }


    // Preise bearbeiten
    private void preiseBearbeiten() {
        Scanner scanner = new Scanner(System.in);
        try {

            System.out.println("Bitte authorisieren Sie sich");
            java.io.Console console = System.console();

            if (console == null) {
                System.out.println(
                        "No console available");
                return;
            }

            String username = console.readLine(
                    "Enter username : ");
            System.out.println(
                    "Nutzername : " + username);
            char[] password = console.readPassword(
                    "Passwort : ");

            if ((userAdministration.getUser(username) != null)
                    && userAdministration.getUser(username).checkPassword(new String(password))) {
                System.out.println("Benutzer erfolgreich authorisiert");
                if (userAdministration.isAdmin(username)) {

                    System.out.print("Bitte geben sie den neuen Preis pro Minute an: (leer lassen, falls nicht geaendert) ");
                    String preis_pro_minuteString = scanner.nextLine();
                    if (!preis_pro_minuteString.isEmpty()) {
                        float preis_pro_minute = Float.parseFloat(preis_pro_minuteString.replaceAll(",", "\\."));
                        calculation.setPrice_per_min(preis_pro_minute);
                        System.out.println("Der neue Preis pro Minute betraegt: " + preis_pro_minute + " Euro");
                    } else {
                        System.out.println("Der Preis pro Minute betraegt weiterhin: " + calculation.getPrice_per_min() + " Euro");
                    }


                    System.out.print("Bitte geben sie den neuen Preis pro Kilometer an:  (leer lassen, falls nicht geaendert)");
                    String preis_pro_kmString = scanner.nextLine();
                    if (!preis_pro_kmString.isEmpty()) {
                        float preis_pro_km = Float.parseFloat(preis_pro_kmString.replaceAll(",", "\\."));
                        calculation.setPrice_per_km(preis_pro_km);
                        System.out.println("Der neue Preis pro Kilometer betraegt: " + preis_pro_km + " Euro");
                    } else {
                        System.out.println("Der Preis pro Kilometer betraegt weiterhin: " + calculation.getPrice_per_km() + " Euro");
                    }

                    System.out.print("Bitte geben sie den neuen Preis pro Entsprechung an: (leer lassen, falls nicht geaendert)");
                    String preis_pro_entsperrungString = scanner.nextLine();
                    if (!preis_pro_entsperrungString.isEmpty()) {
                        float preis_pro_entsperrung = Float.parseFloat(preis_pro_entsperrungString.replaceAll(",", "\\."));
                        calculation.setPrice_per_unlock(preis_pro_entsperrung);
                        System.out.println("Der neue Preis pro Entsprechung betraegt: " + preis_pro_entsperrung + " Euro");
                    } else {
                        System.out.println("Der Preis pro Entsprechung betraegt weiterhin: " + calculation.getPrice_per_unlock() + " Euro");
                    }

                } else {
                    System.out.println("Sie sind nicht berechtigt, diesen Vorgang durchzufuehren");
                    auswahl();
                }
            } else {
                System.out.println("Benutzer nicht authorisiert");
                auswahl();
            }
        } catch (AccountNotFoundException e) {
            System.out.println("Benutzer nicht gefunden und/oder Passwort falsch");
            errorHandling("preiseBearbeiten");
        } catch (Exception e) {
            errorHandling("preiseBearbeiten");
        }
    }


    // Zeitpreis berechnen
    private void zeitpreis() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Bitte geben sie die Dauer in Minuten an: ");
        try {
            String dauerString = scanner.nextLine();
            double dauer = Double.parseDouble(dauerString.replaceAll(",", "\\."));
            System.out.println("Die Dauer betraegt: " + dauer + " Minuten");
            System.out.println("Die Minuten werden automatisch aufgerundet");
            float preis = calculation.calcPricePerMin((int) Math.ceil(dauer));
            System.out.println("Der Preis betraegt: " + preis + " Euro");

        } catch (Exception e) {
            errorHandling("min");
        }
    }


    // Fehlerbehandlung
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