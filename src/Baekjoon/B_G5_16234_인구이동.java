package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_G5_16234_인구이동 {
	static int N, L, R, number;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[][] p;
	static int[][] map, union;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		union = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		flag = true; // 국경 개방 여부
		int count = 0;

		while (flag) {

			// 초기화
			flag = false;
			p = new int[2501][2];
			number = 0;
			for (int i = 0; i < N; i++)
				Arrays.fill(union[i], 0);

			// 인구 조사 & 국경 개방
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (union[i][j] == 0) {
						number++;
						dfs(i, j);
					}
				}
			}

			// 인구 이동
			if (flag) {
				boolean flag2 = false; // 인구 이동이 일어나는지 여부
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						int temp = union[i][j];
						if (map[i][j] != (p[temp][1] / p[temp][0])) {
							map[i][j] = p[temp][1] / p[temp][0];
							flag2 = true; // 인구이동이 일어남
						}
					}
				}
				if (flag2)
					count++;
				else
					flag = false;
			}

		}
		System.out.println(count);
	}

	public static void dfs(int x, int y) {
		union[x][y] = number;
		p[number][0]++;
		p[number][1] = p[number][1] + map[x][y];
		int tx, ty;
		for (int i = 0; i < 4; i++) {
			tx = x + dx[i];
			ty = y + dy[i];
			if (tx >= 0 && tx < N && ty >= 0 && ty < N && union[tx][ty] == 0) {
				int diff = Math.abs(map[x][y] - map[tx][ty]);
				if (L <= diff  && diff <= R) {
					flag = true; // 국경 개방
					dfs(tx, ty);
				}
			}
		}
	}
}
