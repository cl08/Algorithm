package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_5987_달리기_김유창 {
	static int N, M, count;
	static int[] needs;
	static long[] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			needs = new int[N];
			memo = new long[1 << N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				needs[a] |= (1 << b);
			}
			long result = dfs(0);
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static long dfs(int flag) {
		if (flag == (1 << N) - 1) {
			return 1;
		}
		if (memo[flag] > 0) {
			return memo[flag];
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) == 0) {
				if ((flag & needs[i]) == needs[i]) {
					memo[flag] = memo[flag] + dfs(flag | 1 << i);
				}
			}
		}
		return memo[flag];
	}
}
