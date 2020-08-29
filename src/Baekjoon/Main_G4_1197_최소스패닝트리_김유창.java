package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_G4_1197_최소스패닝트리_김유창 {
	static int[] grp;

	static class Info implements Comparable<Info> {
		int v1;
		int v2;
		int cost;

		public Info() {
		}

		public Info(int v1, int v2, int cost) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Info o) {
			return this.cost > o.cost ? 1 : -1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		PriorityQueue<Info> q = new PriorityQueue<Info>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			q.offer(new Info(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken())));
		}
		grp = new int[V];
		makeSet();
		int count = 0;
		int result = 0;
		while (count != V - 1) {
			Info temp = q.poll();
			if (findSet(temp.v1) != findSet(temp.v2)) {
				result = result + temp.cost;
				union(temp.v1, temp.v2);
				count++;
			}
		}
		System.out.println(result);
	}

	public static void makeSet() {
		Arrays.fill(grp, -1);
	}

	public static int findSet(int a) {
		if (grp[a] < 0)
			return a;
		return grp[a] = findSet(grp[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot != bRoot) {
			grp[bRoot] = aRoot;
			return true;
		}
		return false;
	}
}
