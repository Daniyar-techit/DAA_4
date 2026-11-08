import advanced.KasaiLCP;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KasaiTest {

    @Test
    public void testKasaiOnBanana() {
        KasaiLCP kasai = new KasaiLCP();
        String text = "banana";
        int[] sa = {5, 3, 1, 0, 4, 2};

        int[] expectedLcp = {0, 1, 3, 0, 0, 2};

        int[] actualLcp = kasai.computeLCP(text, sa);

        assertArrayEquals(expectedLcp, actualLcp, "LCP array for 'banana' does not match expected manually verified values.");
    }
}