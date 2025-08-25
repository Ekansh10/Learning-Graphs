package Questions;

import java.util.*;

class GFGratInTheMaze {
    // Function to find all possible paths
    public static int[][] ways = {{-1,0},{1,0}, {0,-1}, {0, 1}};
    public static ArrayList<String> arr = new ArrayList<>();
    public static ArrayList<String> ratInMaze(int[][] maze) {
        // code here
        
        HashSet<String> visited = new HashSet<>();
        visited.add("00");
        helper(maze, "", 0, 0, visited);
        ArrayList<String> newarr = new ArrayList<>(arr);
        Collections.sort(newarr);
        arr.clear();
        return newarr;

    }
    public static void helper(int[][] maze, String s, int r, int c, HashSet<String> visited){
        if(r == maze.length-1 && c == maze[0].length-1){
            arr.add(s);
            return;
        }
        
        for(int[] way : ways){
            int nr = r + way[0];
            int nc = c + way[1];
            String cell = ""+nr+nc;
            if(nr>=0 && nr<maze.length && nc >= 0 && nc < maze[0].length && !visited.contains(cell) && maze[nr][nc] == 1){
                visited.add(cell);
                if(way[0] == -1 && way[1] == 0){
                    helper(maze, s+"U", nr, nc, visited);
                }
                else if(way[0] == 1 && way[1] == 0){
                    helper(maze, s+"D", nr, nc, visited);
                }
                else if(way[0] == 0 && way[1] == -1){
                    helper(maze, s+"L", nr, nc, visited);
                }
                else{
                    helper(maze, s+"R", nr, nc, visited);
                }
                visited.remove(cell);
            }
        }
        return;
    }
}