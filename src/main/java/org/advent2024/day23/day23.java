package org.advent2024.day23;

import java.util.*;

import static org.advent2024.day6.Puzzle12.readFromFile;

public class day23 {
    public static void main(String[] args) {
        System.out.println("day23");
        List<String> input = readFromFile("src/main/resources/day23/example.txt");
        Map<String, List<String>> networks = new HashMap<>();
        for (String line : input) {
            String[] parts = line.split("-");
            String computer = parts[0];
            String otherComputer = parts[1];
            networks.computeIfAbsent(computer, k -> new ArrayList<>()).add(otherComputer);
            networks.computeIfAbsent(otherComputer, k -> new ArrayList<>()).add(computer);
        }
        System.out.println(networks);
        Set<Set<String>> groups = findGroupsOf3withT(networks);
        System.out.println(groups.size());

//        List<String> sortedComputers = new ArrayList<>(networks.keySet());
//        Collections.sort(sortedComputers);
//        System.out.println(String.join(",", sortedComputers));
    }

    public static Set<Set<String>> findGroupsOf3withT(Map<String, List<String>> networks) {
        Set<Set<String>> groups = new HashSet<>();
        for (String computer : networks.keySet()) {
            if (computer.startsWith("t")) {
                for (String otherComputer : networks.get(computer)) {
                    if (!otherComputer.equals(computer)) {
                        for (String otherComputerOtherComputer : networks.get(otherComputer)) {
                            if (!otherComputerOtherComputer.equals(computer) && networks.get(otherComputerOtherComputer).contains(computer)) {
                                groups.add(Set.of(computer, otherComputer, otherComputerOtherComputer));
                            }
                        }
                    }
                }
            }
        }
        return groups;
    }




}

