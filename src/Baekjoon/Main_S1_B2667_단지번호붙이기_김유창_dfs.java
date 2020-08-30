package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_S1_B2667_단지번호붙이기_김유창_dfs {
	public static int[][] arr;
	public static boolean[][] visited;
	public static int[] dx = { 0, 1, 0, -1 };
	public static int[] dy = { 1, 0, -1, 0 };
	public static int count = 0, n;
	public static int house;
	public static Queue<Integer> q = new PriorityQueue<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		arr = new int[n][n];
		visited = new boolean[n][n];
		String str = new String();
		for (int i = 0; i < n; i++) {
			str = bf.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] == false && arr[i][j] == 1) {
					count++;
					dfs(i, j);
					q.offer(house);
					house=0;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(visited[i][j])
					System.out.println(1+" ");
				else
					System.out.println(0+" ");
//				System.out.print(visited[i][j]);
			}
			System.out.println();
		}
		System.out.println(q.size());
		while(!q.isEmpty()) {
			System.out.println(q.poll());
		}
	}

	public static void dfs(int x, int y) {
		visited[x][y] = true;
		house++;
		arr[x][y] = count;
		int xx, yy;
		for (int i = 0; i < 4; i++) {
			xx = x + dx[i];
			yy = y + dy[i];
			if (xx >= 0 && xx < n && yy >= 0 && yy < n) {
				if (visited[xx][yy] == false && arr[xx][yy] == 1) {
					dfs(xx, yy);
				}
			}
		}
	}
}
