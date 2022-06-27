package error;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException() {
        super("Benutzer nicht gefunden!");
    }
}
