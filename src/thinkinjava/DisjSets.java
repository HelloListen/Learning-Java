package com.listenzhangbin.algorithm.unrelatedclass;

/**
 * Created by zhangbin on 16/7/3.
 */
public class DisjSets {

    public DisjSets(int numElements) {
        s = new int[numElements];
        for (int i = 0; i < s.length; i++) {
            s[i] = -1;
        }
    }

    public void union(int root1, int root2) {
        s[root2] = root1;
    }

    public int find(int x) {
        if (s[x] < 0) {
            return x;
        }
        return find(s[x]);
    }

    private int[] s;
}
