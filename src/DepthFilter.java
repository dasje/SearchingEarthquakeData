public class DepthFilter implements Filter {
    private double minDep;
    private double maxDep;
    private String name = "Depth";

    public DepthFilter(double min, double max){
        minDep = min;
        maxDep = max;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= minDep && qe.getDepth() <= maxDep;
    }

    @Override
    public String getName() {
        return name;
    }
}

