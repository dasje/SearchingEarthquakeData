import edu.duke.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class EarthQuakeClientTest {

    @Test
    void filterByMagnitudeTest() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> x  = parser.read(source);
        ArrayList<QuakeEntry> y;
        y = EarthQuakeClient.filterByMagnitude(x, 5.0);
        for (QuakeEntry qe: y) {
            System.out.println(qe);
        }

    }

    @Test
    void bigQuakesTest() {
        EarthQuakeClient.bigQuakes();
    }

    @Test
    void closeToMeTest() {
        EarthQuakeClient.closeToMe();
    }

    @Test
    void quakesOfDepthTest() {
        EarthQuakeClient.quakesOfDepth();
    }

    @Test
    void quakesByPhraseTest() {
        EarthQuakeClient.quakesByPhrase();
    }
}