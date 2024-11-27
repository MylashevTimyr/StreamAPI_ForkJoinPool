package org.example;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private final int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    @Override
    protected Long compute() {
        if (number <= 1) {
            return 1L;
        }

        int mid = number / 2;

        FactorialTask lowerHalfTask = new FactorialTask(mid);
        FactorialTask upperHalfTask = new FactorialTask(number - mid);

        lowerHalfTask.fork();

        long upperHalfResult = upperHalfTask.compute();
        long lowerHalfResult = lowerHalfTask.join();

        return multiplyRange(mid + 1, number) * lowerHalfResult;
    }

    private long multiplyRange(int start, int end) {
        long result = 1L;
        for (int i = start; i <= end; i++) {
            result *= i;
        }
        return result;
    }
}
