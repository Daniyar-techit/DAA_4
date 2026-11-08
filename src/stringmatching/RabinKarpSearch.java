package stringmatching;

import java.util.ArrayList;
import java.util.List;

public class RabinKarpSearch {
    private long operationCount = 0;
    private static final int PRIME = 101;
    private static final int ALPHABET_SIZE = 256;

    public List<Integer> search(String text, String pattern) {
        operationCount = 0;
        List<Integer> indices = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        if (m == 0 || m > n) {
            return indices;
        }

        long h = 1;
        for (int i = 0; i < m - 1; i++) {
            h = (h * ALPHABET_SIZE) % PRIME;
        }

        long patternHash = 0;
        long textHash = 0;

        for (int i = 0; i < m; i++) {
            operationCount++;
            patternHash = (ALPHABET_SIZE * patternHash + pattern.charAt(i)) % PRIME;
            textHash = (ALPHABET_SIZE * textHash + text.charAt(i)) % PRIME;
        }

        for (int i = 0; i <= n - m; i++) {
            operationCount++;
            if (patternHash == textHash) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    operationCount++;
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    indices.add(i);
                }
            }

            if (i < n - m) {
                textHash = (ALPHABET_SIZE * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % PRIME;
                if (textHash < 0) {
                    textHash = (textHash + PRIME);
                }
            }
        }
        return indices;
    }

    public long getOperationCount() {
        return operationCount;
    }
}