package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G5_B14499_주사위굴리기_김유창 {
	static int N, M, x, y, K;
	static int[][] map;
	static int[] order;
	static int[] dice = { 0, 0, 0, 0, 0, 0 };
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		order = new int[K];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < K; i++) {
			x = x + dx[order[i]];
			y = y + dy[order[i]];
			if (x >= 0 && x < N && y >= 0 && y < M) {
				// 주사위갱신
				dice(order[i]);
				if (map[x][y] == 0) {
					map[x][y] = dice[1];
				} else {
					dice[1] = map[x][y];
					map[x][y] = 0;
				}
				System.out.println(dice[3]);
			} else {
				x = x - dx[order[i]];
				y = y - dy[order[i]];
			}
		}
	}

	public static void dice(int index) {
		int temp;
		switch (index) {
		case 1: {
			temp = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[3];
			dice[3] = dice[4];
			dice[4] = temp;
			break;
		}
		case 2: {
			temp = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[3];
			dice[3] = dice[5];
			dice[5] = temp;
			break;
		}
		case 3: {
			temp = dice[1];
			dice[1] = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[2];
			dice[2] =temp;
			break;
		}
		case 4: {
			temp = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[3];
			dice[3] = dice[0];
			dice[0] = temp;
			break;
		}
		}
	}
}
