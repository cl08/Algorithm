package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class a_ing_B_G1_1194_달이차오른다가자 {
	static int N, M;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static boolean[][][] visit;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new boolean[N][M][1 << 6];
		Point start = null;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '0')
					start = new Point(i, j, 0, 0);
			}
		}
		int result = bfs(start);
		System.out.println(result);
	}

	static class Point {
		int x;
		int y;
		int key;
		int count;

		public Point(int x, int y, int key, int count) {
			this.x = x;
			this.y = y;
			this.key = key;
			this.count = count;
		}

	}

	public static int bfs(Point start) {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(start);
		visit[start.x][start.y][0] = true;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Point temp = q.poll();
				if (map[temp.x][temp.y] == '1') {
					return temp.count;
				}
				for (int i = 0; i < 4; i++) {
					int tx = temp.x + dx[i];
					int ty = temp.y + dy[i];
					int key = temp.key;
					if (tx >= 0 && tx < N && ty >= 0 && ty < M && !visit[tx][ty][key] && map[tx][ty] != '#') {
						// 키가 있으면 키 획득
						if ('a' <= map[tx][ty] && map[tx][ty] <= 'f') {
							// key = key | (1 << (map[tx][ty] - 'a'));
							key |= (1 << (map[tx][ty] - 'a'));
						}

						// 문인데 키가 없는 경우
						if ('A' <= map[tx][ty] && map[tx][ty] <= 'F') {
							if ((key & (1 << (map[tx][ty] - 'A'))) == 0) {
								continue;
							}
						}

						visit[tx][ty][key] = true;
						q.offer(new Point(tx, ty, key, temp.count + 1));
					}
				}
			}
		}
		return -1;
	}
}
