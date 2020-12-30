package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S_D4_9282_초콜릿과건포도 {
	static int n, m, result;
	static int[][] map;
	static int[][][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new int[n][m];
			dp = new int[n + 1][m + 1][n + 1][m + 1];
			for (int[][][] d1 : dp) {
				for (int[][] d2 : d1) {
					for (int[] d3 : d2) {
						Arrays.fill(d3, Integer.MAX_VALUE);
					}
				}
			}
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = cut(0, 0, n, m);
			System.out.println("#" + tc + " " + result);
		}
	}

	public static int cut(int x, int y, int r, int c) {
		if (dp[x][y][r][c] != Integer.MAX_VALUE)
			return dp[x][y][r][c];
		if (r == 1 && c == 1) {
			return 0;
		}
		int sum = 0;
		for (int i = x; i < x + r; i++) {
			for (int j = y; j < y + c; j++) {
				sum = sum + map[i][j];
			}
		}
		int subSum;
		for (int i = 1; i < c; i++) {
			if (dp[x][y][r][i] == Integer.MAX_VALUE)
				dp[x][y][r][i] = cut(x, y, r, i);
			if (dp[x][y + i][r][c - i] == Integer.MAX_VALUE)
				dp[x][y + i][r][c - i] = cut(x, y + i, r, c - i);
			subSum = sum + dp[x][y][r][i] + dp[x][y + i][r][c - i];
			if (subSum < dp[x][y][r][c])
				dp[x][y][r][c] = subSum;
		}
		for (int i = 1; i < r; i++) {
			if (dp[x][y][i][c] == Integer.MAX_VALUE)
				dp[x][y][i][c] = cut(x, y, i, c);
			if (dp[x + i][y][r - i][c] == Integer.MAX_VALUE)
				dp[x + i][y][r - i][c] = cut(x + i, y, r - i, c);
			subSum = sum + dp[x][y][i][c] + dp[x + i][y][r - i][c];
			if (subSum < dp[x][y][r][c])
				dp[x][y][r][c] = subSum;
		}
		return dp[x][y][r][c];
	}
}
