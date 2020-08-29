package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G5_B14502_연구소_김유창 {
	static int N, M, tx, ty, safeZone, max = 0;
	static int[][] map, copy;
	static boolean[][] visit;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copy = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copy[i][j] = map[i][j];
			}
		}
		// 벽 세우는 경우의 수
		wall(0, 0, 0);
		System.out.println(max);
	}

	public static void wall(int x, int y, int count) {
		// count : 세운 벽의 수
		if (count == 3) {
			// 맵 복사
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copy[i][j] = map[i][j];
				}
			}
			
			// 바이러스 확산
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 2 && !visit[i][j]) {
						dfs(i, j);
					}
				}
			}
			
			// 안전영역 세기
			safeZone = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0) {
						safeZone++;
					}
				}
			}
			if (max < safeZone)
				max = safeZone;
			
			// 맵 복구
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = copy[i][j];
				}
			}
			for(int i=0; i<N; i++) {
				Arrays.fill(visit[i],false);
			}
			return;
		}
		
		for (int i = y; i < M; i++) {
			if (map[x][i] == 0) {
				map[x][i] = 1;
				wall(x, i, count + 1);
				map[x][i] = 0;
			}
		}
		for (int i = x + 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					wall(i, j, count + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	public static void dfs(int x, int y) {
		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			tx = x + dx[i];
			ty = y + dy[i];
			if (tx >= 0 && tx < N && ty >= 0 && ty < M) {
				if (map[tx][ty] == 0) {
					map[tx][ty] = 2;
					dfs(tx, ty);
				}
			}
		}
	}
}
