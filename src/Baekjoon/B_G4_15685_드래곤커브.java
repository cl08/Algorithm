package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_G4_15685_드래곤커브 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		boolean[][] map = new boolean[101][101];
		int[] dx = { 0, -1, 0, 1 };
		int[] dy = { 1, 0, -1, 0 };
		int[][] pattern = new int[4][1024];
		pattern[0][0] = 0;
		pattern[1][0] = 1;
		pattern[2][0] = 2;
		pattern[3][0] = 3;
		for (int k = 0; k < 4; k++) {
			for (int i = 1; i <= 10; i++) {
				int start = (int) Math.pow(2, i - 1);
				int end = (int) Math.pow(2, i);
				for (int j = start, l = 1; j < end; j++, l += 2) {
					pattern[k][j] = pattern[k][j - l] + 1 == 4 ? 0 : pattern[k][j - l] + 1;
				}
			}
		}
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int end = (int) Math.pow(2, g);

			map[x][y] = true;
			for (int j = 0; j < end; j++) {
				x = x + dx[pattern[d][j]];
				y = y + dy[pattern[d][j]];
				map[x][y] = true;
			}
		}

		int count = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1])
					count++;
			}
		}
		System.out.println(count);
	}
}
