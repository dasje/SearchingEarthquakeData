public class MagnitudeFilter implements Filter {

    private double minMag;
    private double maxMag;
    private String name = "Magnitude";

    public MagnitudeFilter(double min, double max) {
        minMag = min;
        maxMag = max;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag;
    }

    @Override
    public String getName() {
        return name;
    }
}
