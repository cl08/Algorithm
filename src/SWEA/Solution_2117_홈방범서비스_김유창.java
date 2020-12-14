package SWEA;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2117_홈방범서비스_김유창 {
	static Queue<Point> q = new LinkedList<Point>();
	static int N, M, depth, count, result;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[] cost;
	static int[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visit = new boolean[N][N];
			cost = new int[N * N + 1];
			for (int i = 0; i < N * N; i++) {
				cost[i] = (i + 1) * (i + 1) + (i) * (i);
			}
			result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visitInit();
					q.clear();
					bfs(i, j);
				}
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static void visitInit() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visit[i], false);
		}
	}

	private static void bfs(int x, int y) {
		depth = 0;
		count = 0;
		visit[x][y] = true;
		q.offer(new Point(x, y));
		if (map[x][y] == 1)
			count++;
		if (cost[depth] <= count * M) {
			if (result < count) {
				result = count;
			}
		}
		while (!q.isEmpty()) {
			int size = q.size();
			depth++;
			while (size-- > 0) {
				Point temp = q.poll();
				int tx, ty;
				for (int i = 0; i < 4; i++) {
					tx = temp.x + dx[i];
					ty = temp.y + dy[i];
					if (tx >= 0 && tx < N && ty >= 0 && ty < N && !visit[tx][ty]) {
						visit[tx][ty] = true;
						q.offer(new Point(tx, ty));
						if (map[tx][ty] == 1)
							count++;
					}
				}
			}
			if (cost[depth] <= count * M && result < count) {
				result = count;
			}
		}
	}
}
