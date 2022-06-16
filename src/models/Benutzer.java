package models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Benutzer {
    private final String username;
    private String displayName;
    private Boolean istAdmin;
    private String passwort_hash;

    final private String passwort_salt = "scootec";

    /**
     * Benutzer
     * @param username Nutzername
     * @param displayName Anzeigename
     * @param istAdmin Boolean, ob Nutzer ein Admin ist
     * @param passwort Passwort
     */
    public Benutzer(String username, String displayName, Boolean istAdmin, String passwort) {
        this.username = username;
        this.displayName = displayName;
        this.istAdmin = istAdmin;
        this.passwort_hash = setPasswort_hash(passwort, this.passwort_salt);
    }

    public Boolean getIstAdmin() throws NullPointerException {
        return istAdmin;
    }

    public void setIstAdmin(Boolean istAdmin) {
        this.istAdmin = istAdmin;
    }

    /**
     * Hashes the given password with the given salt.
     *
     * @param passwort
     * @param passwort_salt
     * @return hash of the password
     */
    public String setPasswort_hash(String passwort, String passwort_salt) {
        // TODO: Implementierung einer Hashfunktion für das Passwort
        String generatedPasswordHash = null;
/*
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // Add salt to MessageDigest
            md.update(passwort_salt.getBytes(StandardCharsets.UTF_8));

            // Get the hash's bytes
            byte[] bytes = md.digest(passwort.getBytes(StandardCharsets.UTF_8));

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
        return passwort;
    }

    /**
     * Prüft, ob Passwort-Hash mit Passwort übereinstimmt.
     * @return Boolean true, falls Passwort-Hash mit Passwort übereinstimmt, false sonst.
     */
    public Boolean checkPasswort(String passwort) {
        return setPasswort_hash(passwort, this.passwort_salt).equals(this.passwort_hash);
    }

    /**
     * Getter für Username
     * @return username
     */
    public String getUsername() {
        return this.username;
    }
}
