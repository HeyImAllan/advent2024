package org.advent2024.day13;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.advent2024.day6.Puzzle12.readFromFile;

public class Puzzle25 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<String> input = readFromFile("src/main/resources/puzzle25/input.txt");

        System.out.println(process(input));

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total runtime: " + totalTime + "ms");
    }

    private static long process(List<String> input) {
        int count = 0;
        long answer = 0L;
        String buttonRegex = "\\d+";
        double x1 = 0;
        double y1 = 0;
        double x2 = 0;
        double y2 = 0;
        double p1 = 0;
        double p2 = 0;
        for (String line: input) {
            if (count == 4) {

                count = 0;
            }

            if (count == 0 ){
                Matcher m = Pattern.compile(buttonRegex)
                        .matcher(line);
                List<Integer> button = new ArrayList<>();
                while (m.find()) {
                    button.add(Integer.valueOf(m.group()));
                }
                x1 = button.get(0);
                y1 = button.get(1);
            }
            if (count == 1 ){
                Matcher m = Pattern.compile(buttonRegex)
                        .matcher(line);
                List<Integer> button = new ArrayList<>();
                while (m.find()) {
                    button.add(Integer.valueOf(m.group()));
                }
                x2 = button.get(0);
                y2 = button.get(1);
            }
            if (count == 2 ){
                Matcher m = Pattern.compile(buttonRegex)
                        .matcher(line);
                List<Integer> button = new ArrayList<>();
                while (m.find()) {
                    button.add(Integer.valueOf(m.group()));
                }
                p1 = button.get(0);
                p2 = button.get(1);
                System.out.println(x1 + " " + y1 + " " + x2 + " " +  y2 + " " + p1 +" "+p2);
                //Button A: X+94, Y+34
                //Button B: X+22, Y+67
                //Prize: X=8400, Y=5400
                // a * x1 + b * x2 = p1
                // a * y1 + b * y2 = p2

                // a * x1 * y2 + b * x2 * y2 = p1 * y2
                // a * x2 * y1 + b * x2 * y2 = x2 * y3

                // a * x1 * y2 - a * x2 * y1 = x3 * y2 - x2 * y3
                // a = (p1 * y2 - x2 * p2) / (x1 * y2 - x2 * y1)

                double a = (p1*y2 - p2*x2) / (x1*y2 - y1*x2);
                double b = (p2*x1 - p1*y1) / (x1*y2 - y1*x2);
                System.out.println(a + " " + b);
                if (a % 1 == 0 && b % 1 == 0){
                    answer += (long) (3 * a + b);
                }





            }
            count++;

        }
        return answer;
    }

}