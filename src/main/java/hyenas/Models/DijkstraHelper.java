package hyenas.Models;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Helper methods for Dijkstra's algorithm.
 * @author Alex
 */
public class DijkstraHelper {
    /**
     * Get the linear distance between two systems.
     * @param system1 starting system
     * @param system2 ending system
     * @return distance between the two systems
     */
    public static double getDistance(SolarSystem system1, SolarSystem system2) {
        int x1 = system1.getX();
        int y1 = system1.getY();
        int x2 = system2.getX();
        int y2 = system2.getY();
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    
    /**
     * Get the distance between two systems according to Djikstra's algorithm.
     * @param start starting system
     * @param goal ending system
     * @return distance between the two systems on the graph
     */
    public static double getDjikstraDistance(SolarSystem start, SolarSystem goal) {
        Queue<ABPair<SolarSystem, Double>> distances = new PriorityQueue<>(
                (ABPair<SolarSystem, Double> o1, ABPair<SolarSystem, Double> o2)
                        -> (int) (o1.getB() - o2.getB()));
        Map<SolarSystem, List<ABPair<SolarSystem, Double>>> adjList = Galaxy
                .getInstance().getDistances();

        if (!adjList.containsKey(start)) {
            return -1;
        }
        distances.add(new ABPair<>(start, 0.0));

        Set<SolarSystem> visited = new HashSet<>();

        while (!distances.isEmpty()) {
            ABPair<SolarSystem, Double> node = distances.remove();
            if (node.getA().equals(goal)) {
                return node.getB();
            }

            if (!visited.contains(node.getA())) {
                visited.add(node.getA());
                List<ABPair<SolarSystem, Double>> neighbors = adjList.get(node.getA());
                if (neighbors != null) {
                    adjList.get(node.getA()).parallelStream().forEach((neighbor) -> {
                            double alternateDistance = node.getB() + neighbor.getB();
                            distances.add(new ABPair<>(neighbor.getA(), alternateDistance));
                        });
                }
            }
        }

        return -1;
    }
}
