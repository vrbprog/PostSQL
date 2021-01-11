import java.sql.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LoginDB pg = new LoginDB();

        String url = pg.getUrlDB();
        String sqlQuery = "SELECT pd.fk_pilot_id, a.model_id, a.capacity, a.num_pilots " +
                "FROM pilot_driving pd LEFT JOIN model_airbus a ON pd.fk_model_id=a.model_id";

        try(Connection connection = DriverManager.getConnection(
                url, pg.getUser(), pg.getPassword());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("fk_pilot_id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
