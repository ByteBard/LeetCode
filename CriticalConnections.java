package LeetCode;

import java.util.*;

public class CriticalConnections {

    public void run() {
        //4
        //[[0,1],[1,2],[2,0],[1,3]]

        List<Integer> test01 = new ArrayList<Integer>();
        List<Integer> test02 = new ArrayList<Integer>();
        List<Integer> test03 = new ArrayList<Integer>();
        List<Integer> test04 = new ArrayList<Integer>();
        test01.add(0);
        test01.add(1);
        test02.add(1);
        test02.add(2);
        test03.add(2);
        test03.add(0);
        test04.add(1);
        test04.add(3);

        List<List<Integer>> test05 = new ArrayList<>();
        test05.add(test01);
        test05.add(test02);
        test05.add(test03);
        test05.add(test04);

        List<List<Integer>> res = criticalConnections(4, test05);

        for (List<Integer> x : res) {
            for (int y : x) {
                System.out.println(y);

            }
        }
    }

    //30% 61% EN
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // 构建一个map，存放每个节点的相邻节点有哪些
        Map<Integer, Set<Integer>> map = new HashMap<>();
        buildMap(connections, map);

        // 创建一个数组，存放每个节点的id是什么
        int[] id = new int[n];
        Arrays.fill(id, -1);

        // 选取一个点作为根节点，dfs向下递归，过程中识别出哪个边是critical connection
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, 0, -1, id, map, res);    // 假设根节点有一个编号是-1父节点

        return res;
    }

    public int dfs(int node, int nodeID, int par, int[] id, Map<Integer, Set<Integer>> map, List<List<Integer>> res) {
        id[node] = nodeID;

        Set<Integer> set = map.get(node);
        for (Integer neighbor : set) {
            if (neighbor == par) {
                continue;
            } else if (id[neighbor] == -1) {
                id[node] = Math.min(id[node], dfs(neighbor, nodeID + 1, node, id, map, res));
            } else {
                id[node] = Math.min(id[node], id[neighbor]);
            }
        }

        if (id[node] == nodeID && node != 0) {
            res.add(Arrays.asList(par, node));
        }

        return id[node];
    }

    public void buildMap(List<List<Integer>> con, Map<Integer, Set<Integer>> map) {
        for (List<Integer> edge : con) {
            int n1 = edge.get(0);
            int n2 = edge.get(1);

            Set<Integer> n1n = map.getOrDefault(n1, new HashSet<>());
            Set<Integer> n2n = map.getOrDefault(n2, new HashSet<>());

            n1n.add(n2);
            n2n.add(n1);

            map.put(n1, n1n);
            map.put(n2, n2n);
        }
    }
}
