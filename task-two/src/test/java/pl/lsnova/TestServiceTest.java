package pl.lsnova;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TestServiceTest {

    private final TestService testService = new TestService();

    @Test
    void getPairs() {

        //given
        int sum = 13;
        List<Integer> integers = Arrays.asList(1, 2, 10, 7, 5, 3, 6, 6, 13, 0);

        //when
        List<CustomDto> result = testService.getPairs(sum, integers);

        //then
        assertNotNull(result);
        assertEquals(4, result.size());

        CustomDto first = result.get(0);
        assertEquals(0, first.getFirstValue());
        assertEquals(13, first.getSecondValue());

        CustomDto last = result.get(3);
        assertEquals(6, last.getFirstValue());
        assertEquals(7, last.getSecondValue());
    }

    @Test
    void getPairs_duplicates() {

        //given
        int sum = 13;
        List<Integer> integers = Arrays.asList(1,2,3,4,5,6,10,10,1,2,2,10,11,11);

        //when
        List<CustomDto> result = testService.getPairs(sum, integers);

        //then
        assertNotNull(result);
        assertEquals(9, result.size());

        long twoCounter = result.stream().filter(customDto -> customDto.getFirstValue() == 2).count();
        assertEquals(6, twoCounter);

        long threeCounter = result.stream().filter(customDto -> customDto.getFirstValue() == 3).count();
        assertEquals(3, threeCounter);
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void getPairs_40_000_values() {

        //given
        int size = 40_000;
        int sum = 13;
        List<Integer> integers = getRandomArray(size);

        //when
        List<CustomDto> result = testService.getPairs(sum, integers);

        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    private static List<Integer> getRandomArray(int size) {
        return new Random().ints(0, 200)
                .limit(size)
                .boxed()
                .collect(Collectors.toList());
    }
}