import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirPortQueryRequest {

    private final static String sqlQueryGetNum = "SELECT COUNT(*) as count_model FROM model WHERE id > 0";
    private final static String sqlQueryGetNumPass = "SELECT num_seats FROM model WHERE id = ";
    private final static String sqlQueryGetNumPilots = "SELECT num_pilots FROM model WHERE id = ";
    private final static String sqlQueryGetNumModels = "SELECT COUNT(*) as count_model_1 FROM airpark WHERE model_id = ";
    private final static String sqlQueryGetNextCrew1 = "SELECT d.pilot_id,d.model_id,p.driving FROM pilot_driving d " +
            "LEFT JOIN pilot p ON p.id=d.pilot_id WHERE d.model_id = ";
    private final static String sqlQueryGetNextCrew2 = " AND d.pilot_id NOT IN(";
    private final static String sqlQueryGetNextCrew3 = ") ORDER BY driving LIMIT ";

    public static int getNumTypeOfPlane(Statement s) throws SQLException {
        ResultSet resultSet = s.executeQuery(sqlQueryGetNum);
        if (resultSet.next())
            return Integer.parseInt(resultSet.getString("count_model"));
        else return 0;
    }

    private static int queryGetParam(Statement s, String num, String query, String param) throws SQLException {
        ResultSet resultSet = s.executeQuery(query + num);
        if (resultSet.next())
            return Integer.parseInt(resultSet.getString(param));
        else return 0;
    }

    public static int getNumPassOfPlane(Statement s, String num) throws SQLException {
        return queryGetParam(s, num, sqlQueryGetNumPass, "num_seats");
    }

    public static int getNumPilotsOfPlane(Statement s, String num) throws SQLException {
        return queryGetParam(s, num, sqlQueryGetNumPilots, "num_pilots");
    }

    public static int getNumModelsOfPlane(Statement s, String num) throws SQLException {
        return queryGetParam(s, num, sqlQueryGetNumModels, "count_model_1");
    }

    public static List<String> getNextCrew(Statement s, String num, String flight, String limit) throws SQLException {
        List<String> list = new ArrayList<>();
        ResultSet resultSet = s.executeQuery(sqlQueryGetNextCrew1 + num +
                sqlQueryGetNextCrew2 + flight + sqlQueryGetNextCrew3 + limit);
        while (resultSet.next()) {
            list.add(resultSet.getString("pilot_id"));
        }
        return list;
    }

}
