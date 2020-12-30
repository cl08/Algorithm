package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_G5_1753_최단경로 {

	public static class Edge implements Comparable<Edge> {
		int v;
		int dist;

		public Edge(int v, int dist) {
			super();
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return this.dist > o.dist ? 1 : -1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine()) - 1;
		ArrayList<Edge>[] list = null;
		for (int i = 0; i < V; i++)
			list[i] = new ArrayList<Edge>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list[Integer.parseInt(st.nextToken()) - 1]
					.add(new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
		}

		// 초기화
		int[] dist = new int[V];
		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[start] = 0;
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		q.offer(new Edge(start, dist[start]));

		while (!q.isEmpty()) {
			Edge minv = q.poll();
			int index = minv.v;

			// 최소값을 가진 노드의 인접 노드들 갱신
			for (int i = 0; i < list[index].size(); i++) {
				Edge temp = list[index].get(i);
				if (dist[temp.v] > dist[index] + temp.dist) {
					dist[temp.v] = dist[index] + temp.dist;
					q.offer(new Edge(temp.v, dist[temp.v]));
				}
			}
		}
		for (int i = 0; i < dist.length; i++) {
			if (dist[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}
	}
}
