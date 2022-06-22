package logic;

import error.AccountNotFoundException;
import models.User;

public class UserAdministration {
    private final User[] users = {
            new User("admin1", "admin 1", true, "admin1"),
            new User("admin2", "admin 2", true, "admin2"),
            new User("user1", "user 1", false, "user1"),
            new User("user2", "user 2", false, "user2"),
    };

    private User current_User;

    public User getUser(String username) throws AccountNotFoundException {
        for (User b : users) {
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
    public Boolean isAdmin(String username) throws AccountNotFoundException {
        User b = getUser(username);
        if (b != null) {
            return b.getIsAdmin();
        }
        return false;
    }

    /**
     * Prüft, ob der aktuelle Benutzer ein Admin ist.
     * @return true, falls der aktuelle Benutzer ein Admin ist, false sonst.
     */
    public Boolean isAdmin() {
        try {
            return current_User.getIsAdmin();
        } catch (NullPointerException e) {
            return false;
        }
    }
    public UserAdministration() {
    }

    public void setCurrentUser(User current_User) {
        this.current_User = current_User;
    }

    public User getCurrentUser() {
        return current_User;
    }

    public Boolean isLoggedIn() {
        return current_User != null;
    }
}
