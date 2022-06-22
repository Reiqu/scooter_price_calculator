package models;

public class User {
    private final String username;
    private final String displayName;
    private Boolean isAdmin;
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

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * Hashes the given password with the given salt.
     *
     * @param password
     * @param password_salt
     * @return hash of the password
     */
    public String setPasswort_hash(String password, String password_salt) {
        // TODO: Implementierung einer Hashfunktion für das Passwort
        String generatedPasswordHash = null;
/*
        try {
            // Create MessageDigest instance for SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // Add salt to MessageDigest
            md.update(password_salt.getBytes(StandardCharsets.UTF_8));

            // Get the hash's bytes
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

            // Transform byte array into String
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            // Return hashed password
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
 */
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
}
