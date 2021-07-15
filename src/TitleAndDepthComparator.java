/**
 * Write a description of class MagnitudeComparator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        int comparison = qe1.getInfo().compareTo(qe2.getInfo());
        if (comparison == 0) {
            double depthDiff = qe1.getDepth() - qe2.getDepth();
            if (depthDiff < 0) return -1;
            if (depthDiff > 0) return 1;
            return 0;
        }
        return qe1.getInfo().compareTo(qe2.getInfo());
    }
}
