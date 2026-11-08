package main;

import dp.EditDistance;
import dp.LCS;
import stringmatching.KMPSearch;
import stringmatching.NaiveSearch;
import stringmatching.RabinKarpSearch;
import advanced.KasaiLCP;
import util.BenchmarkResult;
import util.RandomDataGenerator;

import java.util.ArrayList;
import java.util.List;

public class ExperimentRunner {
    private static final long SEED = 42L;
    private static final int RUNS = 5;

    public static void main(String[] args) {
        System.out.println("=== STARTING EXPERIMENTAL EVALUATION ===\n");

        List<BenchmarkResult> results = new ArrayList<>();
        runDPExperiments(results);
        runStringMatchingExperiments(results);
        runKasaiExperiments(results);

        System.out.println("\n=== SUMMARY RESULTS TABLE ===");
        System.out.println(String.format("%-22s | %-10s | %-15s | %-15s", "Algorithm", "Input Size", "Avg Time (ms)", "Operation Count"));
        System.out.println("----------------------------------------------------------------------");
        for (BenchmarkResult res : results) {
            System.out.println(String.format("%-22s | %-10d | %-15.4f | %-15d",
                    res.getAlgorithmName(), res.getInputSize(), res.getAverageTimeMs(), res.getOperationCount()));
        }
    }

    private static void runDPExperiments(List<BenchmarkResult> results) {
        RandomDataGenerator generator = new RandomDataGenerator(SEED);
        int[] dpSizes = {100, 500, 1000};

        System.out.println("--- Running Part A: Dynamic Programming ---");
        for (int size : dpSizes) {
            String s1 = generator.generateString(size);
            String s2 = generator.generateString(size);

            LCS lcs = new LCS();
            lcs.findLength(s1, s2);
            long totalTimeLCS = 0;
            for (int r = 0; r < RUNS; r++) {
                long start = System.nanoTime();
                lcs.findLength(s1, s2);
                long end = System.nanoTime();
                totalTimeLCS += (end - start);
            }
            results.add(new BenchmarkResult("LCS", size, (totalTimeLCS / (double) RUNS) / 1e6, lcs.getOperationCount()));

            EditDistance ed = new EditDistance();
            ed.calculate(s1, s2);
            long totalTimeED = 0;
            for (int r = 0; r < RUNS; r++) {
                long start = System.nanoTime();
                ed.calculate(s1, s2);
                long end = System.nanoTime();
                totalTimeED += (end - start);
            }
            results.add(new BenchmarkResult("Edit Distance", size, (totalTimeED / (double) RUNS) / 1e6, ed.getOperationCount()));
        }
    }

    private static void runStringMatchingExperiments(List<BenchmarkResult> results) {
        RandomDataGenerator generator = new RandomDataGenerator(SEED);
        int[] textSizes = {10000, 50000, 100000};
        int patternLength = 15;

        System.out.println("--- Running Part B: String Matching ---");
        for (int size : textSizes) {
            String text = generator.generateString(size);
            String presentPattern = text.substring(size / 2, (size / 2) + patternLength);

            NaiveSearch naive = new NaiveSearch();
            KMPSearch kmp = new KMPSearch();
            RabinKarpSearch rk = new RabinKarpSearch();

            long timeNaive = measureMatching(naive, text, presentPattern);
            results.add(new BenchmarkResult("Naive Search", size, timeNaive / 1e6, naive.getOperationCount()));

            long timeKmp = measureMatching(kmp, text, presentPattern);
            results.add(new BenchmarkResult("KMP Search", size, timeKmp / 1e6, kmp.getOperationCount()));

            long timeRk = measureMatching(rk, text, presentPattern);
            results.add(new BenchmarkResult("Rabin-Karp Search", size, timeRk / 1e6, rk.getOperationCount()));
        }
    }

    private static long measureMatching(Object searcher, String text, String pattern) {
        long totalTime = 0;
        for (int r = 0; r < RUNS; r++) {
            long start = System.nanoTime();
            if (searcher instanceof NaiveSearch) ((NaiveSearch) searcher).search(text, pattern);
            else if (searcher instanceof KMPSearch) ((KMPSearch) searcher).search(text, pattern);
            else if (searcher instanceof RabinKarpSearch) ((RabinKarpSearch) searcher).search(text, pattern);
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        return totalTime / RUNS;
    }

    private static void runKasaiExperiments(List<BenchmarkResult> results) {
        RandomDataGenerator generator = new RandomDataGenerator(SEED);
        int[] kasaiSizes = {1000, 5000, 10000};

        System.out.println("--- Running Part C: Kasai's Algorithm ---");
        for (int size : kasaiSizes) {
            String text = generator.generateString(size);
            int[] sa = createSimpleSuffixArray(text);

            KasaiLCP kasai = new KasaiLCP();
            kasai.computeLCP(text, sa);

            long totalTime = 0;
            for (int r = 0; r < RUNS; r++) {
                long start = System.nanoTime();
                kasai.computeLCP(text, sa);
                long end = System.nanoTime();
                totalTime += (end - start);
            }
            results.add(new BenchmarkResult("Kasai LCP", size, (totalTime / (double) RUNS) / 1e6, kasai.getOperationCount()));
        }
    }

    private static int[] createSimpleSuffixArray(String text) {
        int n = text.length();
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        java.util.Arrays.sort(indices, (a, b) -> text.substring(a).compareTo(text.substring(b)));

        int[] sa = new int[n];
        for (int i = 0; i < n; i++) sa[i] = indices[i];
        return sa;
    }
}