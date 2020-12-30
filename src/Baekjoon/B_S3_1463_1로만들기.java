package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_S3_1463_1로만들기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(br.readLine());
		int[] dp;
		if (x < 3)
			dp = new int[4];
		else
			dp = new int[x + 1];
		Arrays.fill(dp,Integer.MAX_VALUE);
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		
		for (int i = 4; i <= x; i++) {
			if (i % 3 == 0 && dp[i / 3] + 1 < dp[i])
				dp[i] = dp[i / 3] + 1;
			if (i % 2 == 0 && dp[i / 2] + 1 < dp[i])
				dp[i] = dp[i / 2] + 1;
			if (dp[i - 1] + 1 < dp[i])
				dp[i] = dp[i - 1] + 1;
		}
		System.out.println(dp[x]);
	}
}
