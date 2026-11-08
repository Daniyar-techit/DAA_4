package util;

import java.util.List;

public class BenchmarkResult {
    private final String algorithmName;
    private final int inputSize;
    private final double averageTimeMs;
    private final long operationCount;

    public BenchmarkResult(String algorithmName, int inputSize, double averageTimeMs, long operationCount) {
        this.algorithmName = algorithmName;
        this.inputSize = inputSize;
        this.averageTimeMs = averageTimeMs;
        this.operationCount = operationCount;
    }

    public String getAlgorithmName() { return algorithmName; }
    public int getInputSize() { return inputSize; }
    public double getAverageTimeMs() { return averageTimeMs; }
    public long getOperationCount() { return operationCount; }

    @Override
    public String toString() {
        return String.format("%-18s | Size: %-7d | Avg Time: %8.4f ms | Operations: %d",
                algorithmName, inputSize, averageTimeMs, operationCount);
    }
}