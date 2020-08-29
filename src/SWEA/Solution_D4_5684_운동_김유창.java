package SWEA;

import java.io.IOException;
import java.util.Scanner;

public class Solution_D4_5684_운동_김유창 {
	static int N, M, start, min;
	static int[][] arr;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N =sc.nextInt()+1;
			M =sc.nextInt();
			arr = new int[N][N];
			visit = new boolean[N];
			for (int i = 0; i < M; i++) {
				arr[sc.nextInt()][sc.nextInt()] = sc.nextInt();
			}
			min = Integer.MAX_VALUE;
			for (int i = 1; i < N; i++) {
				start = i;
				dfs(i, 0);
			}
			if(min == Integer.MAX_VALUE)
				min = -1;
			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		sc.close();
		System.out.println(sb);
	}

	public static void dfs(int index, int dis) {
		if (dis >= min)
			return;
		if (index == start && visit[start]) {
			if (dis < min)
				min = dis;
			return;
		}
		for (int i = 1; i < N; i++) {
			if (arr[index][i] != 0 && !visit[i]) {
				visit[i] = true;
				dfs(i, dis + arr[index][i]);
				visit[i] = false;
			}
		}
	}
}
