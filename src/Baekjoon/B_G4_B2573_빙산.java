package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_G4_B2573_빙산 {

	static int N, M, piece, result;
	static int[][] map, copy;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		// 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = 0;
		piece = 1;

		while (piece == 1) {
			result++;

			// 녹을 빙산의 높이를 저장할 배열
			copy = new int[N][M];

			// 녹을 빙산의 높이 구하기
			for (int i = 1; i < N; i++) {
				for (int j = 1; j < M; j++) {
					if (map[i][j] > 0) {
						for (int k = 0; k < 4; k++) {
							if (map[i + dx[k]][j + dy[k]] == 0)
								copy[i][j]++;
						}
					}
				}
			}

			// 빙산 녹음
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = map[i][j] - copy[i][j];
					if (map[i][j] < 0)
						map[i][j] = 0;
				}
			}

			// 두 덩이로 나뉘어 졌는지 확인
			visit = new boolean[N][M];
			piece = 0;
			L: for (int i = 1; i < N; i++) {
				for (int j = 1; j < M; j++) {
					if (map[i][j] > 0 && !visit[i][j]) {
						piece++;
						// 빙산이 두 덩이면 되면 탐색을 멈춤
						if (piece == 2)
							break L;
						dfs(i, j);
					}
				}
			}
		}
		if (piece == 0)
			System.out.println(0);
		else
			System.out.println(result);
	}

	public static void dfs(int x, int y) {
		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			if (tx >= 0 && tx < N && ty >= 0 && ty < M && map[tx][ty] > 0 && !visit[tx][ty]) {
				dfs(tx, ty);
			}
		}
	}
}
