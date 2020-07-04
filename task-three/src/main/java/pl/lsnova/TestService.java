package pl.lsnova;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestService {

    public void readFromConsole(String[] args) {

        int argSize = Integer.parseInt(args[0]) * 2;

        Scanner scan = new Scanner(System.in);
        List<Integer> vertices = IntStream
                .generate(scan::nextInt)
                .limit(argSize)
                .boxed()
                .collect(Collectors.toList());

        int graphsCount = getGraphsCount(vertices);
        System.out.println(graphsCount);
    }

    public int getGraphsCount(List<Integer> vertices) {

        if (vertices.size() % 2 != 0) {
            throw new RuntimeException("Vertices size must be odd");
        }

        int counter = 0;
        Set<Integer> graphs = new HashSet<>();

        for (int i = 0; i < vertices.size(); i = i + 2) {
            int first = vertices.get(i);
            int second = vertices.get(i + 1);

            if ((first + 1 == second) || (first - 1 == second)) {

                if (!graphs.contains(first) && !graphs.contains(second)) {
                    counter++;

                }
                graphs.add(first);
                graphs.add(second);

            }
        }

        return counter;
    }
}
