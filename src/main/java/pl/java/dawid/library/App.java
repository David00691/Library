package pl.java.dawid.library;

import pl.java.dawid.library.core.Core;
import pl.java.dawid.library.database.Provider;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        Provider conn = Provider.getInstance();
        Connection connection = conn.connect();
        Core.getInstance().start();
    }
}
