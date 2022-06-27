package models;

public class User {
    private final String username;
    private final String displayName;
    private final Boolean isAdmin;
    private final String password_hash;

    final private String password_salt = "scootec";

    /**
     * Benutzer
     * @param username Nutzername
     * @param displayName Anzeigename
     * @param isAdmin Boolean, ob Nutzer ein Admin ist
     * @param passwort Passwort
     */
    public User(String username, String displayName, Boolean isAdmin, String passwort) {
        this.username = username;
        this.displayName = displayName;
        this.isAdmin = isAdmin;
        this.password_hash = setPasswort_hash(passwort, this.password_salt);
    }

    public Boolean getIsAdmin() throws NullPointerException {
        return isAdmin;
    }

    /**
     * Hashes the given password with the given salt.
     *
     * @param password Password to hash
     * @param password_salt Salt to hash with
     * @return hash of the password
     */
    public String setPasswort_hash(String password, String password_salt) {
        // NICETOHAVE: Implementierung einer Hashfunktion für das Passwort
        String generatedPasswordHash = null;
        return password;
    }

    /**
     * Prüft, ob Passwort-Hash mit Passwort übereinstimmt.
     * @return Boolean true, falls Passwort-Hash mit Passwort übereinstimmt, false sonst.
     */
    public Boolean checkPassword(String password) {
        return setPasswort_hash(password, this.password_salt).equals(this.password_hash);
    }

    /**
     * Getter für Username
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    public String getDisplayName() {
        return displayName;
    }
}
