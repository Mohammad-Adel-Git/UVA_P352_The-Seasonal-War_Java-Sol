import java.io.*;
import java.util.*;

public class Main {
    static int n, m, t;
    static ArrayList<Integer>[] adj;
    static int[] distance;
    static char[][] values;
    static int[] nodesValues;
    static boolean[] visited;
    static Kattio io = new Kattio();
    public static void main(String[] args) throws Exception {
        int image = 0;
        n = io.nextInt();
        while(true){
            image++;
            values = new char[n][n];
            for (int i = 0; i < n; i++) {
                String row = io.next();
                values[i] = row.toCharArray();
            }
            adj = new ArrayList[n*n];
            for (int i = 0; i < n*n; i++){
                adj[i] = new ArrayList<>();
            }
            visited = new boolean[n*n];
            nodesValues = new int[n*n];
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (isOne(i, j)){
                        int nodeNum = getNodeNum(i, j);
                        nodesValues[nodeNum] = 1;
                        if (validIndices(i+1,j) && isOne(i+1,j))
                            adj[nodeNum].add(getNodeNum(i+1, j));
                        if(validIndices(i-1, j) && isOne(i-1, j))
                            adj[nodeNum].add(getNodeNum(i-1, j));
                        if (validIndices(i, j + 1) && isOne(i, j + 1))
                            adj[nodeNum].add(getNodeNum(i, j + 1));
                        if (validIndices(i, j - 1) && isOne(i, j - 1))
                            adj[nodeNum].add(getNodeNum(i, j - 1));
                        if (validIndices(i + 1, j + 1) && isOne(i + 1, j + 1))
                            adj[nodeNum].add(getNodeNum(i+1, j+1));
                        if (validIndices(i - 1, j - 1) && isOne(i - 1, j - 1))
                            adj[nodeNum].add(getNodeNum(i-1, j-1));
                        if (validIndices(i - 1, j + 1) && isOne(i - 1, j + 1))
                            adj[nodeNum].add(getNodeNum(i-1, j +1));
                        if (validIndices(i+1, j - 1) && isOne(i+1, j - 1))
                            adj[nodeNum].add(getNodeNum(i+1, j - 1));
                    }
                }
            }
            int sum = 0;
            for (int i =0; i < n*n; i++){
                if (!visited[i] && nodesValues[i] == 1){
                    DFS(i);
                    sum++;
                }
            }
            io.println("Image number " + image + " contains " + sum +" war eagles.");
            if (!io.ready())
                break;
            n = io.nextInt();
        }
        io.close();
    }
    public static int getNodeNum(int i, int j){
        return i * n + j;
    }
    public static boolean validIndices(int i, int j){
        return (i >= 0 && i < n) && (j >= 0 && j < n);
    }
    public static boolean isOne(int i, int j){
        return values[i][j] == '1';
    }
    public static int BFS(int s, int e){
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.add(s);
        distance[s] = 0;
        while(!dq.isEmpty()){
            int cur = dq.pollFirst();
            for(int node : adj[cur]){
                if (distance[node] > distance[cur] + 1){
                    distance[node] = distance[cur] + 1;
                    if (node == e)
                        return distance[e];
                    dq.add(node);
                }
            }
        }
        return distance[e];
    }
    public static void DFS(int i){
        visited[i] = true;
        for (int node : adj[i]){
            if (!visited[node])
                DFS(node);
        }
    }

    //BeginCodeSnip{Kattio}
    static class Kattio extends PrintWriter {
        private BufferedReader r;
        private StringTokenizer st;
        // standard input
        public Kattio() { this(System.in, System.out); }
        public Kattio(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }
        // USACO-style file input
        public Kattio(String problemName) throws IOException {
            super(problemName + ".out");
            r = new BufferedReader(new FileReader(problemName + ".in"));
        }
        // returns null if no more input
        public String next() {
            try {
                while (st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(r.readLine());
                return st.nextToken();
            } catch (Exception e) { }
            return null;
        }
        public int nextInt() { return Integer.parseInt(next()); }
        public double nextDouble() { return Double.parseDouble(next()); }
        public long nextLong() { return Long.parseLong(next()); }
        public boolean ready() throws IOException {
            return r.ready() || st.hasMoreTokens();
        }
        public String nextLine() throws IOException {
            return r.readLine();
        }
    }
    //EndCodeSnip
}