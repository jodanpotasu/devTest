package pl.lsnova;

import java.util.*;
import java.util.stream.Collectors;

public class TestService {

    public void readFromConsole(String[] args) {
        List<Integer> ints = Arrays.stream(args)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        getPairs(13, ints).forEach(System.out::println);
    }

    public List<CustomDto> getPairs(Integer sum, List<Integer> integers) {

        Collections.sort(integers);
        List<CustomDto> customDtos = new ArrayList<>();

        int startIndex = 0;
        int endIndex = integers.size() - 1;

        while (startIndex < endIndex) {

            int valueSum = integers.get(startIndex) + integers.get(endIndex);

            if (valueSum == sum) {

                customDtos.add(new CustomDto(integers.get(startIndex), integers.get(endIndex)));
                startIndex++;
            } else if (valueSum < sum) {

                startIndex = startIndex + 1;
            } else {

                endIndex = endIndex - 1;
            }
        }

        return customDtos.stream()
                .sorted(Comparator.comparingInt(CustomDto::getFirstValue))
                .collect(Collectors.toList());
    }
}
