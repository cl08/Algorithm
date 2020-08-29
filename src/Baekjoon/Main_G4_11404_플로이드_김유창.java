package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G4_11404_플로이드_김유창 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[][] dp = new int[n][n];
		final int max = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], max);
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int price = Integer.parseInt(st.nextToken());
			if (dp[start][end] > price)
				dp[start][end] = price;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;
				for (int k = 0; k < n; k++) {
					if (j == k || i == k)
						continue;
					if (dp[j][i] != max && dp[i][k] != max && dp[j][i] + dp[i][k] < dp[j][k])
						dp[j][k] = dp[j][i] + dp[i][k];
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (dp[i][j] == max)
					System.out.print(0 + " ");
				else
					System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}
}
