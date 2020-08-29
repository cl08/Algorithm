package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G5_2636_치즈_김유창 {
	static int N, M;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static boolean[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				if (Integer.parseInt(st.nextToken()) == 1)
					map[i][j] = true;
			}
		}
		int cheese = 0;

		// 초기 치즈가 몇덩이 인지 체크
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (map[i][j] == true)
					cheese++;

		int time = 0;
		int save = 0;

		// 치즈가 모두 사라질때 까지 반복
		while (cheese != 0) {
			time++;

			// 치즈 덩이 수 백업
			save = cheese;

			// 치즈 녹이기
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					visit[i][j] = false;
			dfs(0, 0);

			// 치즈 몇덩이 남아있는지 체크
			cheese = 0;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					if (map[i][j] == true)
						cheese++;
		}
		System.out.println(time);
		System.out.println(save);
	}

	public static void dfs(int x, int y) {
		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			if (tx >= 0 && tx < N && ty >= 0 && ty < M && !visit[tx][ty] && map[tx][ty]) {
				map[tx][ty] = false;
				visit[tx][ty] = true;
			}
		}
		for (int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			if (tx >= 0 && tx < N && ty >= 0 && ty < M && !visit[tx][ty] && !map[tx][ty])
				dfs(tx, ty);
		}
	}
}
