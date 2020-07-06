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
    void getGraphsCount() {

        //given
        List<Integer> integers = Arrays.asList(1, 2, 2, 3, 5, 6);

        //when
        int result = testService.getGraphsCount(integers);

        //then
        assertEquals(2, result);
    }

    @Test
    void getGraphsCount_second() {

        //given
        List<Integer> integers = Arrays.asList(1, 2, 1, 40, 1, 41, 2, 3, 5, 6, 7, 10, 10, 11, 11, 40);

        //when
        int result = testService.getGraphsCount(integers);

        //then
        assertEquals(2, result);
    }

    @Test
    @Timeout(value = 400, unit = TimeUnit.MILLISECONDS)
    void getGraphsCount_400_000_values() {

        //given
        int size = 400_000;
        List<Integer> integers = getRandomArray(size);

        //when
        testService.getGraphsCount(integers);
    }

    @Test
    void getGraphsCount_not_even() {

        //given
        List<Integer> integers = Arrays.asList(1, 2, 3, 5, 6);

        //when
        //then
        RuntimeException throwable = assertThrows(
                RuntimeException.class,
                () -> testService.getGraphsCount(integers)
        );

        assertEquals("Vertices size must be even", throwable.getMessage());
    }

    private static List<Integer> getRandomArray(int size) {
        return new Random().ints(0, 200)
                .limit(size)
                .boxed()
                .collect(Collectors.toList());
    }
}