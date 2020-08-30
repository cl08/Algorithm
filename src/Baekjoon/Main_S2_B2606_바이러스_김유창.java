package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S2_B2606_바이러스_김유창 {
	static int size, result = 0;
	static boolean[][] arr;
	static boolean[] visited;
	static Queue<Integer> q = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		arr = new boolean[size + 1][size + 1];
		visited = new boolean[size + 1];
		int n = Integer.parseInt(br.readLine());
		int temp1, temp2;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			temp1 = Integer.parseInt(st.nextToken());
			temp2 = Integer.parseInt(st.nextToken());
			arr[temp1][temp2] = true;
			arr[temp2][temp1] = true;
		}
		bfs();
		System.out.println(result);
	}

	public static void bfs() {
		int current;
		visited[1] = true;
		q.offer(1);
		while (!q.isEmpty()) {
			current = q.poll();
			for (int i = 1; i < size + 1; i++) {
				if (arr[current][i] && !visited[i]) {
					q.offer(i);
					visited[i] = true;
					result++;
				}
			}
		}
	}
}
