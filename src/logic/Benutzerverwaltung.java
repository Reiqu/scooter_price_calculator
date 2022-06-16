package logic;

import error.AccountNotFoundException;
import models.Benutzer;

public class Benutzerverwaltung {
    private Benutzer[] benutzer = {
            new Benutzer("admin1", "admin 1", true, "admin1"),
            new Benutzer("admin2", "admin 2", true, "admin2"),
            new Benutzer("user1", "user 1", false, "user1"),
            new Benutzer("user2", "user 2", false, "user2"),
    };

    private Benutzer aktuellerBenutzer;

    public Benutzer[] getBenutzer() {
        return benutzer;
    }

    public Benutzer getBenutzer(String username) throws AccountNotFoundException {
        for (Benutzer b : benutzer) {
            if (b.getUsername().equals(username)) {
                return b;
            }
        }
        throw new AccountNotFoundException("Benutzer nicht gefunden!");
    }


    /**
     * Prüft, ob der Benutzer ein Admin ist.
     * @param username
     * @return true, falls der Benutzer ein Admin ist, false sonst.
     */
    public Boolean istAdmin(String username) throws AccountNotFoundException {
        Benutzer b = getBenutzer(username);
        if (b != null) {
            return b.getIstAdmin();
        }
        return false;
    }

    /**
     * Prüft, ob der aktuelle Benutzer ein Admin ist.
     * @return true, falls der aktuelle Benutzer ein Admin ist, false sonst.
     */
    public Boolean istAdmin() {
        try {
            return aktuellerBenutzer.getIstAdmin();
        } catch (NullPointerException e) {
            return false;
        }
    }



    @SuppressWarnings("ConstantConditions")
    public void addBenutzer(Benutzer benutzer) {
        this.benutzer[this.benutzer.length] = benutzer;
    }
    public Benutzerverwaltung() {
    }

    public void setAktuellerBenutzer(Benutzer aktuellerBenutzer) {
        this.aktuellerBenutzer = aktuellerBenutzer;
    }

    public Benutzer getAktuellerBenutzer() {
        return aktuellerBenutzer;
    }

    public Boolean istAngemeldet() {
        return aktuellerBenutzer != null;
    }
}
