package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_G3_1238_파티_김유창 {

	static int N, M, X;
	static Node[] adjList;
	static Node[] radjList;

	static class Node {
		int no, weight;
		Node next;

		public Node(int no, int weight, Node next) {
			this.no = no;
			this.weight = weight;
			this.next = next;
		}
	}

	static class Point implements Comparable<Point> {
		int v;
		int time;

		public Point(int v, int time) {
			super();
			this.v = v;
			this.time = time;
		}

		@Override
		public int compareTo(Point o) {
			return this.time - o.time;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()) - 1;
		adjList = new Node[N];
		radjList = new Node[N];
		int x, y, t;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			t = Integer.parseInt(st.nextToken());
			adjList[x] = new Node(y, t, adjList[x]);
			radjList[y] = new Node(x, t, radjList[y]);
		}
		int max = 0, temp = 0;
		int[] gD = go(X, radjList);
		int[] cD = go(X, adjList);
		for(int i=0; i<N; i++) {
			if(i==X) continue;
			temp = gD[i] + cD[i];
			if(max < temp) max = temp;
		}
		System.out.println(max);
	}

	private static int[] go(int start, Node[] adjList) {
		int[] D = new int[N];
		Arrays.fill(D, Integer.MAX_VALUE);
		boolean[] visit = new boolean[N];
		D[start] = 0;

		PriorityQueue<Point> q = new PriorityQueue<Point>();
		q.offer(new Point(start, 0));

		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (visit[cur.v])
				continue;
			visit[cur.v] = true;
			for (Node temp = adjList[cur.v]; temp != null; temp = temp.next) {
				if (!visit[temp.no] && D[temp.no] > cur.time + temp.weight) {
					D[temp.no] = cur.time + temp.weight;
					q.offer(new Point(temp.no, D[temp.no]));
				}
			}
		}
		return D;
	}
}
