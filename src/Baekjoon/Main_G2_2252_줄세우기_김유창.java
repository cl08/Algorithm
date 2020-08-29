package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G2_2252_줄세우기_김유창 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		int[] indegree = new int[N + 1];

		for (int i = 0; i < N + 1; i++)
			list.add(new ArrayList<Integer>());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			list.get(v1).add(v2);
			indegree[v2]++;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		Queue<Integer> result = new LinkedList<Integer>();

		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0)
				q.offer(i);
		}

		while (!q.isEmpty()) {
			int node = q.poll();
			result.offer(node);

			for (Integer linked : list.get(node)) {
				indegree[linked]--;

				if (indegree[linked] == 0)
					q.offer(linked);
			}
		}

		while (!result.isEmpty()) {
			System.out.print(result.poll() + " ");
		}
	}
}
