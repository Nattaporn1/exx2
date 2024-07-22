import java.util.*;

public class MirrorReflection {
    
    static class Point {
        int x, y, mirrors;
        
        Point(int x, int y, int mirrors) {
            this.x = x;
            this.y = y;
            this.mirrors = mirrors;
        }
    }
    
    public static boolean isSolvable(int startX, int startY, int endX, int endY, Set<Point> mirrors, int maxMirrors) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Queue<Point> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.add(new Point(startX, startY, 0));
        visited.add(startX + "," + startY);
        
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            
            if (current.x == endX && current.y == endY) {
                return true;
            }
            
            for (int[] direction : directions) {
                int nx = current.x + direction[0];
                int ny = current.y + direction[1];
                Point nextPoint = new Point(nx, ny, current.mirrors + 1);
                
                if (isInBounds(nx, ny, startX, startY, endX, endY, mirrors) 
                        && !visited.contains(nx + "," + ny) 
                        && nextPoint.mirrors <= maxMirrors) {
                    queue.add(nextPoint);
                    visited.add(nx + "," + ny);
                }
            }
        }
        
        return false;
    }
    
    private static boolean isInBounds(int x, int y, int startX, int startY, int endX, int endY, Set<Point> mirrors) {
        return (x == startX && y == startY) || (x == endX && y == endY) || mirrors.contains(new Point(x, y, 0));
    }

    public static void main(String[] args) {
        int startX = 0, startY = 0;
        int endX = 3, endY = 3;
        Set<Point> mirrors = new HashSet<>(Arrays.asList(
                new Point(1, 0, 0),
                new Point(1, 1, 0),
                new Point(2, 1, 0),
                new Point(2, 2, 0),
                new Point(3, 2, 0)
        ));
        int maxMirrors = 3;

        System.out.println(isSolvable(startX, startY, endX, endY, mirrors, maxMirrors));
    }
}
