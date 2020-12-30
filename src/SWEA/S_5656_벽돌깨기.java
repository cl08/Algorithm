package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_5656_벽돌깨기 {
	static int N, C, R, min;
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			map = new int[R + 1][C];
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 0)
						map[R][j]++;
				}
			}
			min = Integer.MAX_VALUE;
			drop(0);
			if (min == Integer.MAX_VALUE) {
				min = 0;
			}
			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		System.out.println(sb);
	}

	public static void drop(int n) {
		// 종료 조건(구슬을 N번 떨어뜨림)
		if (n == N) {
			// 남은 구슬 세기
			int count = 0;
			for (int i = 0; i < C; i++) {
				count=count+map[R][i];
			}
			if (count < min)
				min = count;
			return;
		}
		// i번째 열에 구슬 드랍
		for (int i = 0; i < C; i++) {
			// 가지치기
			if (map[R][i] == 0)
				continue;
			for (int j = 0; j < R; j++) {
				if (map[j][i] > 0) {
					// 맵 백업
					int[][] copy = new int[R+1][C];
					for (int k = 0; k < R+1; k++) {
						for (int l = 0; l < C; l++) {
							copy[k][l] = map[k][l];
						}
					}
					bomb(j, i);
					f5();
					drop(n + 1);
					// 맵 복구
					for (int k = 0; k < R+1; k++) {
						for (int l = 0; l < C; l++) {
							map[k][l] = copy[k][l];
						}
					}
					break;
				}
			}
		}

	}

	public static void bomb(int x, int y) {
		// x,y 지점부터 연쇄폭발
		int temp = map[x][y];
		if (temp > 0) {
			map[x][y] = 0;
			map[R][y]--;
		}
		if (temp == 1)
			return;
		for (int i = 1; i < temp; i++) {
			for (int j = 0; j < 4; j++) {
				int tx = x + dx[j] * i;
				int ty = y + dy[j] * i;
				if (tx >= 0 && tx < R && ty >= 0 && ty < C)
					bomb(tx, ty);
			}
		}
	}

	public static void f5() {
		for (int i = 0; i < C; i++) {
			// 가치지기
			if (map[R][i] == 0)
				continue;
			for (int j = R - 1; j > 0; j--) {
				if (map[j][i] > 0)
					continue;
				else {
					int k = 1;
					while (map[j - k][i] == 0 && j - k != 0) {
						k++;
					}
					map[j][i] = map[j - k][i];
					map[j - k][i] = 0;
				}
			}
		}
	}
}
