package stringmatching;

import java.util.ArrayList;
import java.util.List;

public class KMPSearch {
    private long operationCount = 0;

    public List<Integer> search(String text, String pattern) {
        operationCount = 0;
        List<Integer> indices = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        if (m == 0 || m > n) {
            return indices;
        }

        int[] lps = computeLPS(pattern);
        int i = 0;
        int j = 0;

        while (i < n) {
            operationCount++;
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                indices.add(i - j);
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return indices;
    }

    private int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < m) {
            operationCount++;
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public long getOperationCount() {
        return operationCount;
    }
}