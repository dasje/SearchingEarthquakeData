import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClosestQuakesTest {

    @Test
    void getClosestTest() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> x  = parser.read(source);
        Location jakarta  = new Location(-6.211,106.845);
        ArrayList<QuakeEntry> qe = ClosestQuakes.getClosest(x, jakarta,4);
        System.out.println("read data for " + x.size() + " quakes");
        for (QuakeEntry q: qe) {
            System.out.println(q);
        }
        System.out.println("number found: " + qe.size());
    }
}