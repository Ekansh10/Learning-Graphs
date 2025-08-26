package Questions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
   public static int minStepToReachTarget(int knightPos[], int targetPos[], int n) {
        // Code here
        if(knightPos[0] == targetPos[0] && knightPos[1] == targetPos[1]){ return 0;}
        
        int[][] visited = new int[n+1][n+1];
        for(int i = 0; i<n+1; i++){
            for(int j = 0; j<n+1; j++){
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        
        Queue<ArrayList<Integer>> q = new LinkedList<>();
        
        int[][] ways = {{-1,- 2}, {-1, 2}, {-2, -1}, {-2, 1},{1, -2}, {1, 2},{2, -1}, {2, 1}};//possible ways knight can move
        
        ArrayList<Integer> startPos = new ArrayList<>();
        startPos.add(knightPos[0]);
        startPos.add(knightPos[1]);
        
        visited[knightPos[0]][knightPos[1]] = 1;
    
        q.add(startPos);
        
        while(!q.isEmpty()){
            ArrayList<Integer> cell = q.poll();
            for(int[] way : ways){
                
                int x = cell.get(0) + way[0];
                int y = cell.get(1) + way[1];
                
                if(!(x>=1 && x<=n && y>=1 && y<=n)) continue; // checked for invalid position
                
                if(x == targetPos[0] && y == targetPos[1]){ // checked for target meet
                    visited[x][y] = Math.min(visited[x][y], visited[cell.get(0)][cell.get(1)]);
                    continue;
                }
                    
                ArrayList<Integer> neigh = new ArrayList<>();
                neigh.add(x);
                neigh.add(y);
                
                if(visited[x][y] > visited[cell.get(0)][cell.get(1)]+1){
                    visited[x][y] = visited[cell.get(0)][cell.get(1)]+1;
                    q.add(neigh);
                }
            }
        }
        
        int minStep = visited[targetPos[0]][targetPos[1]];
        
        return minStep;
        
    }

    public static void main(String[] args) {
        
        int[] target = {1,2};
        int[] curr = {3,3};
        int n = 3;
        
        int res = minStepToReachTarget(curr, target, n);
        System.out.println(res);
    }
}