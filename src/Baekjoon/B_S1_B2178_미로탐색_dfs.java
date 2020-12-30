package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_S1_B2178_미로탐색_dfs {

	static boolean[][] arr;
	static boolean[][] visited;
	static int n, m, result;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new boolean[n][m];
		visited = new boolean[n][m];
		result = n * m;
		String str;
		for (int i = 0; i < n; i++) {
			str = br.readLine();
			for (int j = 0; j < m; j++) {
				if(str.charAt(j)=='1')
					arr[i][j] = true;
				else
					arr[i][j] = false;
			}
		}
		dfs(0, 0, 1);
		sb.append(result);
		System.out.println(sb);
	}
	public static void dfs(int x, int y, int count) {
		if (x == n - 1 && y == m - 1 && count < result) {
			result = count;
		}
		visited[x][y] = true;
		int curX, curY;
		for (int i = 0; i < 4; i++) {
			curX = x + dx[i];
			curY = y + dy[i];
			if (curX >= 0 && curX < n && curY >= 0 && curY < m && !visited[curX][curY] && arr[curX][curY]) {
				dfs(curX, curY, count + 1);
			}
		}
		visited[x][y] = false;
	}
}