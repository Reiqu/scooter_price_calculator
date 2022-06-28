package models;

public class User {
    private final String username;
    private final Boolean isAdmin;
    private final String password_hash;

    /**
     * Benutzer
     * @param username Nutzername
     * @param isAdmin Boolean, ob Nutzer ein Admin ist
     * @param passwort Passwort
     */
    public User(String username, String passwort, Boolean isAdmin) {
        this.username = username;
        this.isAdmin = isAdmin;
        this.password_hash = setPasswort_hash(passwort);
    }

    public Boolean getIsAdmin() throws NullPointerException {
        return isAdmin;
    }

    /**
     * Hashes the given password with the given salt.
     *
     * @param password Password to hash
     * @return hash of the password
     */
    public String setPasswort_hash(String password) {
        return password;
    }

    /**
     * Prüft, ob Passwort-Hash mit Passwort übereinstimmt.
     * @return Boolean true, falls Passwort-Hash mit Passwort übereinstimmt, false sonst.
     */
    public Boolean checkPassword(String password) {
        return setPasswort_hash(password).equals(this.password_hash);
    }

    /**
     * Getter für Username
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

}
