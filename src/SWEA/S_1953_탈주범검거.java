package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S_1953_탈주범검거 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int[][][][] dir = { {},
				{ { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } },
						{ { 1, 2, 4, 7 }, { 1, 3, 6, 7 }, { 1, 2, 5, 6 }, { 1, 3, 4, 5 } } },
				{ { { 1, 0 }, { -1, 0 } }, { { 1, 2, 4, 7 }, { 1, 2, 5, 6 } } },
				{ { { 0, 1 }, { 0, -1 } }, { { 1, 3, 6, 7 }, { 1, 3, 4, 5 } } },
				{ { { -1, 0 }, { 0, 1 } }, { { 1, 2, 5, 6 }, { 1, 3, 6, 7 } } },
				{ { { 1, 0 }, { 0, 1 } }, { { 1, 2, 4, 7 }, { 1, 3, 6, 7 } } },
				{ { { 0, -1 }, { 1, 0 } }, { { 1, 3, 4, 5 }, { 1, 2, 4, 7 } } },
				{ { { -1, 0 }, { 0, -1 } }, { { 1, 2, 5, 6 }, { 1, 3, 4, 5 } } } };

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][M];
			boolean[][] visit = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int count = 1;
			int time = 0;
			Queue<Point> q = new LinkedList<Point>();
			visit[R][C] = true;
			q.offer(new Point(map[R][C], R, C));
			while (time < L - 1) {
				time++;
				int size = q.size();
				while (size-- > 0) {
					Point temp = q.poll();
					L: for (int i = 0; i < dir[temp.pipe][0].length; i++) {
						int tx = temp.x + dir[temp.pipe][0][i][0];
						int ty = temp.y + dir[temp.pipe][0][i][1];
						if (tx >= 0 && tx < N && ty >= 0 && ty < M && !visit[tx][ty] && map[tx][ty] != 0) {
							for (int k = 0; k < dir[temp.pipe][1][i].length; k++) {
								if (map[tx][ty] == dir[temp.pipe][1][i][k]) {
									visit[tx][ty] = true;
									q.offer(new Point(map[tx][ty], tx, ty));
									count++;
									continue L;
								}
							}
						}
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}

	public static class Point {
		int pipe;
		int x;
		int y;

		public Point(int pipe, int x, int y) {
			this.pipe = pipe;
			this.x = x;
			this.y = y;
		}
	}
}
