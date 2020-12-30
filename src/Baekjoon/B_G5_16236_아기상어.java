package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_G5_16236_아기상어 {

	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int x = 0, y = 0, size = 2; // 아기상어 정보
		int sizeCount = 0;
		int time = 0;
		int fish = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					x = i;
					y = j;
				} else if (map[i][j] > 0)
					fish++;
			}
		}
		boolean flag = true;
		L: while (flag && fish != 0) {
			// 가까운 먹이 탐색
			PriorityQueue<Point> q = new PriorityQueue<Point>();
			boolean[][] visit = new boolean[N][N];
			q.offer(new Point(x, y, 0));
			visit[x][y] = true;
			int dist = 0;
			while (!q.isEmpty()) {
				int qSize = q.size();
				dist++;
				while (qSize-- > 0) {
					Point temp = q.poll();
					if (map[temp.x][temp.y] > 0 && map[temp.x][temp.y] < size && map[temp.x][temp.y] != 9) {
						// 먹이 발견
						map[x][y] = 0;
						x = temp.x;
						y = temp.y;
						map[x][y] = 9;
						time = time + dist - 1;
						fish--;
						sizeCount++;
						if (sizeCount == size) {
							size++;
							sizeCount = 0;
						}
						// print(N, map, size);
						continue L;
					}
					for (int i = 0; i < 4; i++) {
						int tx = temp.x + dx[i];
						int ty = temp.y + dy[i];
						if (tx >= 0 && tx < N && ty >= 0 && ty < N && !visit[tx][ty] && map[tx][ty] <= size) {
							visit[tx][ty] = true;
							q.offer(new Point(tx, ty, dist));
						}
					}
				}
			}
			flag = false;
		}
		System.out.println(time);
	}

	public static class Point implements Comparable<Point> {
		int x;
		int y;
		int dist;

		public Point(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Point o) {
			if (this.dist == o.dist)
				if (this.x == o.x)
					return this.y > o.y ? 1 : -1;
				else
					return this.x > o.x ? 1 : -1;
			return this.dist > o.dist ? 1 : -1;
		}
	}

	public static void print(int N, int[][] map, int size) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(size);
		System.out.println();
	}
}
