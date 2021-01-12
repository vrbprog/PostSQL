public class PlaneInfo {
    private String planeID;
    private String numPilots;
    private int numPlanes;
    private int numOfPass;

    public PlaneInfo(String planeID, String numPilots, int numPlanes, int numOfPass) {
        this.planeID = planeID;
        this.numPilots = numPilots;
        this.numPlanes = numPlanes;
        this.numOfPass = numOfPass;
    }

    public String getPlaneID() {
        return planeID;
    }

    public String getNumPilots() {
        return numPilots;
    }

    public int getNumPlanes() {
        return numPlanes;
    }

    public int getNumOfPass() {
        return numOfPass;
    }

    @Override
    public String toString() {
        return "PlaneInfo{" +
                "planeID='" + planeID + '\'' +
                ", numPilots='" + numPilots + '\'' +
                ", countOfPlanes=" + numPlanes +
                ", numOfPass=" + numOfPass +
                '}';
    }
}
