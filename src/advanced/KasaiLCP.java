package advanced;

public class KasaiLCP {
    private long operationCount = 0;
    public int[] computeLCP(String text, int[] sa) {
        operationCount = 0;
        int n = text.length();
        int[] lcp = new int[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            operationCount++;
            rank[sa[i]] = i;
        }

        int h = 0;
        for (int i = 0; i < n; i++) {
            operationCount++;
            if (rank[i] > 0) {
                int k = sa[rank[i] - 1];

                while (i + h < n && k + h < n && text.charAt(i + h) == text.charAt(k + h)) {
                    operationCount++;
                    h++;
                }

                lcp[rank[i]] = h;

                if (h > 0) {
                    h--;
                }
            } else {
                lcp[rank[i]] = 0;
            }
        }
        return lcp;
    }

    public long getOperationCount() {
        return operationCount;
    }
}