package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_D5_1247_최적경로 {
	static int N, min;
	static int[][] loc;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			loc = new int[N + 2][2];
			visit = new boolean[N + 2];
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N + 2; i++) {
				loc[i][0] = Integer.parseInt(st.nextToken());
				loc[i][1] = Integer.parseInt(st.nextToken());
			}
			dfs(0, 0, 0);
			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs(int index, int dis, int count) {
		// 가지치기
		if (dis > min)
			return;

		if (count == N) {
			dis = dis + Math.abs(loc[index][0] - loc[1][0]) + Math.abs(loc[index][1] - loc[1][1]);
			if (min > dis)
				min = dis;
			return;
		}
		for (int i = 2; i < N + 2; i++) {
			if (!visit[i]) {
				visit[i] = true;
				dfs(i, dis + Math.abs(loc[index][0] - loc[i][0]) + Math.abs(loc[index][1] - loc[i][1]), count + 1);
				visit[i] = false;
			}
		}
	}
}
