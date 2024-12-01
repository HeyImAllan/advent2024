package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;

import static org.example.Puzzle1.readListsFromFile;

public class Puzzle2 {

    public static void solve() {
        ArrayList<List<Integer>> puzzleInput = readListsFromFile("src/main/resources/puzzle1/input.txt");
        List<Integer> list1 = puzzleInput.get(0).stream().sorted().toList();
        List<Integer> list2 = puzzleInput.get(1).stream().sorted().toList();
        Integer answer = 0;
        for (int i = 0 ; i <list1.size() - 1; i++) {
            Integer integer1 = list1.get(i);
            System.out.println("Processing " + integer1);
            Integer count = Math.toIntExact(list2.stream().
                    filter(integer2 -> Objects.equals(integer2, integer1)).
                    count());
            answer += (integer1 * count);
        }
        System.out.println(answer);
    }
}
