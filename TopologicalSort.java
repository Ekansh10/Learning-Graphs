import java.util.*;

public class TopologicalSort {

    static void dfs(ArrayList<ArrayList<Integer>> adjlst, Stack<Integer> st, int vertice, int[] visited){
        visited[vertice] = 1;
        for(int neighbour : adjlst.get(vertice)){
            if(visited[neighbour] == 0){
                dfs(adjlst, st, neighbour, visited);
            }
        }
        st.add(vertice);
    }

    static int[] topoSortDFS(ArrayList<ArrayList<Integer>> adjacencyList, int V){
        Stack<Integer> st = new Stack<>();
        int[] visited = new int[V];
        for(int i = 0; i<V; i++){
            visited[i] = 0;
        }
        for(int i = 0; i<V; i++){
            if(visited[i] == 0){
                dfs(adjacencyList, st, i, visited);
            }
        }
        int[] res = new int[V];
        int i = 0;
        while(!st.isEmpty()){
            res[i++] = st.pop();
        }
        return res;
    }
    public static void main(String[] args) {
        ArrayList<Integer> v0 = new ArrayList<>();
        ArrayList<Integer> v1 = new ArrayList<>();
        ArrayList<Integer> v2 = new ArrayList<>();
        v2.add(3);
        ArrayList<Integer> v3 = new ArrayList<>();
        v3.add(1);
        ArrayList<Integer> v4 = new ArrayList<>();
        v4.add(0);
        v4.add(1);
        ArrayList<Integer> v5 = new ArrayList<>();
        v5.add(0);
        v5.add(2);

        ArrayList<ArrayList<Integer>> adjlst = new ArrayList<>();
        adjlst.add(v0);
        adjlst.add(v1);
        adjlst.add(v2);
        adjlst.add(v3);
        adjlst.add(v4);
        adjlst.add(v5);

        int[] result = topoSortDFS(adjlst, 6);

        System.out.println("Topological Sort: ");
        for(int i : result){
            System.out.print(i+" ");
        }
        System.out.println("");
    }
}
