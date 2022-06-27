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
