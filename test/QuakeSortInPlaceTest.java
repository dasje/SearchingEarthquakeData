import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuakeSortInPlaceTest {

    @Test
    void testSortTest() {
        QuakeSortInPlace.testSort();
    }

    @Test
    void getLargestDepthTest() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        /*for (QuakeEntry qe: list) {
            System.out.println(qe);
        }*/

        System.out.println("read data for "+list.size()+" quakes");
        int x = QuakeSortInPlace.getLargestDepth(list, 0);
        System.out.println(x);
        System.out.println(list.get(x));
        System.out.println();
        int y = QuakeSortInPlace.getLargestDepth(list, 11);
        System.out.println(y);
        System.out.println(list.get(y));
    }
}