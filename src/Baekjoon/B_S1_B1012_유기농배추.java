package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_S1_B1012_유기농배추 {
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int xSize, ySize;
	static boolean[][] visited;
	static boolean[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int n;
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			xSize = Integer.parseInt(st.nextToken());
			ySize = Integer.parseInt(st.nextToken());
			arr = new boolean[xSize][ySize];
			visited = new boolean[xSize][ySize];
			n = Integer.parseInt(st.nextToken());
			int x, y, count=0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				arr[x][y] = true;
			}
			for (int i = 0; i < xSize; i++) {
				for (int j = 0; j < ySize; j++) {
					if (!visited[i][j] && arr[i][j]) {
						dfs(i, j);
						count++;
					}
					
				}
			}
			System.out.println(count);
		}
	}
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if (xx >= 0 && xx < xSize && yy >= 0 && yy < ySize && !visited[xx][yy] && arr[xx][yy] == true) {
				dfs(xx, yy);
			}
		}
	}
}
