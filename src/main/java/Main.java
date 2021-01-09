import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String HOST = "localhost"; // default host
        String PORT = "5432"; // default PostgreSQL port
        String DATABASE = "testdb"; // My database
        String USER = "postgres"; // My account in PostgreSQL Server
        String PASSWORD = "Dbrnjhbz2004"; // My password in PostgreSQL Server

        String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE;
        String sqlQuery = "SELECT * FROM book";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("title"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
