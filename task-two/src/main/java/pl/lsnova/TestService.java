package pl.lsnova;

import java.util.*;
import java.util.stream.Collectors;

public class TestService {

    public void readFromConsole(String[] args) {
        int sum = 13;

        List<Integer> ints = Arrays.stream(args)
                .mapToInt(Integer::parseInt)
                .filter(value -> value <= sum)
                .boxed()
                .collect(Collectors.toList());

        getPairs(sum, ints)
                .forEach(System.out::println);
    }

    public List<CustomDto> getPairs(Integer sum, List<Integer> integers) {

        Collections.sort(integers);
        List<CustomDto> customDtos = new ArrayList<>();

        int startIndex = 0;
        int endIndex = integers.size() - 1;

        while (startIndex < endIndex) {

            Integer firstValue = integers.get(startIndex);

            int valueSum = firstValue + integers.get(endIndex);

            if (valueSum == sum) {

                customDtos.add(new CustomDto(firstValue, integers.get(endIndex)));

                addSubPairs(startIndex, sum, customDtos, integers);
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

    private void addSubPairs(int currentIndex, Integer sum, List<CustomDto> customDtos, List<Integer> integers) {
        int searchValue = sum - integers.get(currentIndex);
        int[] primitiveArray = integers.stream().mapToInt(i -> i).toArray();

        int index = Arrays.binarySearch(primitiveArray, searchValue);
        int first = index;
        int last = index;

        //surrounding values
        while (first > 0 && integers.get(first - 1) == searchValue) {
            customDtos.add(new CustomDto(integers.get(currentIndex), integers.get(--first)));
        }
        while (last < integers.size() - 1 && integers.get(last + 1) == searchValue) {
            customDtos.add(new CustomDto(integers.get(currentIndex), integers.get(++last)));
        }
    }
}
