import java.util.*;

/**
 * Find N-largest quakes
 *
 * @author Duke Software/Learn to Program
 * @author Ben Sweeney/dasje
 * @version 1.0, November 2015
 */

public class LargestQuakes {
    public static void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(list);
        ArrayList<QuakeEntry> retList = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
            int ind = 0;
            QuakeEntry largest = copy.get(0);
            for (int j = 1; j < copy.size(); j++) {
                if (copy.get(j).getMagnitude() > largest.getMagnitude()) {
                    ind = j;
                    largest = copy.get(j);
                }
            }
            retList.add(copy.get(ind));
            copy.remove(ind);
        }
        int counter = 1;
        for (QuakeEntry qe: retList) {
            System.out.println(counter + " " + qe);
            counter += 1;
        }
        System.out.println("read data for "+list.size());
    }

    public static int indexOfLargest(ArrayList<QuakeEntry> data) {
        int ind = 0;
        QuakeEntry largest = data.get(ind);
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).getMagnitude() > largest.getMagnitude()) {
                ind = i;
                largest = data.get(i);
            }
        }
        return ind;
    }

    public static ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copyQuakes = new ArrayList<QuakeEntry>(quakeData);

        for (int i = 0; i < howMany; i++) {
            int j = 0;
            QuakeEntry current = copyQuakes.get(j);
            for (int k = 1; k < copyQuakes.size(); k++) {
                QuakeEntry q = copyQuakes.get(k);
                if (q.getMagnitude() > current.getMagnitude()) {
                    current = q;
                    j = k;
                }
            }
            ret.add(copyQuakes.get(j));
            copyQuakes.remove(j);
        }

        return ret;
    }
}
