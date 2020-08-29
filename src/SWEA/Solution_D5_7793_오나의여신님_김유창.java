package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D5_7793_오나의여신님_김유창 {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { 1, 0, -1, 0 };
		int T = Integer.parseInt(br.readLine());
		L: for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			char[][] map = new char[N][M];
			// 수연이의 이동 경로가 들어갈 큐
			Queue<Point> sy = new LinkedList<Point>();
			// 악마의 손아귀 확산 경로가 들어갈 큐
			Queue<Point> dv = new LinkedList<Point>();
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 'S') {
						sy.offer(new Point(i, j));
					} else if (map[i][j] == '*') {
						dv.offer(new Point(i, j));
					}
				}
			}
			int count = 0;
			while (!sy.isEmpty()) {
				count++;
				// BFS로 악마의 손길 확산. 확산하면서 수연이나 빈칸을 영역으로 만듦
				int size = dv.size();
				while (size-- > 0) {
					Point temp = dv.poll();
					for (int i = 0; i < 4; i++) {
						int tx = temp.x + dx[i];
						int ty = temp.y + dy[i];
						if (tx >= 0 && tx < N && ty >= 0 && ty < M) {
							if (map[tx][ty] == '.' || map[tx][ty] == 'S') {
								map[tx][ty] = '*';
								dv.offer(new Point(tx, ty));
							}
						}
					}
				}
				// BFS 수연이 이동
				size = sy.size();
				while (size-- > 0) {
					Point temp = sy.poll();
					for (int i = 0; i < 4; i++) {
						int tx = temp.x + dx[i];
						int ty = temp.y + dy[i];
						if (tx >= 0 && tx < N && ty >= 0 && ty < M) {
							if (map[tx][ty] == 'D') {
								sb.append('#').append(tc).append(' ').append(count).append('\n');
								continue L;
							}
							if (map[tx][ty] == '.') {
								map[tx][ty] = 'S';
								sy.offer(new Point(tx, ty));
							}
						}
					}
				}

			}
			sb.append('#').append(tc).append(' ').append("GAME OVER").append('\n');
		}
		System.out.println(sb);
	}

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
