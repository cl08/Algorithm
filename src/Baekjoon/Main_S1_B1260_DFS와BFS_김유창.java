package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_B1260_DFS와BFS_김유창 {
	static int size, n, start;
	static boolean[] visited;
	static boolean[][] arr;
	static StringBuilder sb = new StringBuilder();
	static Queue<Integer> q = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		arr = new boolean[size + 1][size + 1];
		visited = new boolean[size + 1];
		int temp1, temp2;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			temp1 = Integer.parseInt(st.nextToken());
			temp2 = Integer.parseInt(st.nextToken());
			arr[temp1][temp2] = true;
			arr[temp2][temp1] = true;

		}
		dfs(start);

		sb.append('\n');
		Arrays.fill(visited, false);
		
		bfs(start);
		System.out.println(sb);
	}

	public static void dfs(int start) {
		visited[start] = true;
		sb.append(start).append(' ');
		for (int i = 1; i < size + 1; i++) {
			if (arr[start][i] && !visited[i]) {
				dfs(i);
			}
		}
	}

	public static void bfs(int current) {
		q.offer(current);
		visited[current] = true;
		while (!q.isEmpty()) {
			current = q.poll();
			sb.append(current).append(' ');
			for (int i = 1; i < size + 1; i++) {
				if (arr[current][i] && !visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
	}
}
