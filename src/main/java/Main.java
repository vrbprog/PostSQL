import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numTypeOfPlane;
        int numOfPass;
        int numOfPilots;
        int numOfPlanes;
        int totalPassOfAirPort = 0;
        String flight = "0";

        List<PlaneInfo> planes = new ArrayList<>();
        List<String> currentCrew;

        LoginDB pg = new LoginDB();
        String url = pg.getUrlDB();

        try (Connection connection = DriverManager.getConnection(
                url, pg.getUser(), pg.getPassword());
             Statement statement = connection.createStatement();
        ) {

            numTypeOfPlane = AirPortQueryRequest.getNumTypeOfPlane(statement);

            for (int i = 1; i <= numTypeOfPlane; i++) {
                numOfPass = AirPortQueryRequest.getNumPassOfPlane(statement, String.valueOf(i));
                numOfPlanes = AirPortQueryRequest.getNumModelsOfPlane(statement, String.valueOf(i));
                numOfPilots = AirPortQueryRequest.getNumPilotsOfPlane(statement, String.valueOf(i));
                planes.add(new PlaneInfo(String.valueOf(i), String.valueOf(numOfPilots), numOfPlanes, numOfPass));
            }
            for (PlaneInfo p : planes) {
                System.out.println(p);
            }

            for (int i = 1; i <= numTypeOfPlane; i++) {
                for (int j = 1; j <= planes.get(i - 1).getNumPlanes(); j++) {
                    currentCrew = AirPortQueryRequest.getNextCrew(
                            statement, planes.get(i - 1).getPlaneID(),
                            flight, planes.get(i - 1).getNumPilots());

                    if (currentCrew.size() < Integer.parseInt(planes.get(i - 1).getNumPilots())) {
                        continue;
                    } else {
                        totalPassOfAirPort += planes.get(i - 1).getNumOfPass();
                        flight = flight + "," + currentCrew.get(0) + "," + currentCrew.get(1);
                        //System.out.println(currentCrew);
                    }
                }
            }
            System.out.println("Maximum number of passengers of airport is: " + totalPassOfAirPort);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
