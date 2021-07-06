import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LargestQuakesTest {

    @Test
    void indexOfLargestTest() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        int i = LargestQuakes.indexOfLargest(list);
        System.out.println("The largest magnitude quake can be found at index " + i + ". And the magnitude was "
                + list.get(i).getMagnitude());
    }

    @Test
    void getLargestTest() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> i = LargestQuakes.getLargest(list, 5);
        for (QuakeEntry qe: i) {
            System.out.println(qe);
        }
        System.out.println(list.size() + " quakes processed");
        System.out.println(i.size() + " top quakes");
    }
}