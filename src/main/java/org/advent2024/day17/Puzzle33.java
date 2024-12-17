package org.advent2024.day17;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.advent2024.day6.Puzzle12.readFromFile;

public class Puzzle33{
    private static int regA = 0;
    private static int regB = 0;
    private static int regC = 0;
    private static List<Integer> instr = List.of();



    public static String part1() {
        Computer computer = new Computer(regA, regB, regC, instr);
        computer.execute();
        return computer.print();
    }

    private static class Computer {
        long A;
        long B;
        long C;
        List<Integer> opcodes;
        List<Integer> out = new ArrayList<>();
        int ip;

        Computer(long registerA, int registerB, int registerC, List<Integer> opcodes) {
            A = registerA;
            B = registerB;
            C = registerC;
            this.opcodes = opcodes;
            ip = 0;
        }

        void execute() {
            while (ip < opcodes.size()) {
                int litOp = opcodes.get(ip + 1);
                long combOp = getCombo(opcodes.get(ip + 1));
                boolean skipIncrease = false;
                switch (opcodes.get(ip)) {
                    case 0 -> {
                        long den = (long) Math.pow(2, combOp); // den is always 8?
                        A = A / den;
                    }
                    case 1 -> B = B ^ ((long) litOp);
                    case 2 -> B = combOp % 8;
                    case 3 -> {
                        if (A != 0) {
                            ip = litOp;
                            skipIncrease = true;
                        }
                    }
                    case 4 -> B = B ^ C;
                    case 5 -> out.add((int) (combOp % 8L));
                    case 6 -> {
                        long den = (long) Math.pow(2, combOp);
                        B = A / den;
                    }
                    case 7 -> {
                        long den = (long) Math.pow(2, combOp);
                        C = A / den;
                    }
                }
                if (!skipIncrease) {
                    ip += 2;
                }
            }
        }

        long getCombo(int value) {
            return switch (value % 8) {
                case 0, 1, 2, 3 -> value;
                case 4 -> A;
                case 5 -> B;
                case 6 -> C;
                case 7 -> Long.MIN_VALUE;
                default -> throw new IllegalStateException("Impossible to reach");
            };
        }

        String print() {
            return String.join(",", out.stream().map(Long::toString).toList());
        }
    }

    public static void main(String[] args) {
        List<String> input = readFromFile("src/main/resources/puzzle33/input.txt");
        regA = Integer.parseInt(input.get(0).split("Register A: ")[1]);
        regB = Integer.parseInt(input.get(1).split("Register B: ")[1]);
        regC = Integer.parseInt(input.get(2).split("Register C: ")[1]);

        String instrRegex = "\\d+";
        Matcher m = Pattern.compile(instrRegex)
                .matcher(input.get(4).split("Program: ")[1]);
        List<Integer> instructions = new ArrayList<>();
        while (m.find()) {
            instructions.add(Integer.valueOf(m.group()));
        }
        instr = Collections.unmodifiableList(instructions);
        System.out.println(part1());
    }
}