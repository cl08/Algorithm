package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class a_ing_B_G5_17069_파이프옮기기2 {

	static int N, count;
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 0, 1 };

	static int[][] map;
	static int[][] dp;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 파이프 초기값
		count = 0;
		Pipe pipe = new Pipe(0, 1, 0);

		dfs(0, 1, 0);

		System.out.println(count);

	}

	public static void dfs(int x, int y, int shape) {
		if (shape == 0) {
			if (dp[x][y] == 0)
				dp[x][y] = dp[x][y - 1] + 1;
			else 
			dfs(x, y + 1, 0);
			dfs(x + 1, y + 1, 2);
		} else if (shape == 1) {
		} else if (shape == 2) {

		}
	}

	public static class Pipe {
		int x;
		int y;
		int shape;

		public Pipe(int x, int y, int shape) {
			this.x = x;
			this.y = y;
			this.shape = shape;
		}

	}
}
