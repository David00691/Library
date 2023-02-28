package pl.java.dawid.library.core;

import org.apache.commons.codec.digest.DigestUtils;
import pl.java.dawid.library.database.UserDB;
import pl.java.dawid.library.model.User;

public class Authenticator {
    final UserDB userDB = UserDB.getInstance();
    private User loggedUser = null;
    private final String seed = "OK4wkjJ15XD@T*41pO9M21t^rLhlt#&9srznHWyo";
    private static final Authenticator instance = new Authenticator();

    private Authenticator() {

    }
    public boolean authenticate(User user) {
        User userFromDB = this.userDB.findByLogin(user.getLogin());
        if(userFromDB != null &&
                userFromDB.getPassword().equals(
                        DigestUtils.md5Hex(user.getPassword() + this.seed))) {
            this.loggedUser = userFromDB;
            return true;
        }
    return false;
    }


    public static Authenticator getInstance() {
        return instance;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public String getSeed() {
        return seed;
    }
}
