package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S_D4_1251_하나로 {
	static int N, count;
	static double E;
	static int[][] L;
	static double[][] dp;
	static boolean[] visit;
	static ArrayList<Integer> al = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			L = new int[N][2];
			visit = new boolean[N];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					L[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			E = Double.parseDouble(br.readLine());
			dp = new double[N][N];
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					dp[i][j] = dp[j][i] = Math.pow((L[i][0] - L[j][0]),2)+Math.pow((L[i][1] - L[j][1]),2);
				}
			}
			visit[0] = true;
			count = 1;
			long result = (long) Math.round(prim() * E);
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static double prim() {
		if (count == N) {
			return 0;
		}
		double min = Double.MAX_VALUE;
		int x = 0, y = 0;
		for (int i = 0; i < N; i++) {
			if (visit[i]) {
				for (int j = 0; j < N; j++) {
					if (!visit[j] && dp[i][j] != 0 && dp[i][j] < min) {
						min = dp[i][j];
						x = i;
						y = j;
					}
				}
			}
		}
		visit[y] = true;
		dp[x][y] = Double.MAX_VALUE;
		count++;
		return min + prim();
	}
}
