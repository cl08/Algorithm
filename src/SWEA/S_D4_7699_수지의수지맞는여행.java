package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_D4_7699_수지의수지맞는여행 {
	static int R, C, max;
	static int[][] map;
	static String str;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static boolean[] alphabet = new boolean[26];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[R][C];
			for (int i = 0; i < R; i++) {
				str = br.readLine();
				for(int j=0; j<C; j++) {
					map[i][j] = str.charAt(j)-'A';
				}
			}
			max = 0;
			dfs(0, 0, 1);
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs(int x, int y, int count) {
		alphabet[map[x][y]] = true;
		if (count > max)
			max = count;
		for (int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			if (tx >= 0 && tx < R && ty >= 0 && ty < C) {
				if (!alphabet[map[tx][ty]])
					dfs(tx, ty, count + 1);
			}
		}
		alphabet[map[x][y]] = false;
	}
}
