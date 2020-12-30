package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_B1_2839_소금배달 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine())+1;
		int[] memo = new int[n];
		for (int i = 0; i < n; i++) {
			if (i % 3 == 0)
				memo[i] = i / 3;
			else
				memo[i] = Integer.MAX_VALUE;
		}
		for (int i = 5; i < n; i++) {
			if(memo[i-5] != Integer.MAX_VALUE && memo[i] > memo[i-5]+1)
				memo[i] = memo[i-5]+1;
		}
		System.out.println(memo[n-1] == Integer.MAX_VALUE ? -1 : memo[n-1]);
	}
}