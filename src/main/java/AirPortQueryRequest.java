import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirPortQueryRequest {

    private static final  String QUERY_GET_NUM_TYPES = "SELECT COUNT(*) as count_model FROM model WHERE id > 0";
    private static final String QUERY_GET_NUM_PASS = "SELECT num_seats FROM model WHERE id = ";
    private static final String QUERY_GET_NUM_PILOTS = "SELECT num_pilots FROM model WHERE id = ";
    private static final String QUERY_GET_NUM_MODEL = "SELECT COUNT(*) as count_model_1 FROM airpark WHERE model_id = ";
    private static final String QUERY_GET_NEXT_CREW1 = "SELECT d.pilot_id,d.model_id,p.driving FROM pilot_driving d "
            + "LEFT JOIN pilot p ON p.id=d.pilot_id WHERE d.model_id = ";
    private static final String QUERY_GET_NEXT_CREW2 = " AND d.pilot_id NOT IN(";
    private static final String QUERY_GET_NEXT_CREW3 = ") ORDER BY driving LIMIT ";

    public static int getNumTypeOfPlane(Statement s) throws SQLException {
        ResultSet resultSet = s.executeQuery(QUERY_GET_NUM_TYPES);
        if (resultSet.next()) {
            return Integer.parseInt(resultSet.getString("count_model"));
        } else {
            return 0;
        }
    }

    private static int queryGetParam(Statement s, String num, String query, String param) throws SQLException {
        ResultSet resultSet = s.executeQuery(query + num);
        if (resultSet.next()) {
            return Integer.parseInt(resultSet.getString(param));
        } else {
            return 0;
        }
    }

    public static int getNumPassOfPlane(Statement s, String num) throws SQLException {
        return queryGetParam(s, num, QUERY_GET_NUM_PASS, "num_seats");
    }

    public static int getNumPilotsOfPlane(Statement s, String num) throws SQLException {
        return queryGetParam(s, num, QUERY_GET_NUM_PILOTS, "num_pilots");
    }

    public static int getNumModelsOfPlane(Statement s, String num) throws SQLException {
        return queryGetParam(s, num, QUERY_GET_NUM_MODEL, "count_model_1");
    }

    public static List<String> getNextCrew(Statement s, String num, String flight, String limit) throws SQLException {
        List<String> list = new ArrayList<>();
        ResultSet resultSet = s.executeQuery(QUERY_GET_NEXT_CREW1 + num
                + QUERY_GET_NEXT_CREW2 + flight + QUERY_GET_NEXT_CREW3 + limit);
        while (resultSet.next()) {
            list.add(resultSet.getString("pilot_id"));
        }
        return list;
    }

}
