package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class B_S1_B2667_단지번호붙이기_bfs {
	public static int[][] arr;
	public static boolean[][] visited;
	public static int[] dx = { 0, 1, 0, -1 };
	public static int[] dy = { 1, 0, -1, 0 };
	public static int count = 0, n, house;
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
					house=0;
					bfs(i, j);
				}
			}
		}
		System.out.println(q.size());
		while(!q.isEmpty()) {
			System.out.println(q.poll());
		}
	}

	public static void bfs(int x, int y) {
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		arr[x][y] = count;
		house++;
		visited[x][y] = true;
		qx.offer(x);
		qy.offer(y);
		int curX,curY,xx,yy;
		while (!qx.isEmpty() && !qy.isEmpty()) {
			curX = qx.poll();
			curY = qy.poll();
			for (int i = 0; i < 4; i++) {
				xx = curX+dx[i];
				yy = curY+dy[i];
				if (xx >= 0 && xx < n && yy >= 0 && yy < n) {
					if (visited[xx][yy] == false && arr[xx][yy] == 1) {
						arr[xx][yy] = count;
						visited[xx][yy]=true;
						house++;
						qx.offer(xx);
						qy.offer(yy);
					}
				}
			}
		}
		q.offer(house);
	}
}
