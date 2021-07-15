import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DifferentSortersTest {

    @Test
    void sortWithCompareToTest() {
        DifferentSorters.sortWithCompareTo();
    }

    @Test
    void sortByTitleAndDepthTest() {
        DifferentSorters.sortByTitleAndDepth();
    }

    @Test
    void sortByLastWordInTitleAndThenMagnitudeTest() {
        DifferentSorters.sortByLastWordInTitleAndThenMagnitude();
    }
}