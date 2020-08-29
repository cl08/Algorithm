package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D4_1251_하나로_김유창_prim {
	static double E;
	static int N;
	static PriorityQueue<Edge> q;

	static class Edge implements Comparable<Edge> {
		int v;
		double cost;

		public Edge() {
		}

		public Edge(int v, double cost) {
			super();
			this.v = v;
			this.cost = cost;
		}

		public int compareTo(Edge o) {
			return this.cost > o.cost ? 1 : -1;
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
			for (int i = 0; i < N; i++)
				x[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int i = 0; i < N; i++)
				y[i] = Integer.parseInt(st.nextToken());
			E = Double.parseDouble(br.readLine().trim());
			double[][] adj = new double[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					adj[i][j] = adj[j][i] = (Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2)) * E;
				}
			}
			q = new PriorityQueue<Edge>();
			boolean[] visit = new boolean[N];
			double[] dist = new double[N];
			q.offer(new Edge(0, 0));
			for (int i = 0; i < N; i++) {
				Edge temp = q.poll();
				int index = temp.v;
				if (visit[index]) {
					i--;
					continue;
				}
				visit[index] = true;
				dist[index] = temp.cost;
				for (int j = 0; j < N; j++) {
					if (!visit[j]) {
						q.offer(new Edge(j, adj[index][j]));
					}
				}
			}
			double result = 0;
			for (int i = 0; i < N; i++) {
				result = result + dist[i];
			}
			result = Math.round(result);
			sb.append('#').append(tc).append(' ').append((long) result).append('\n');
		}
		System.out.println(sb);
	}
}
