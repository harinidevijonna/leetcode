//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class DriverClass
{
    public static void main(String args[]) throws IOException {

        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);
    
            ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
            for(int i=0;i<V;i++)
            {
                adj.add(new ArrayList<ArrayList<Integer>>());
            }
            
            int i=0;
            while (i++<E) {
                String S[] = read.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                int w = Integer.parseInt(S[2]);
                ArrayList<Integer> t1 = new ArrayList<Integer>();
                ArrayList<Integer> t2 = new ArrayList<Integer>();
                t1.add(v);
                t1.add(w);
                t2.add(u);
                t2.add(w);
                adj.get(u).add(t1);
                adj.get(v).add(t2);
            }
            
            int S = Integer.parseInt(read.readLine());
            
            Solution ob = new Solution();
            
            int[] ptr = ob.dijkstra(V, adj, S);
            
            for(i=0; i<V; i++)
                System.out.print(ptr[i] + " ");
            System.out.println();
        }
    }
}
// } Driver Code Ends


//User function Template for Java
class Pair{
    int dist;
    int node;
    Pair(int dist,int node){
        this.dist=dist;
        this.node=node;
    }
}

class Solution
{
    //Function to find the shortest distance of all the vertices
    //from the source vertex S.
    static int[] dijkstra(int v, ArrayList<ArrayList<ArrayList<Integer>>> adj, int s)
    {
        // Write your code here
        int dis[]=new int[v];
        for(int i=0;i<v;i++){
            dis[i]=(int)(1e9);
        }
        dis[s]=0;
        PriorityQueue<Pair>pq=new PriorityQueue<>((x,y)->(x.dist-y.dist));
        pq.add(new Pair(0,s));
        while(!pq.isEmpty()){
            int n=pq.size();
            while(n-->0){
                Pair pair=pq.poll();
                for(int i=0;i<adj.get(pair.node).size();i++){
                    if(adj.get(pair.node).get(i).get(1)+dis[pair.node]<dis[adj.get(pair.node).get(i).get(0)]){
                        dis[adj.get(pair.node).get(i).get(0)]=adj.get(pair.node).get(i).get(1)+dis[pair.node];
                        pq.add(new Pair(dis[adj.get(pair.node).get(i).get(0)],adj.get(pair.node).get(i).get(0)));
                    }
                }
            }
        }
        return dis;
    }
}

