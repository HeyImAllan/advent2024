package org.advent2024.day20;

import org.advent2024.day16.DijkstraPqueue;
import org.advent2024.day16.NodeDist;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.advent2024.day16.DijkstraPqueue.createGraphFromPointsList;
import static org.advent2024.day6.Puzzle12.readFromFile;
import static org.advent2024.util.MapTools.*;


public class day20 {
    public static void main(String[] args) {
        part1();
    }
    public static void part1() {
        long startTime = System.currentTimeMillis();
        List<String> input = readFromFile("src/main/resources/day20/input.txt");
        Map<Point, String> map = buildmap(input);
        int mapHeight = input.size();
        int mapWidth = input.get(0).length();
        printMap(map, mapHeight, mapWidth);
        List<Point> nodes = getAllPoints(map, ".");
        List<Point> walls = getAllPoints(map, "#");
        System.out.println(nodes);
        System.out.println(walls);
        Point start = getfirstcoordinateof("S", map);
        Point end = getfirstcoordinateof("E", map);
        nodes.remove(start);
        nodes.remove(end);
        nodes.add(0, start);
        nodes.add(end);
        List<List<NodeDist>> graph = createGraphFromPointsList(nodes, map);
        //System.out.println(graph);
        Map<Integer, Integer> result = DijkstraPqueue.ShortestPath(0, nodes.size(), graph);

        //System.out.println("answer 1: " + result.get(nodes.size()-1) + " picoseconds");
        int INF = 999999999;
        int answerp1 = 0;
        for (Point p : walls) {
            if (p.x != 0 && p.y != 0 && p.x != mapHeight - 1 && p.y != mapWidth - 1) {
                List<Point> testNodes = new ArrayList<>(nodes);
                testNodes.add(nodes.size() - 2, p);
                Map newmap = updateMap(p, ".", map);
                List<List<NodeDist>> tempGraph = createGraphFromPointsList(testNodes, newmap);
                Map<Integer, Integer> tempResult = DijkstraPqueue.ShortestPath(0, testNodes.size(), tempGraph);
                if (result.get(nodes.size() - 1) - tempResult.get(testNodes.size() - 1) >= 100) {
                    //System.out.println("Alternative route:" + tempResult.get(testNodes.size() - 1) + " picoseconds");
                    answerp1++;
                }
            }
        }
        System.out.println("answer part1: " + answerp1);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total runtime: " + totalTime + "ms");

    }

}
