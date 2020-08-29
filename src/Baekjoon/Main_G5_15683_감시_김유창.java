package Baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_G5_15683_감시_김유창 {
	static int N, M, min;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[][] map;
	static boolean[][] visit;
	static boolean[][][] copy;
	static ArrayList<Point> list = new ArrayList<Point>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6) {
					list.add(new Point(i, j));
				}
			}
		}
		copy = new boolean[list.size()][N][M];
		min = Integer.MAX_VALUE;
		dfs(0);
		System.out.println(min);
	}

	public static void dfs(int count) {
		// 종료 조건
		if (count == list.size()) {
			// 사각지대 갯수 체크
			int blindSpot = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0 && !visit[i][j])
						blindSpot++;
				}
			}
			if (blindSpot < min)
				min = blindSpot;
			return;
		}

		Point cctv = list.get(count);
		// 방향 돌려가며 cctv on/off
		int tx, ty;
		switch (map[cctv.x][cctv.y]) {
		case 1:
			// visit 복사
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copy[count][i][j] = visit[i][j];
				}
			}
			for (int i = 0; i < 4; i++) {
				// i방향 cctv on
				tx = cctv.x + dx[i];
				ty = cctv.y + dy[i];
				while (tx >= 0 && tx < N && ty >= 0 && ty < M && map[tx][ty] != 6) {
					visit[tx][ty] = true;
					tx = tx + dx[i];
					ty = ty + dy[i];
				}
				dfs(count + 1);
				// visit 복구
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						visit[j][k] = copy[count][j][k];
					}
				}
			}
			break;
		case 2:
			// visit 복사
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copy[count][i][j] = visit[i][j];
				}
			}
			for (int i = 0; i < 2; i++) {
				// i방향 cctv on
				tx = cctv.x + dx[i];
				ty = cctv.y + dy[i];
				while (tx >= 0 && tx < N && ty >= 0 && ty < M && map[tx][ty] != 6) {
					visit[tx][ty] = true;
					tx = tx + dx[i];
					ty = ty + dy[i];
				}
				tx = cctv.x + dx[i + 2];
				ty = cctv.y + dy[i + 2];
				while (tx >= 0 && tx < N && ty >= 0 && ty < M && map[tx][ty] != 6) {
					visit[tx][ty] = true;
					tx = tx + dx[i + 2];
					ty = ty + dy[i + 2];
				}
				dfs(count + 1);
				// visit 복구
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						visit[j][k] = copy[count][j][k];
					}
				}
			}
			break;
		case 3:
			// visit 복사
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copy[count][i][j] = visit[i][j];
				}
			}
			for (int i = 0; i < 4; i++) {
				// i방향 cctv on
				tx = cctv.x + dx[i];
				ty = cctv.y + dy[i];
				while (tx >= 0 && tx < N && ty >= 0 && ty < M && map[tx][ty] != 6) {
					visit[tx][ty] = true;
					tx = tx + dx[i];
					ty = ty + dy[i];
				}
				tx = cctv.x + dx[(i + 1) % 4];
				ty = cctv.y + dy[(i + 1) % 4];
				while (tx >= 0 && tx < N && ty >= 0 && ty < M && map[tx][ty] != 6) {
					visit[tx][ty] = true;
					tx = tx + dx[(i + 1) % 4];
					ty = ty + dy[(i + 1) % 4];
				}
				dfs(count + 1);
				// visit 복구
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						visit[j][k] = copy[count][j][k];
					}
				}
			}
			break;
		case 4:
			// visit 복사
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copy[count][i][j] = visit[i][j];
				}
			}
			for (int i = 0; i < 4; i++) {
				// i방향 cctv on
				tx = cctv.x + dx[i];
				ty = cctv.y + dy[i];
				while (tx >= 0 && tx < N && ty >= 0 && ty < M && map[tx][ty] != 6) {
					visit[tx][ty] = true;
					tx = tx + dx[i];
					ty = ty + dy[i];
				}
				tx = cctv.x + dx[(i + 1) % 4];
				ty = cctv.y + dy[(i + 1) % 4];
				while (tx >= 0 && tx < N && ty >= 0 && ty < M && map[tx][ty] != 6) {
					visit[tx][ty] = true;
					tx = tx + dx[(i + 1) % 4];
					ty = ty + dy[(i + 1) % 4];
				}
				tx = cctv.x + dx[(i + 2) % 4];
				ty = cctv.y + dy[(i + 2) % 4];
				while (tx >= 0 && tx < N && ty >= 0 && ty < M && map[tx][ty] != 6) {
					visit[tx][ty] = true;
					tx = tx + dx[(i + 2) % 4];
					ty = ty + dy[(i + 2) % 4];
				}
				dfs(count + 1);
				// visit 복구
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						visit[j][k] = copy[count][j][k];
					}
				}
			}
			break;
		case 5:
			// visit 복사
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copy[count][i][j] = visit[i][j];
				}
			}
			for (int i = 0; i < 4; i++) {
				tx = cctv.x + dx[i];
				ty = cctv.y + dy[i];
				while (tx >= 0 && tx < N && ty >= 0 && ty < M && map[tx][ty] != 6) {
					visit[tx][ty] = true;
					tx = tx + dx[i];
					ty = ty + dy[i];
				}
			}
			dfs(count + 1);
			// i방향 cctv off
			// visit 복구
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					visit[j][k] = copy[count][j][k];
				}
			}
			break;
		}
	}
}
