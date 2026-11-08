import dp.EditDistance;
import dp.LCS;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DPTest {

    @Test
    public void testLCSIdentical() {
        LCS lcs = new LCS();
        assertEquals(5, lcs.findLength("ABCDE", "ABCDE"));
    }

    @Test
    public void testLCSDifferent() {
        LCS lcs = new LCS();
        assertEquals(0, lcs.findLength("ABC", "XYZ"));
    }

    @Test
    public void testLCSEmpty() {
        LCS lcs = new LCS();
        assertEquals(0, lcs.findLength("", "ABC"));
        assertEquals(0, lcs.findLength("ABC", ""));
    }

    @Test
    public void testEditDistanceIdentical() {
        EditDistance ed = new EditDistance();
        assertEquals(0, ed.calculate("kitten", "kitten"));
    }

    @Test
    public void testEditDistanceEmpty() {
        EditDistance ed = new EditDistance();
        assertEquals(3, ed.calculate("", "abc"));
        assertEquals(3, ed.calculate("abc", ""));
    }

    @Test
    public void testEditDistanceTransformations() {
        EditDistance ed = new EditDistance();
        // "kitten" -> "sitting": k->s (subst), e->i (subst), +g (insert) = 3 operations
        assertEquals(3, ed.calculate("kitten", "sitting"));
    }
}