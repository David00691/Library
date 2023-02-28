package pl.java.david.library;

        import org.junit.jupiter.api.*;
        import pl.java.dawid.library.database.UserDB;
        import pl.java.dawid.library.model.User;

        import static org.junit.jupiter.api.Assertions.assertThrows;

public class Usertest {

    UserDB userDB = UserDB.getInstance();

    @Test
    public void succesFinduser() {
        String login = "admin";
        User expectedResult = new User(
                "admin",
                "eb0468abcd9f88e9844fd140fbb6acff",
                1,
                User.Role.ADMIN
        );
        User actual = userDB.findByLogin(login);
        Assertions.assertAll(
                "Assertions of 'admin' user",
                () -> Assertions.assertEquals(expectedResult.getid(), actual.getid() ),
                () -> Assertions.assertEquals(expectedResult.getLogin(), actual.getLogin()),
                () -> Assertions.assertEquals(expectedResult.getPassword(), actual.getPassword()),
                () -> Assertions.assertEquals(expectedResult.getRole(), actual.getRole()));
    }
    @Test
    public void succesFindusernotadmin() {
        String login = "Janusz";
        User expectedResult = new User(
                "Janusz",
                "6fff9bb96e12805ea3ccb8ec27e8206f",
                2,
                User.Role.USER
        );
        User actual = userDB.findByLogin(login);
        Assertions.assertAll(
                "Assertions of 'not admin' user",
                () -> Assertions.assertEquals(expectedResult.getid(), actual.getid() ),
                () -> Assertions.assertEquals(expectedResult.getLogin(), actual.getLogin()),
                () -> Assertions.assertEquals(expectedResult.getPassword(), actual.getPassword()),
                () -> Assertions.assertEquals(expectedResult.getRole(), actual.getRole()));
    }
}