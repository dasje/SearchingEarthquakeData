import java.util.ArrayList;

public class MatchAllFilter implements Filter {

    private ArrayList<Filter> filters;

    public MatchAllFilter() {
        filters = new ArrayList<>();
    }

    public void addFilter(Filter toAdd) {
        filters.add(toAdd);
    }

    public boolean satisfies(QuakeEntry qe) {
        for (Filter filter: filters) {
            if (! filter.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getName() {
        String retString = "";
        for (Filter filter: filters) {
            retString = retString + filter.getName() + " ";
        }
        return retString;
    }
}
