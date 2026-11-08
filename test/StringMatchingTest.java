import org.junit.jupiter.api.Test;
import stringmatching.KMPSearch;
import stringmatching.NaiveSearch;
import stringmatching.RabinKarpSearch;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringMatchingTest {

    private final NaiveSearch naive = new NaiveSearch();
    private final KMPSearch kmp = new KMPSearch();
    private final RabinKarpSearch rk = new RabinKarpSearch();

    private void assertAllMatch(String text, String pattern) {
        List<Integer> resNaive = naive.search(text, pattern);
        List<Integer> resKmp = kmp.search(text, pattern);
        List<Integer> resRk = rk.search(text, pattern);

        assertEquals(resNaive, resKmp, "KMP mismatch for text: " + text + ", pattern: " + pattern);
        assertEquals(resNaive, resRk, "Rabin-Karp mismatch for text: " + text + ", pattern: " + pattern);
    }

    @Test
    public void testPositions() {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        List<Integer> expected = List.of(10);
        assertEquals(expected, naive.search(text, pattern));
        assertAllMatch(text, pattern);
    }

    @Test
    public void testMultipleOccurrences() {
        String text = "AABAACAADAABAABA";
        String pattern = "AABA";
        assertAllMatch(text, pattern);
    }

    @Test
    public void testAtBeginningAndEnd() {
        String text = "TESTASTRINGTEST";
        assertAllMatch(text, "TEST");
    }

    @Test
    public void testAbsentPattern() {
        String text = "HELLO WORLD";
        assertAllMatch(text, "XYZ");
    }

    @Test
    public void testPatternLongerThanText() {
        String text = "SHORT";
        assertAllMatch(text, "MUCHLONGERPATTERN");
    }

    @Test
    public void testEmptyPattern() {
        String text = "ANYTEXT";
        assertAllMatch(text, "");
    }
}