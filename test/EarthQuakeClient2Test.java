import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EarthQuakeClient2Test {

    @Test
    void quakesWithFilterTest() {
        EarthQuakeClient2.quakesWithFilter();
    }

    @Test
    void matchAllFilterTest() {
        EarthQuakeClient2.matchAllFilter();
    }
}