package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_S3_2579_계단오르기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] score = new int[n + 1];
		int[] dp = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		dp[0] = 0;
		dp[1] = score[1];
		if (n >= 2)
			dp[2] = dp[1] + score[2];
		for (int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i - 3] + score[i - 1] + score[i], dp[i - 2] + score[i]);
		}
		System.out.println(dp[n]);
	}
}
