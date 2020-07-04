package pl.lsnova;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestService {

    public void readFromConsole(String[] args) {

        List<Integer> integers = Arrays.stream(args)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        CustomIntSummaryStatistics summaryStatistics = getSummaryStatistics(integers);
        System.out.println(summaryStatistics);
    }

    public CustomIntSummaryStatistics getSummaryStatistics(List<Integer> integers) {
        return integers.stream()
                .collect(CustomIntSummaryStatistics::new,
                        CustomIntSummaryStatistics::accept,
                        CustomIntSummaryStatistics::combine);
    }
}
