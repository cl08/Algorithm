package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D5_1907_모래성쌓기_김유창 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int H, W, count, result;
		int[][] map;
		boolean[][] visit;
		int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
		Queue<Point> q = new LinkedList<Point>();
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			visit = new boolean[H][W];
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					if (str.charAt(j) == '.')
						map[i][j] = 0;
					else
						map[i][j] = str.charAt(j) - '0';
				}
			}
			for (int i = 1; i < H - 1; i++) {
				for (int j = 1; j < W - 1; j++) {
					if (map[i][j] == 0)
						continue;
					count = 0;
					for (int k = 0; k < 8; k++) {
						if (map[i + dx[k]][j + dy[k]] == 0)
							count++;
					}
					if (map[i][j] <= count) {
						visit[i][j] = true;
						q.offer(new Point(i, j));
					}
				}
			}
			result = 1;
			int size, tx, ty;
			while (!q.isEmpty()) {
				size = q.size();
				while (size-- > 0) {
					Point temp = q.poll();
					map[temp.x][temp.y] = 0;
					for (int i = 0; i < 8; i++) {
						tx = temp.x + dx[i];
						ty = temp.y + dy[i];
						if (!visit[tx][ty] && map[tx][ty] != 0) {
							count = 0;
							for (int j = 0; j < 8; j++) {
								if (map[tx + dx[j]][ty + dy[j]] == 0)
									count++;
							}
							if (map[tx][ty] <= count) {
								visit[tx][ty] = true;
								q.offer(new Point(tx, ty));
							}
						}
					}
				}
				result++;
			}
			sb.append('#').append(tc).append(' ').append(result - 1).append('\n');
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