
public class DisjointSet{

    public int parents[];
    public int rank[];
    public int size[];
    public DisjointSet(int startNode, int endNode){
        this.parents = new int[endNode+1];
        this.rank = new int[endNode+1];
        this.size = new int[endNode+1];

        for(int i = 0; i<startNode; i++){
            this.parents[i] = -1;
            this.rank[i] = -1;
            this.size[i] = -1;
        }
        for(int i = startNode; i<=endNode; i++){
            this.parents[i] = i;
            this.rank[i] = 0;
            this.size[i] = 1;
        }
    }

    static void unionByRank(DisjointSet ds, int u, int v){
        int pu = getParent(ds, u);
        int pv = getParent(ds, v);

        int rpu = ds.rank[pu];
        int rpv = ds.rank[pv];

        if(rpu < rpv){
            ds.parents[pu] = pv;
        }
        else if(rpu > rpv){
            ds.parents[pv] = pu;
        }
        else{
            ds.parents[pv] = pu;
            ds.rank[pu] += 1; // updating rank if both the ultimate parents have same rank
        }

    }

    static void unionBySize(DisjointSet ds, int u, int v){
        int pu = getParent(ds, u);
        int pv = getParent(ds, v);

        int spu = ds.size[pu];
        int spv = ds.size[pv];

        if(spu < spv){
            ds.parents[pu] = pv;
            ds.size[pv] += ds.size[pu];
        }
        else{
            ds.parents[pv] = pu;
            ds.rank[pu] += ds.size[pv]; // updating size if both the ultimate parents have same size or size[pu] > size[pv]
        }

    }

    // when call getParent function then only the path compression happens 
    static int getParent(DisjointSet ds, int u){ // getting parent + path compression
        if(u == ds.parents[u]){
            return u;
        }
        int parent = getParent(ds, ds.parents[u]);
        ds.parents[u] = parent;
        return parent;
    }

    static void printMetaData(DisjointSet ds){
        System.out.println("Parents: ");
        for(int i = 0; i<8; i++){
            System.out.print(ds.parents[i] + " ");
            
        }
        System.out.println("");
        System.out.println("Rank: ");

        for(int i = 0; i<8; i++){
            System.out.print(ds.rank[i] + " ");
        }
        System.out.println("");
        System.out.println("Size: ");
        for(int i = 0; i<8; i++){
            System.out.print(ds.size[i] + " ");
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(0, 7);
        unionByRank(ds, 4, 1);
        printMetaData(ds);
        unionByRank(ds, 1, 3);
        printMetaData(ds);
        unionByRank(ds, 1, 0);
        printMetaData(ds);
        unionByRank(ds, 1, 2);
        printMetaData(ds);

        if(getParent(ds, 1) == getParent(ds, 7)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

        unionByRank(ds, 6, 5);
        printMetaData(ds);
        unionByRank(ds, 5, 7);
        printMetaData(ds);
        unionByRank(ds, 4, 6);
        printMetaData(ds);

        if(getParent(ds, 1) == getParent(ds, 7)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

        printMetaData(ds);

        
        

    }
}