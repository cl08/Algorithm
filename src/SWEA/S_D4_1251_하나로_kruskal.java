package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S_D4_1251_하나로_kruskal {
	static double E;
	static int N;
	static int[] parents;
	static PriorityQueue<Distance> q;

	static class Distance implements Comparable<Distance> {
		int x;
		int y;
		double dis;

		public Distance() {
		}

		public Distance(int x, int y, double dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}

		public int compareTo(Distance o) {
			return this.dis > o.dis ? 1:-1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			int[] x = new int[N];
			int[] y = new int[N];
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(br.readLine().trim());
			q = new PriorityQueue<Distance>();
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					double temp = Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2);
					q.offer(new Distance(i, j, temp * E));
				}
			}
			parents = new int[N];
			makeSet();
			int count = 0;
			double result = 0;

			while (count != N - 1) {
				Distance temp = q.poll();
				int v1 = findSet(temp.x);
				int v2 = findSet(temp.y);
				if (v1 == v2)
					continue;
				result = result + temp.dis;
				union(v1, v2);
				count++;
			}
			result = Math.round(result);
			sb.append('#').append(tc).append(' ').append((long) result).append('\n');
		}
		System.out.println(sb);
	}

	private static void makeSet() {
		Arrays.fill(parents, -1);
	}

	private static int findSet(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = findSet(parents[a]); // path compression
	}

	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true; // 합치기 성공
		}
		return false; // 이미 같은 집합
	}
}
