package pl.lsnova;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

public class CustomIntSummaryStatistics implements IntConsumer {

    private final Set<Integer> values = new HashSet<>();
    private long count;
    private int min = Integer.MAX_VALUE;
    private int max = Integer.MIN_VALUE;

    public void combine(CustomIntSummaryStatistics other) {
        count += other.count;
        values.addAll(other.getValues());
        min = Math.min(min, other.min);
        max = Math.max(max, other.max);
    }

    @Override
    public void accept(int value) {
        ++count;
        values.add(value);
        min = Math.min(min, value);
        max = Math.max(max, value);
    }

    public long getUniqueCount() {
        return this.values.size();
    }

    public List<Integer> getValues() {
        return values.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public long getCount() {
        return count;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "CustomIntSummaryStatistics{" +
                getValues() +
                "count=" + getCount() +
                ", distinct=" + getUniqueCount() +
                ", min=" + getMin() +
                ", max=" + getMax() +
                '}';
    }
}
