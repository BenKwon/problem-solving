package 백준.DP;

import java.io.*;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
    static int[][] graph = new int[3][3];
    public static void bfs(int[][] tmp) {
        tmp[0][0] =3;
    }
    public static void main(String[] args) {

        bfs(graph);
        System.out.println("graph[0][0] = " + graph[0][0]);
//        double min_diff = 1000000000;
//        int min_diff_index = 0;
//        double[] arr = new double[]{12.331, 12.3444, 12.357, 12.37, 12.383, 12.396, 12.409, 12.422, 13, 14};
//        double input = 12.4;
//        for (int i = 0; i < 100; i++) {
//            double diff = Math.abs(input - arr[(int) i]);
//            System.out.println("diff = " + diff);
//            if (diff < min_diff) {
//                min_diff = diff;
//                min_diff_index = i;
//            }
//            if (arr[i] > input) break;
//        }
//        System.out.println("min_diff_index = " + min_diff_index);
//        System.out.println(arr[min_diff_index]);
    }

}