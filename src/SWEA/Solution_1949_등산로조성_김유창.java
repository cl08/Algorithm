package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1949_등산로조성_김유창 {
	static int N, K, max, start;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visit = new boolean[N][N];
			start = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > start)
						start = map[i][j];
				}
			}
			max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == start)
						dfs(i, j, 1);
				}
			}
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs(int x, int y, int count) {
		if (count > max)
			max = count;
		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			if (tx >= 0 && tx < N && ty >= 0 && ty < N && !visit[tx][ty]) {
				if (!flag && map[x][y] <= map[tx][ty]) {
						if (map[x][y] > map[tx][ty] - K) {
							flag = true;
							int temp = map[tx][ty];
							map[tx][ty] = map[x][y] - 1;
							dfs(tx, ty, count + 1);
							map[tx][ty] = temp;
							flag = false;
						}
				}
				if (map[x][y] > map[tx][ty])
					dfs(tx, ty, count + 1);
			}
		}
		visit[x][y] = false;
	}
}
