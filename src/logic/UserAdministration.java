package logic;

import error.AccountNotFoundException;
import models.User;

public class UserAdministration {
    private final User[] users = {
            new User("admin1", "admin1", true),
            new User("admin2", "admin2", true),
            new User("user1", "user1", false),
            new User("user2", "user2", false),
    };

    /**
     * @param username Benutzername
     * @return User
     * @throws AccountNotFoundException Wenn Benutzer nicht gefunden wird
     */
    public User getUser(String username) throws AccountNotFoundException {
        for (User b : users) {
            if (b.getUsername().equals(username)) {
                return b;
            }
        }
        throw new AccountNotFoundException();
    }


    /**
     * Prüft, ob der Benutzer ein Admin ist.
     * @param username Benutzername
     * @return true, falls der Benutzer ein Admin ist, sonst false.
     */
    public Boolean isAdmin(String username) throws AccountNotFoundException {
        User b = getUser(username);
        if (b != null) {
            return b.getIsAdmin();
        }
        return false;
    }

    public UserAdministration() {
    }

}
