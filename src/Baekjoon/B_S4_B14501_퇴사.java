package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_S4_B14501_퇴사 {

	static int[] pay;
	static int[] day;
	static int N, max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		pay = new int[N];
		day = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			day[i] = Integer.parseInt(st.nextToken());
			pay[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0);
		System.out.println(max);
	}

	public static void dfs(int start, int sum) {
		if (start > N)
			return;
		if (sum > max)
			max = sum;
		for (int i = start; i < N; i++) {
			dfs(i + day[i], sum + pay[i]);
		}
	}
}
