import java.util.*;

public class FireTruck {
    private Map<Integer, List<Integer>> adj = new HashMap<>();
    private List<Integer> vList = new ArrayList<>();

    public FireTruck() {
    }

    public void addEdge(int v, int w) {
        if (!vList.contains(v)) {
            vList.add(v);
        }
        if (vList.contains(w)) {
            vList.add(w);
        }

        if (!adj.containsKey(v)) {
            adj.put(v, new ArrayList<>());
        }
        adj.get(v).add(w);

        if (!adj.containsKey(w)) {
            adj.put(w, new ArrayList<>());
        }
        adj.get(w).add(v);
    }

    public void printPaths(int s, int d) {
        Boolean[] visited = new Boolean[vList.size()];
        int[] path = new int[vList.size()];
        int path_index = 0;

        for (int i = 0; i < vList.size(); i++) {
            visited[i] = false;
        }

        printPathsUtil(s, d, visited, path, path_index);
    }

    public void printPathsUtil(int s, int d, Boolean[] visited, int[] path, int path_index) {
        visited[s] = true;
        path[path_index] = s;
        path_index++;

        if (s == d) {
            for (int i = 0; i < path_index; i++)
                System.out.print(path[i] + " ");
            System.out.println();
        } else {
            List<Integer> list = adj.get(s);
            for (Integer i : list) {
                if (!visited[i])
                    printPathsUtil(i, d, visited, path, path_index);
            }
        }

        path_index--;
        visited[s] = false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int counter = 1;

        for (int i = 0; i < 10; i++) {
            FireTruck g = new FireTruck();
            int v1, v2;
            v1 = sc.nextInt();
            v2 = sc.nextInt();
            while (!(v1 == 0 && v2 == 0)) {
                g.addEdge(v1, v2);
                v1 = sc.nextInt();
                v2 = sc.nextInt();
            }

            System.out.println("CASE " + counter + ": Print all paths to " + number);
            g.printPaths(1, number);
            counter++;
        }
    }
}