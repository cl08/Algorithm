package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2105_디저트카페_김유창 {
	static int N, startX, startY, max;
	static int[] dx = { -1, 1, 1, -1 };
	static int[] dy = { 1, 1, -1, -1 };
	static int[][] arr;
	static boolean[] dessert = new boolean[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					Arrays.fill(dessert, false);
					startX = i;
					startY = j;
					dfs(i, j, 0, 1);
				}
			}
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs(int x, int y, int dir, int count) {
		dessert[arr[x][y]] = true;
		int tx, ty;
		for (int i = dir; i < 4; i++) {
			tx = x + dx[i];
			ty = y + dy[i];
			if (tx == startX && ty == startY && count > 3) {
				if (count > max) {
					max = count;
					return;
				}
			}
			if (tx >= 0 && tx < N && ty >= 0 && ty < N && !dessert[arr[tx][ty]])
				dfs(tx, ty, i, count + 1);
		}
		dessert[arr[x][y]] = false;
	}
}
