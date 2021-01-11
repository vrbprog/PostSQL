import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LoginDB {
    private String database;
    private String user;
    private String password;
    private String host;
    private String port;
    private String urlDB;

    public LoginDB() {
        Properties login = new Properties();
        try (FileReader in = new FileReader("login.properties")) {
            login.load(in);
            user = login.getProperty("username"); // My account in PostgreSQL Server
            password = login.getProperty("password"); // My password in PostgreSQL Server
            database = login.getProperty("database"); // My database
            host = login.getProperty("host"); // default host
            port = login.getProperty("port"); // default PostgreSQL port
            urlDB = "jdbc:postgresql://" + host + ":" + port + "/" + database;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUrlDB() {
        return urlDB;
    }
}
