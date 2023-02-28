package pl.java.dawid.library.database;

import pl.java.dawid.library.model.User;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDB {

    private static final Provider conn = Provider.getInstance();
    private static final Connection connection = conn.connect();
    private static final UserDB instance = new UserDB();
    private final List <User> users;

    private UserDB() {
       users=getAllUsers();
    }

    public User findByLogin(String login) {

        for(User user : this.users) {
            if(user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }
    public List<User> getAllUsers(){
        List<User> result = new ArrayList<>();
        try {
            String sql = "SELECT is_admin, id, login, password FROM users";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                result.add(new User(
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getInt("id"),
                        rs.getInt("is_admin") == 1 ? User.Role.ADMIN : User.Role.USER));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static UserDB getInstance() {
        return instance;
    }
}
