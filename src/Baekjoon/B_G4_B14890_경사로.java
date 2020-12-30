package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_G4_B14890_경사로 {
	static int N, L, count = 0;
	static int map[][];
	static boolean ladder[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		ladder = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++)
			moveR(i, 0);
		for (int i = 0; i < N; i++)
			Arrays.fill(ladder[i], false);
		for (int i = 0; i < N; i++)
			moveC(0, i);

		System.out.println(count);
	}

	public static void moveR(int x, int y) {
		int tx = x;
		int ty = y;
		while (ty < N - 1) {
			// 경사가 같음
			if (ty + 1 < N && map[tx][ty] == map[tx][ty + 1]) {
				ty++;
			}

			// 경사가 높음
			else if (ty + 1 < N && map[tx][ty] + 1 == map[tx][ty + 1]) {
				for (int i = 0; i < L; i++) {
					if (ty - i >= 0 && map[tx][ty - i] == map[tx][ty - i] && !ladder[tx][ty - i])
						ladder[tx][ty - i] = true;
					else
						return;
				}
				ty++;
			}

			// 경사가 낮음
			else if (ty + 1 < N && map[tx][ty] == map[tx][ty + 1] + 1) {
				for (int i = 1; i <= L; i++) {
					if (ty + i < N && map[tx][ty + i] + 1 == map[tx][ty])
						ladder[tx][ty + i] = true;
					else
						return;
				}
				ty = ty + L;
			} else
				return;
			if (ty == N - 1)
				count++;
		}
	}

	public static void moveC(int x, int y) {
		int tx = x;
		int ty = y;
		while (tx < N - 1) {
			// 경사가 같음
			if (tx + 1 < N && map[tx][ty] == map[tx + 1][ty]) {
				tx++;
			}

			// 경사가 높음
			else if (tx + 1 < N && map[tx][ty] + 1 == map[tx + 1][ty]) {
				for (int i = 0; i < L; i++) {
					if (tx - i >= 0 && map[tx - i][ty] == map[tx - i][ty] && !ladder[tx - i][ty])
						ladder[tx - i][ty] = true;
					else
						return;
				}
				tx++;
			}

			// 경사가 낮음
			else if (tx + 1 < N && map[tx][ty] == map[tx + 1][ty] + 1) {
				for (int i = 1; i <= L; i++) {
					if (tx + i < N && map[tx + i][ty] + 1 == map[tx][ty])
						ladder[tx + i][ty] = true;
					else
						return;
				}
				tx = tx + L;
			} else
				return;
			if (tx == N - 1)
				count++;
		}
	}
}
