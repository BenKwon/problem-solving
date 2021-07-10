package 알고리즘기법;

/**
 * union find (disjoint set) 구현하기
 * 백준 사용 문제  : 10755 공항
 */
public class UnionFind {
    public static int getParent(int[] parent, int x){
        if(parent[x] == x) return x;
        else{
            return parent[x] = getParent(parent, parent[x]);
        }
    }

    public static void unionParent(int[] parent, int a, int b){
        a = getParent(parent, a);
        System.out.println("a = " + a);
        b = getParent(parent, b);
        System.out.println("b = " + b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static int findParent(int parent[], int a, int b){
        a = getParent(parent, a);
        b = getParent(parent, b);
        if(a == b) return 1;
        else return 0;
    }

    public static void main(String[] args) {
        int[] node = new int[11];
        for (int i = 1; i < 11; i++) {
            node[i] = i;
        }
        unionParent(node, 1, 2);
        unionParent(node, 2, 4);
        unionParent(node, 5, 6);
//        System.out.println("2과 5는 연결되어 있나요? " + findParent(node, 2,5));
        unionParent(node,2,6);
        System.out.println("2과 5는 연결되어 있나요? " + findParent(node, 2,5));
        getParent(node, 6);
        System.out.println(node[6]);

    }
}
