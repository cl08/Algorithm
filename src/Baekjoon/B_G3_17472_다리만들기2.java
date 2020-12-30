package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_G3_17472_다리만들기2 {
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬마다 번호 붙이기
		visit = new boolean[N][M];
		int num = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j] && map[i][j] != 0) {
					num++;
					dfs(i, j, num);
				}
			}
		}

		// 섬의 간선 정보 저장할 배열
		arr = new int[num][num];
		for (int i = 0; i < num; i++) {
			Arrays.fill(arr[i], Integer.MAX_VALUE);
		}

		// 각 섬의 최단 거리를 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					int base = map[i][j];
					for (int k = 0; k < 4; k++) {
						for (int l = 1;; l++) {
							int tx = i + dx[k] * l;
							int ty = j + dy[k] * l;
							if (tx >= 0 && tx < N && ty >= 0 && ty < M) {
								if (map[tx][ty] == 0)
									continue;
								else if (map[tx][ty] == base)
									break;
								else {
									if (l > 2) {
										arr[base - 1][map[tx][ty] - 1] = arr[map[tx][ty] - 1][base - 1] = Math
												.min(arr[base - 1][map[tx][ty] - 1], l - 1);
									}
									break;
								}
							} else
								break;
						}
					}
				}
			}
		}

		// 프림
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		boolean[] check = new boolean[num];
		check[0] = true;
		for (int i = 0; i < num; i++) {
			if (arr[0][i] != Integer.MAX_VALUE) {
				q.offer(new Vertex(i, arr[0][i]));
			}
		}
		int sum = 0;
		int count = 1;
		while (!q.isEmpty()) {
			Vertex temp = q.poll();
			if (!check[temp.v]) {
				count++;
				check[temp.v] = true;
				sum = sum + temp.cost;
				for (int i = 0; i < num; i++) {
					if (!check[i] && arr[temp.v][i] != Integer.MAX_VALUE) {
						q.offer(new Vertex(i, arr[temp.v][i]));
					}
				}
			}
		}
		if (count == num)
			System.out.println(sum);
		else
			System.out.println(-1);
	}

	public static void dfs(int x, int y, int num) {
		visit[x][y] = true;
		map[x][y] = num;
		for (int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			if (tx >= 0 && tx < N && ty >= 0 && ty < M && !visit[tx][ty] && map[tx][ty] != 0) {
				dfs(tx, ty, num);
			}
		}
	}

	public static class Vertex implements Comparable<Vertex> {
		int v;
		int cost;

		public Vertex(int v, int cost) {
			super();
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.cost - o.cost;
		}
	}
}
