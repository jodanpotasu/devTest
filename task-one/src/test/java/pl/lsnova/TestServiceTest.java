package pl.lsnova;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestServiceTest {

    private final TestService testService = new TestService();

    @Test
    void getSummaryStatistics() {

        //given
        List<Integer> integers = Arrays.asList(1, 10, 20, 20, 2, 5);

        //when
        CustomIntSummaryStatistics result = testService.getSummaryStatistics(integers);

        //then
        assertEquals(5, result.getUniqueCount());
        assertEquals(6, result.getCount());
        assertEquals(20, result.getMax());
        assertEquals(1, result.getMin());
    }

    @Test
    @Timeout(value = 400, unit = TimeUnit.MILLISECONDS)
        //on slower pc average is about 250
    void getSummaryStatistics_400_000_values() {

        //given
        int size = 500_000;
        List<Integer> integers = getRandomArray(size);

        //when
        CustomIntSummaryStatistics result = testService.getSummaryStatistics(integers);

        //then
        assertNotNull(result);
        assertEquals(size, result.getCount());
    }

    private static List<Integer> getRandomArray(int size) {
        return new Random().ints(0, 200)
                .limit(size)
                .boxed()
                .collect(Collectors.toList());
    }
}