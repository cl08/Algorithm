package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_G3_2146_다리만들기 {
	static int N;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[][] map;
	static boolean[][] visit;
	static Queue<Point> q;
	static ArrayList<Point> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int index = 0;
		list = new ArrayList<Point>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j] && map[i][j] == 1) {
					index++;
					dfs(i, j, index);
				}
			}
		}
		int min = (N - 1) * 2;
		for (Point p : list) {
			q = new LinkedList<Point>();
			visit = new boolean[N][N];
			visit[p.x][p.y] = true;
			q.offer(p);
			int start = p.num;
			int dist = 0;
			L:while (!q.isEmpty()) {
				int size = q.size();
				dist++;
				if (dist >= min) {
					break;
				}
				while (size-- > 0) {
					Point temp = q.poll();
					// 여기서부터 최단 거리 찾아서
					for (int i = 0; i < 4; i++) {
						int tx = temp.x + dx[i];
						int ty = temp.y + dy[i];
						if (tx >= 0 && tx < N && ty >= 0 && ty < N && !visit[tx][ty]) {
							if (map[tx][ty] == start)
								continue;
							else if (map[tx][ty] == 0) {
								visit[tx][ty] = true;
								q.offer(new Point(tx, ty, start));
							}
							// 다른 섬 발견
							else {
								if (dist < min) {
									min = dist;
									break L;
								}
							}
						}
					}
				}
			}
		}

		System.out.println(min);
	}

	public static void dfs(int x, int y, int index) {
		map[x][y] = index;
		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			if (tx >= 0 && tx < N && ty >= 0 && ty < N && !visit[tx][ty]) {
				if (map[tx][ty] == 1)
					dfs(tx, ty, index);
				else if (map[tx][ty] == 0) {
					visit[tx][ty] = true;
					list.add(new Point(tx, ty, map[x][y]));
				}
			}
		}
	}

	public static class Point {
		public Point(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}

		int x;
		int y;
		int num;
	}
}
