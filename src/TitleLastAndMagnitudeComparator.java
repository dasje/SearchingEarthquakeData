/**
 * Write a description of class MagnitudeComparator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String qe1LastWord = qe1.getInfo().substring(qe1.getInfo().lastIndexOf(" ") + 1);
        String qe2LastWord = qe2.getInfo().substring(qe2.getInfo().lastIndexOf(" ") + 1);
        int titleComp = qe1LastWord.compareToIgnoreCase(qe2LastWord);
        System.out.println(qe1LastWord + " " + qe2LastWord);
        System.out.println(titleComp);
        if (titleComp == 0) {
            double depthMag = qe1.getMagnitude() - qe2.getMagnitude();
            if (depthMag < 0) return -1;
            if (depthMag > 0) return 1;
            return 0;
        }
        if (titleComp < 0) return -1;
        if (titleComp > 0) return  1;
        return 0;
    }
}
