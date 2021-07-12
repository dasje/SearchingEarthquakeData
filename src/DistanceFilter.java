public class DistanceFilter implements Filter {

    private Location loc;
    private double maxDist;
    private String name = "Distance";

    public DistanceFilter(Location location, double distance) {
        loc = location;
        maxDist = distance;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(loc) < maxDist;
    }

    @Override
    public String getName() {
        return name;
    }
}
