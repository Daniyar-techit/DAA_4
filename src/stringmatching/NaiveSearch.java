package stringmatching;

import java.util.ArrayList;
import java.util.List;

public class NaiveSearch {
    private long operationCount = 0;

    public List<Integer> search(String text, String pattern) {
        operationCount = 0;
        List<Integer> indices = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        if (m == 0 || m > n) {
            return indices;
        }

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                operationCount++;
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                indices.add(i);
            }
        }
        return indices;
    }

    public long getOperationCount() {
        return operationCount;
    }
}