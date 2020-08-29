package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D5_7396_종구의딸이름짓기_김유창 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int[] dx = { 0, 1 };
		int[] dy = { 1, 0 };
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			char[][] map = new char[N][M];
			boolean[][] visit = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				map[i] = str.toCharArray();
			}

			// BFS 로 알파벳을 넣으면서 알파벳 우선순위가 가장 높은 알파벳만 뽑음
			PriorityQueue<Point> q = new PriorityQueue<Point>();

			// PriorityQueue 에 들어간 알파벳들 중에 동일한 알파벳이 있을때 처리하기 위한 SubQueue
			Queue<Point> subQ = new LinkedList<Point>();
			q.offer(new Point(map[0][0], 0, 0));
			while (!q.isEmpty()) {
				Point p = q.poll();
				sb.append(p.c);
				subQ.offer(p);
				int size, tx, ty;
				Point temp;
				size = q.size();
				for (int i = 0; i < size; i++) {
					temp = q.poll();
					if (p.c == temp.c)
						subQ.offer(temp);
					else {
						q.clear();
						break;
					}
				}
				size = subQ.size();
				for (int i = 0; i < size; i++) {
					temp = subQ.poll();
					for (int j = 0; j < 2; j++) {
						tx = temp.x + dx[j];
						ty = temp.y + dy[j];
						if (tx < N && ty < M && !visit[tx][ty]) {
							visit[tx][ty] = true;
							q.offer(new Point(map[tx][ty], tx, ty));
						}
					}
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	public static class Point implements Comparable<Point> {
		char c;
		int x;
		int y;

		Point(char c, int x, int y) {
			this.c = c;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point p) {
			return this.c - p.c;
		}
	}
}
