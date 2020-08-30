package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_B1697_숨바꼭질_김유창 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int n, count = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visit = new boolean[100001];
		if (K <= N) {
			System.out.println(N - K);
			return;
		}
		visit[N] = true;
		q.offer(N);
		L: while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				n = q.poll();
				if (n == K) {
					break L;
				} else {
					if (0 <= n - 1 && n - 1 <= 100000 && !visit[n - 1]) {
						visit[n - 1] = true;
						q.offer(n - 1);
					}
					if (0 <= n + 1 && n + 1 <= 100000 && !visit[n + 1]) {
						visit[n + 1] = true;
						q.offer(n + 1);
					}
					if (0 <= n * 2 && n * 2 <= 100000 && !visit[n * 2]) {
						visit[n * 2] = true;
						q.offer(n * 2);
					}
				}
			}
			count++;
		}
		System.out.println(count);
	}
}