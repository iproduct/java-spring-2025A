package course.spring.streams;

import java.util.Arrays;

public class StreamsDemo01 {
    public static void main(String[] args) {
        int[][] points = {{5, 6}, {3, 4}, {10, 2}, {7, 8}};
        Arrays.stream(points)
                .parallel()
                .sorted((p1, p2) -> p1[0] - p2[0])
                .mapToDouble(p -> Math.hypot(p[0], p[1]))
//                .filter(r -> r <= 5.0 )
                .forEach(d -> System.out.println(d));
    }
}
